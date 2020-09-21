package cn.jiawei.workflow.controller;

import cn.jiawei.workflow.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : willian fu
 * @date : 2020/9/17
 */
@RestController
@RequestMapping("setting")
public class SettingController {

    @Autowired
    private SettingService settingService;


    @GetMapping("form/group")
    public Object getFormGroups(){
        return settingService.getFormGroups();
    }
}
