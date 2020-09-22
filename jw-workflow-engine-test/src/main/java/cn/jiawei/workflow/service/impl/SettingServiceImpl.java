package cn.jiawei.workflow.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.jiawei.workflow.bean.bo.TemplateGroupBo;
import cn.jiawei.workflow.bean.entity.FormGroups;
import cn.jiawei.workflow.bean.entity.ProcessTemplates;
import cn.jiawei.workflow.bean.entity.TemplateGroup;
import cn.jiawei.workflow.bean.vo.TemplateGroupVo;
import cn.jiawei.workflow.mapper.FormGroupsMapper;
import cn.jiawei.workflow.mapper.ProcessTemplatesMapper;
import cn.jiawei.workflow.mapper.TemplateGroupMapper;
import cn.jiawei.workflow.service.SettingService;
import cn.jiawei.workflow.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author : willian fu
 * @date : 2020/9/21
 */
@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private ProcessTemplatesMapper templatesMapper;

    @Autowired
    private TemplateGroupMapper templateGroupMapper;

    @Autowired
    private FormGroupsMapper groupsMapper;

    /**
     * 查询表单组
     *
     * @return 表单组数据
     */
    @Override
    public Object getFormGroups(String token, String name) {
        if (ObjectUtil.isNull(token)) {

        }
        if (StrUtil.isNotBlank(token)) {

        }
        List<TemplateGroupBo> allformAndGroups = templateGroupMapper.getAllFormAndGroups();
        Map<Integer, List<TemplateGroupBo>> coverMap = new LinkedHashMap<>();
        allformAndGroups.forEach(fg -> {
            List<TemplateGroupBo> bos = coverMap.get(fg.getGroupId());
            if (ObjectUtil.isNull(bos)) {
                List<TemplateGroupBo> list = new ArrayList<>();
                list.add(fg);
                coverMap.put(fg.getGroupId(), list);
            } else {
                bos.add(fg);
            }
        });
        List<TemplateGroupVo> results = new ArrayList<>();
        coverMap.forEach((key, val) -> {
            List<TemplateGroupVo.Template> templates = new ArrayList<>();
            val.forEach(v -> {
                if (ObjectUtil.isNotNull(v.getTemplateId())) {
                    templates.add(TemplateGroupVo.Template.builder()
                            .id(v.getTemplateId())
                            .tgId(v.getId())
                            .name(v.getTemplateName())
                            .icon(v.getIcon())
                            .isStop(v.getIsStop())
                            .updated(v.getUpdated())
                            .background(v.getBackground())
                            .build());
                }
            });
            results.add(TemplateGroupVo.builder().id(key).name(val.get(0).getGroupName()).items(templates).build());
        });
        return R.ok(results);
    }

    /**
     * 表单及分组排序
     *
     * @param groups 分组数据
     * @return 排序结果
     */
    @Override
    @Transactional
    public Object formGroupsSort(List<TemplateGroupVo> groups) {
        int i = 0, j = 0;
        try {
            for (TemplateGroupVo group : groups) {
                groupsMapper.updateByPrimaryKeySelective(FormGroups.builder()
                        .groupId(group.getId())
                        .sortNum(group.getId().equals(0) ? 999999 : i + 2)
                        .build());
                for (TemplateGroupVo.Template item : group.getItems()) {
                    templateGroupMapper.updateByPrimaryKeySelective(
                            TemplateGroup.builder()
                                    .id(item.getTgId())
                                    .groupId(group.getId())
                                    .templateId(item.getId())
                                    .sortNum(j + 1).build());
                    j++;
                }
                i++;
                j = 0;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return R.badRequest("排序异常 " + e.getMessage());
        }
        return R.ok("排序成功");
    }

    /**
     * 查询表单模板数据
     *
     * @param templateId 模板id
     * @return 模板详情数据
     */
    @Override
    public Object getFormTemplateById(String templateId) {
        return R.ok(templatesMapper.selectByPrimaryKey(templateId));
    }

    /**
     * 修改分组
     *
     * @param id   分组ID
     * @param name 分组名
     * @return 修改结果
     */
    @Override
    public Object updateFormGroupName(Integer id, String name) {
        groupsMapper.updateByPrimaryKeySelective(FormGroups.builder()
                .groupId(id).groupName(name.trim()).build());
        return R.ok("修改成功");
    }

    /**
     * 新增表单分组
     *
     * @param name 分组名
     * @return 添加结果
     */
    @Override
    public Object createFormGroup(String name) {
        Date time = GregorianCalendar.getInstance().getTime();
        groupsMapper.insertSelective(
                FormGroups.builder().groupName(name)
                        .sortNum(1).created(time)
                        .updated(time).build());
        return R.ok("添加分组 " + name + " 成功");
    }

    /**
     * 删除分组
     *
     * @param id 分组ID
     * @return 删除结果
     */
    @Override
    @Transactional
    public Object deleteFormGroup(Integer id) {
        Example example = new Example(TemplateGroup.class);
        example.createCriteria().andEqualTo("groupId", id);
        templateGroupMapper.updateByExampleSelective(TemplateGroup.builder().groupId(1).build(), example);
        groupsMapper.deleteByPrimaryKey(id);
        return R.ok("删除分组成功");
    }

    /**
     * 编辑表单
     *
     * @param templateId 摸板ID
     * @param type       类型 stop using delete
     * @return 操作结果
     */
    @Override
    @Transactional
    public Object updateForm(String templateId, String type, Integer groupId) {
        boolean isStop = "stop".equals(type);
        Example example = new Example(TemplateGroup.class);
        example.createCriteria().andEqualTo("templateId", templateId);
        if ("using".equals(type) || isStop) {
            templatesMapper.updateByPrimaryKeySelective(
                    ProcessTemplates.builder().templateId(templateId).isStop(isStop).build());
            templateGroupMapper.updateByExampleSelective(TemplateGroup.builder().groupId(isStop ? 0 : 1).build(), example);
        } else if ("delete".equals(type)) {
            templatesMapper.deleteByPrimaryKey(templateId);
            templateGroupMapper.delete(TemplateGroup.builder().templateId(templateId).build());
        } else if ("move".equals(type)) {
            if (ObjectUtil.isNull(groupId)) {
                return R.badRequest("分组ID必传");
            }
            templateGroupMapper.updateByExampleSelective(
                    TemplateGroup.builder().templateId(templateId).groupId(groupId).build(), example);
        } else {
            return R.badRequest("不支持的操作");
        }
        return R.ok("操作成功");
    }
}
