package cn.jiawei.workflow.controller;

import cn.jiawei.workflow.bean.vo.TemplateGroupVo;
import cn.jiawei.workflow.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : willian fu
 * @date : 2020/9/17
 */
@RestController
@RequestMapping("setting")
public class SettingController {

    @Autowired
    private SettingService settingService;


    /**
     * 查询所有表单分组
     * @return
     */
    @GetMapping("form/group")
    public Object getFormGroups(){
        return settingService.getFormGroups(null, null);
    }

    /**
     * 查询表单模板数据
     * @param templateId 模板id
     * @return 模板详情数据
     */
    @GetMapping("form/detail")
    public Object getFormTemplateById(@RequestParam String templateId){
        return settingService.getFormTemplateById(templateId);
    }

    /**
     * 表单分组排序
     * @param groups 分组数据
     * @return 排序结果
     */
    @PutMapping("form/group/sort")
    public Object formGroupsSort(@RequestBody List<TemplateGroupVo> groups){
        return settingService.formGroupsSort(groups);
    }


}
