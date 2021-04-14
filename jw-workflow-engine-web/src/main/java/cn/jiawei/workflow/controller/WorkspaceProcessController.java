package cn.jiawei.workflow.controller;

import cn.jiawei.workflow.bean.vo.ProcessInstancesVo;
import cn.jiawei.workflow.service.WorkspaceProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : willian fu
 * @version : 1.0
 */
@RestController
@RequestMapping("workspace")
public class WorkspaceProcessController {

    @Autowired
    private WorkspaceProcessService processService;


    @GetMapping("form/group")
    public Object getFormGroups(@RequestParam(required = false) String name){
        return processService.getFormGroups(name);
    }

    @GetMapping("process")
    public Object getProcessInstance(@RequestParam Integer status){
        return null;//processService.getProcessInstance();
    }

    @GetMapping("process/{status}")
    public Object getProcessInstance(){
        return null;//processService.getProcessInstance();
    }

    /**
     * 创建审批实例
     * @param instancesVo 审批信息
     * @return 创建结果
     */
    @PostMapping("process/instance")
    public Object createApproveInstance(@RequestBody ProcessInstancesVo instancesVo){
        return processService.createApproveInstance(instancesVo);
    }
}
