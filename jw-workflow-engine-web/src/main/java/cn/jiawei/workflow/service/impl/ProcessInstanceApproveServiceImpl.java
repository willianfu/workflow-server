package cn.jiawei.workflow.service.impl;

import cn.jiawei.workflow.bean.vo.ProcessApproveVo;
import cn.jiawei.workflow.core.Process;
import cn.jiawei.workflow.core.enums.ApprovalResultEnum;
import cn.jiawei.workflow.core.exceptions.NotFoundApprovalUserException;
import cn.jiawei.workflow.core.exceptions.ProcessNoAuthorityException;
import cn.jiawei.workflow.core.instance.Instance;
import cn.jiawei.workflow.service.ProcessInstanceApproveService;
import cn.jiawei.workflow.utils.R;
import org.springframework.stereotype.Service;

/**
 * @author : willian fu
 * @version : 1.0
 */
@Service
public class ProcessInstanceApproveServiceImpl implements ProcessInstanceApproveService {



    /**
     * 获取审批流程实例详情
     *
     * @param userId     用户ID
     * @param instanceId 实例ID
     * @return 审批流程实例详情
     */
    @Override
    public Object getProcessInstance(String userId, String instanceId) {
        return R.ok(Instance.getInstance(instanceId));
    }

    /**
     * 进行审批
     *
     * @param approveVo 审批信息
     * @return 审批结果
     */
    @Override
    public Object processApproveControl(ProcessApproveVo approveVo) {
        Process instance = Instance.getInstance(approveVo.getInstanceId());
        try {
            if (ApprovalResultEnum.AGREE.equals(approveVo.getResult())) {
                instance.agree(approveVo.getUserId(), approveVo.getOpinion());
            } else if (ApprovalResultEnum.REFUSE.equals(approveVo.getResult())) {
                instance.refuse(approveVo.getUserId(), approveVo.getOpinion());
            }
        } catch (ProcessNoAuthorityException | NotFoundApprovalUserException e) {
            return R.badRequest(e.getMessage());
        }
        return R.ok("操作成功");
    }

    /**
     * 审批评论
     *
     * @param approveVo 评论信息
     * @return 评论结果
     */
    @Override
    public Object processApproveComment(ProcessApproveVo approveVo) {
        return null;
    }


}
