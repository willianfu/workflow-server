package cn.jiawei.workflow.controller;

import cn.jiawei.workflow.service.WorkspaceProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
