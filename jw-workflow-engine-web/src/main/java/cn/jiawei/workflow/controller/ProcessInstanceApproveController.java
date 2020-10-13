package cn.jiawei.workflow.controller;

import cn.jiawei.workflow.bean.vo.ProcessApproveVo;
import cn.jiawei.workflow.service.ProcessInstanceApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : willian fu
 * @version : 1.0
 */
@RestController
@RequestMapping("process/instance")
public class ProcessInstanceApproveController {

    @Autowired
    private ProcessInstanceApproveService approveService;


    /**
     * 获取审批流程实例详情
     * @param userId 用户ID
     * @param instanceId 实例ID
     * @return 审批流程实例详情
     */
    @GetMapping("{instanceId}/{userId}")
    public Object getProcessInstance(@PathVariable String userId,
                                     @PathVariable String instanceId){
        return approveService.getProcessInstance(userId, instanceId);
    }

    /**
     * 进行审批
     * @param approveVo 审批信息
     * @return 审批结果
     */
    @PostMapping
    public Object processApproveControl(@RequestBody ProcessApproveVo approveVo){
        return approveService.processApproveControl(approveVo);
    }

    /**
     * 审批评论
     * @param approveVo 评论信息
     * @return 评论结果
     */
    @PostMapping("comment")
    public Object processApproveComment(@RequestBody ProcessApproveVo approveVo){
        return approveService.processApproveComment(approveVo);
    }
}
