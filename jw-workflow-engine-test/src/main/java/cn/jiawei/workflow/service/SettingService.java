package cn.jiawei.workflow.service;

import cn.jiawei.workflow.bean.vo.TemplateGroupVo;

import java.util.List;

/**
 * @author : willian fu
 * @date : 2020/9/21
 */
public interface SettingService {

    /**
     * 查询表单组
     * @return 表单组数据
     */
    Object getFormGroups(String token, String name);

    /**
     * 表单及分组排序
     * @param groups 分组数据
     * @return 排序结果
     */
    Object formGroupsSort(List<TemplateGroupVo> groups);

    /**
     * 查询表单模板数据
     * @param templateId 模板id
     * @return 模板详情数据
     */
    Object getFormTemplateById(String templateId);
}
