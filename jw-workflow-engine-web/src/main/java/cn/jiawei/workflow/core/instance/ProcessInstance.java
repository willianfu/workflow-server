package cn.jiawei.workflow.core.instance;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.jiawei.workflow.bean.entity.ProcessInstances;
import cn.jiawei.workflow.core.Notify;
import cn.jiawei.workflow.core.Process;
import cn.jiawei.workflow.core.bean.instance.ApproveOpinion;
import cn.jiawei.workflow.core.bean.instance.ProcessInstanceNode;
import cn.jiawei.workflow.core.bean.instance.node.ApproveUserNode;
import cn.jiawei.workflow.core.bean.instance.node.BaseUserNode;

import cn.jiawei.workflow.core.enums.ApprovalModeEnum;
import cn.jiawei.workflow.core.enums.ApprovalResultEnum;
import cn.jiawei.workflow.core.enums.ProcessInstanceStatusEnum;
import cn.jiawei.workflow.core.exceptions.NotFoundApprovalUserException;
import cn.jiawei.workflow.core.exceptions.ProcessNoAuthorityException;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author : willian fu
 * @version : 1.0
 * 审批流程实例
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProcessInstance extends Instance implements Process {

    //流程审批顺序 正向
    private Boolean direction = false;

    //消息通知接口
    private Notify notify;

    //审批实例
    private ProcessInstances processInstance;

    //当前审批结点
    private Integer currentApproveNode;

    //当前流程结点，包括评论
    private Integer currentProcessNode;

    //审批流程链
    private List<ProcessInstanceNode> processLinked;

    ProcessInstance() {
        List<ProcessInstanceNode> instanceNodes = new LinkedList<>();
        //instanceNodes.add(rootUserNode);
    }

    @Override
    public List<ProcessInstanceNode> getProcessLink() {
        return processLinked;
    }

    /**
     * 获取当前审批结点
     *
     * @return
     */
    @Override
    public ProcessInstanceNode getCurrentNode() {
        return this.processLinked.get(this.currentApproveNode);
    }

    /**
     * 同意流程
     */
    @Override
    public void agree(String userId, ApproveOpinion opinion)
            throws ProcessNoAuthorityException, NotFoundApprovalUserException {
        if (this.resultHandler(true, userId, opinion)) {
            //校验整个节点是否审批结束
            ApprovalResultEnum nodeResult = this.getNodeResult(this.getCurrentNode());
            if (ObjectUtil.isNull(nodeResult)) {
                //还没有结束
                this.processInstance.setResult(null);
                this.processInstance.setStatus(ProcessInstanceStatusEnum.RUNNING);
            } else if (ApprovalResultEnum.AGREE.equals(nodeResult)) {
                //本节点审批完成，获取下一个审批节点
                Integer nextApproveNode = this.getNextApproveNode();
                if (ObjectUtil.isNull(nextApproveNode)) {
                    //没有需要审批的节点，整个流程已经处理完毕
                    this.processInstance.setResult(nodeResult);
                    nextApproveNode = this.processLinked.size();
                    this.processInstance.setStatus(ProcessInstanceStatusEnum.FINISH);
                }
                //一直执行到下一个审批结点
                this.toEachExNextNode(nextApproveNode);
            } else if (ApprovalResultEnum.REFUSE.equals(nodeResult)) {
                this.processInstance.setResult(nodeResult);
                this.processInstance.setStatus(ProcessInstanceStatusEnum.FINISH);
            }
        }

    }

    /**
     * 执行下面的工作流节点
     * @param nextApproveNode 下一个审批结点/终点
     */
    private void toEachExNextNode(Integer nextApproveNode){
        do {
            this.currentProcessNode ++;
            ProcessInstanceNode instanceNode = this.processLinked.get(this.currentProcessNode);
            if (ObjectUtil.isNotNull(instanceNode)){
               //判断是否是抄送结点
                if (!instanceNode.getIsApprove()){
                    //是抄送结点就执行抄送任务
                    this.carbonCopyHandler(instanceNode);
                }else {
                    //审批结点就终止处理
                    break;
                }
            }
        }while (this.currentProcessNode < nextApproveNode);
    }


    /**
     * 处理抄送任务
     * @param node 抄送结点
     */
    private void carbonCopyHandler(ProcessInstanceNode node){
        node.getUserNode().forEach(cc -> {
            this.notify.info(cc.getUserId(), cc.getUserName() + "发起的审批，请知晓");
        });
    }

    /**
     * 驳回流程
     */
    @Override
    public void refuse(String userId, ApproveOpinion opinion)
            throws NotFoundApprovalUserException, ProcessNoAuthorityException {
        if (this.resultHandler(true, userId, opinion)) {
            //只要驳回了，整个流程直接结束
            this.processInstance.setResult(ApprovalResultEnum.REFUSE);
            this.processInstance.setStatus(ProcessInstanceStatusEnum.FINISH);
        }
    }

    /**
     * 撤销流程
     */
    @Override
    public void revoke(String userId) {

    }

    /**
     * 将处理流程反转
     */
    @Override
    public void reverse(String userId) {
        //暂不实现
    }

    /**
     * 添加一个结点
     *
     * @param instanceNode 结点数据
     */
    @Override
    public void appendNode(ProcessInstanceNode instanceNode) {
        if (this.currentApproveNode < this.processLinked.size()) {
            this.processLinked.add(this.currentApproveNode + 1, instanceNode);
        } else {
            throw new RuntimeException("当前结点位置:"
                    + this.currentApproveNode
                    + " 超出流程链长度:"
                    + this.processLinked.size());
        }
    }

    @Override
    public Map<String, Process> getInstance() {
        return super.getInstance();
    }

    /**
     * 获取某个节点的审批结果
     *
     * @param instanceNode 实例节点
     * @return 审批结果
     */
    private ApprovalResultEnum getNodeResult(ProcessInstanceNode instanceNode) {
        int size = instanceNode.getUserNode().size();
        int passNum = 0;
        for (BaseUserNode node : instanceNode.getUserNode()) {
            if (node instanceof ApproveUserNode) {
                ApproveUserNode approveNode = (ApproveUserNode) node;
                if (ApprovalResultEnum.REFUSE.equals(approveNode.getResult())) {
                    return ApprovalResultEnum.REFUSE;
                } else if (ApprovalResultEnum.AGREE.equals(approveNode.getResult())) {
                    //如果是或签并且审批人数大于1
                    if (ApprovalModeEnum.OR.equals(instanceNode.getMode())) {
                        return ApprovalResultEnum.AGREE;
                    } else if (ApprovalModeEnum.AND.equals(instanceNode.getMode())) {
                        passNum++;
                    }
                    if (size == passNum) {
                        return ApprovalResultEnum.AGREE;
                    }
                }
            }
        }
        return null;
    }


    /**
     * 获取下一个审批节点
     *
     * @return 下一个审批节点的索引位置
     */
    private Integer getNextApproveNode() {
        Integer index = 0;
        for (ProcessInstanceNode node : this.processLinked) {
            //必须是后面的节点
            if (index > this.currentApproveNode
                    && CollectionUtil.isNotEmpty(node.getUserNode())
                    && node.getUserNode().get(0) instanceof ApproveUserNode) {
                return index;
            }
            index++;
        }
        return null;
    }

    /**
     * 处理审批
     *
     * @param result  审批结果
     * @param userId  用户ID标识
     * @param opinion 意见评论
     * @return
     * @throws ProcessNoAuthorityException
     * @throws NotFoundApprovalUserException
     */
    private boolean resultHandler(boolean result, String userId, ApproveOpinion opinion)
            throws ProcessNoAuthorityException, NotFoundApprovalUserException {
        ProcessInstanceNode currentNode = this.getCurrentNode();
        if (CollectionUtil.isNotEmpty(currentNode.getUserNode())) {
            for (BaseUserNode node : currentNode.getUserNode()) {
                if (node instanceof ApproveUserNode) {
                    ApproveUserNode approveNode = (ApproveUserNode) node;
                    if (userId.equals(node.getUserId()) && ObjectUtil.isNull(approveNode.getResult())) {
                        approveNode.setResult(result ? ApprovalResultEnum.AGREE : ApprovalResultEnum.REFUSE);
                        approveNode.setEnd(GregorianCalendar.getInstance().getTime());
                        approveNode.setOpinion(opinion);
                        return true;
                    } else {
                        throw new ProcessNoAuthorityException("该用户无法审批当前节点");
                    }
                }
            }
        } else {
            throw new NotFoundApprovalUserException("当前审批结点审批人为空，无法审批");
        }
        return false;
    }
}
