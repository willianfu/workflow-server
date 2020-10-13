package cn.jiawei.workflow.service;

import cn.jiawei.workflow.bean.vo.ProcessApproveVo;

/**
 * @author : willian fu
 * @version : 1.0
 */
public interface ProcessInstanceApproveService {
    /**
     * 获取审批流程实例详情
     * @param userId 用户ID
     * @param instanceId 实例ID
     * @return 审批流程实例详情
     */
    Object getProcessInstance(String userId, String instanceId);

    /**
     * 进行审批
     * @param approveVo 审批信息
     * @return 审批结果
     */
    Object processApproveControl(ProcessApproveVo approveVo);

    /**
     * 审批评论
     * @param approveVo 评论信息
     * @return 评论结果
     */
    Object processApproveComment(ProcessApproveVo approveVo);
}
