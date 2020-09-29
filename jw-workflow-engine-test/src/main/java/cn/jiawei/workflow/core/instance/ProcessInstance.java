package cn.jiawei.workflow.core.instance;

import cn.jiawei.workflow.core.Process;
import cn.jiawei.workflow.core.bean.instance.ProcessInstanceNode;
import cn.jiawei.workflow.core.bean.instance.node.RootUserNode;
import cn.jiawei.workflow.core.enums.ApprovalResultEnum;
import cn.jiawei.workflow.core.enums.ProcessInstanceStatusEnum;
import cn.jiawei.workflow.core.exceptions.NotFoundApprovalUserException;
import cn.jiawei.workflow.core.exceptions.ProcessNoAuthorityException;

import java.util.LinkedList;
import java.util.Map;

/**
 * @author : willian fu
 * @version : 1.0
 * 审批流程实例
 */
public class ProcessInstance extends Instance implements Process {

    //流程审批顺序 正向
    private Boolean direction = false;

    //审批实例ID
    private String instanceId;

    //审批结果
    private ApprovalResultEnum result;

    //审批状态
    private ProcessInstanceStatusEnum status;

    //当前审批结点
    private Integer currentApproveNode;

    //当前流程结点，包括评论
    private Integer currentProcessNode;

    private LinkedList<ProcessInstanceNode> processLinked;

    ProcessInstance(){
        LinkedList<ProcessInstanceNode> instanceNodes = new LinkedList<>();
        instanceNodes.add(rootUserNode);
    }

    @Override
    public LinkedList<ProcessInstanceNode> getProcessLink() {
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
    public void agree(String userId) throws ProcessNoAuthorityException, NotFoundApprovalUserException {
        ProcessInstanceNode currentNode = this.getCurrentNode();
        if (currentNode.getUserNode().size() != 0){

        }else {
            throw new NotFoundApprovalUserException("当前审批结点审批人为空，无法审批");
        }
    }

    /**
     * 驳回流程
     */
    @Override
    public void refuse(String userId) {

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

    }

    /**
     * 添加一个结点
     *
     * @param instanceNode 结点数据
     */
    @Override
    public void appendNode(ProcessInstanceNode instanceNode) {
        if (this.currentApproveNode < this.processLinked.size()){
            this.processLinked.add(this.currentApproveNode + 1, instanceNode);
        }else {
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
}
