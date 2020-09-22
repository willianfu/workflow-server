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
        if (ObjectUtil.isNull(token)){

        }
        if (StrUtil.isNotBlank(token)){

        }
        List<TemplateGroupBo> allformAndGroups = templateGroupMapper.getAllFormAndGroups();
        Map<Integer, List<TemplateGroupBo>> coverMap = new LinkedHashMap<>();
        allformAndGroups.forEach(fg -> {
            List<TemplateGroupBo> bos = coverMap.get(fg.getGroupId());
            if (ObjectUtil.isNull(bos)){
                List<TemplateGroupBo> list = new ArrayList<>();
                list.add(fg);
                coverMap.put(fg.getGroupId(), list);
            }else {
                bos.add(fg);
            }
        });
        List<TemplateGroupVo> results = new ArrayList<>();
        coverMap.forEach((key, val) ->{
            List<TemplateGroupVo.Template> templates = new ArrayList<>();
            val.forEach(v -> {
                if (ObjectUtil.isNotNull(v.getTemplateId())){
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
                        .sortNum(group.getId().equals(0) ? 999999: i + 2)
                        .build());
                for (TemplateGroupVo.Template item : group.getItems()) {
                    templateGroupMapper.updateByPrimaryKeySelective(
                            TemplateGroup.builder()
                                    .id(item.getTgId())
                                    .groupId(group.getId())
                                    .templateId(item.getId())
                                    .sortNum(j+1).build());
                    j++;
                }
                i++;
                j = 0;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return R.badRequest("排序异常 "+ e.getMessage());
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
}
