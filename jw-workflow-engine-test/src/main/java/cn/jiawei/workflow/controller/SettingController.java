package cn.jiawei.workflow.controller;

import cn.jiawei.workflow.bean.vo.TemplateGroupVo;
import cn.jiawei.workflow.service.SettingService;
import org.apache.ibatis.annotations.Delete;
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
     * 表单分组排序
     * @param groups 分组数据
     * @return 排序结果
     */
    @PutMapping("form/group/sort")
    public Object formGroupsSort(@RequestBody List<TemplateGroupVo> groups){
        return settingService.formGroupsSort(groups);
    }

    /**
     * 修改分组
     * @param id 分组ID
     * @param name 分组名
     * @return 修改结果
     */
    @PutMapping("form/group")
    public Object updateFormGroupName(@RequestParam Integer id,
                                       @RequestParam String name){
        return settingService.updateFormGroupName(id, name);
    }

    /**
     * 新增表单分组
     * @param name 分组名
     * @return 添加结果
     */
    @PostMapping("form/group")
    public Object createFormGroup(@RequestParam String name){
        return settingService.createFormGroup(name);
    }

    /**
     * 删除分组
     * @param id 分组ID
     * @return 删除结果
     */
    @DeleteMapping("form/group")
    public Object deleteFormGroup(@RequestParam Integer id){
        return settingService.deleteFormGroup(id);
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
     * 编辑表单
     * @param templateId 摸板ID
     * @param type 类型 stop using delete
     * @return 操作结果
     */
    @PutMapping("form")
    public Object updateForm(@RequestParam String templateId,
                             @RequestParam String type,
                             @RequestParam(required = false) Integer groupId){
        return settingService.updateForm(templateId, type, groupId);
    }
}
