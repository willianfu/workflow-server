package cn.jiawei.workflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : willian fu
 * @date : 2020/9/17
 */
@RestController
@RequestMapping("setting")
public class SettingController {

    @GetMapping("form/group")
    public Object getFormGroups(){

    }
}
