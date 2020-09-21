package cn.jiawei.workflow.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.jiawei.workflow.bean.bo.TemplateGroupBo;
import cn.jiawei.workflow.bean.entity.ProcessTemplates;
import cn.jiawei.workflow.bean.entity.TemplateGroup;
import cn.jiawei.workflow.bean.vo.TemplateGroupVo;
import cn.jiawei.workflow.mapper.ProcessTemplatesMapper;
import cn.jiawei.workflow.mapper.TemplateGroupMapper;
import cn.jiawei.workflow.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : willian fu
 * @date : 2020/9/21
 */
@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private ProcessTemplatesMapper templatesMapper;

    @Autowired
    private TemplateGroupMapper groupMapper;
    /**
     * 查询表单组
     *
     * @return 表单组数据
     */
    @Override
    public Object getFormGroups() {
        List<TemplateGroupBo> allformAndGroups = groupMapper.getAllformAndGroups();
        Map<Integer, List<TemplateGroupBo>> coverMap = new HashMap<>();
        allformAndGroups.forEach(fg -> {
            List<TemplateGroupBo> bos = coverMap.get(fg.getGroupId());
            if (ObjectUtil.isNull(bos)){
                List<TemplateGroupBo> list = new ArrayList<>();
                list.add(fg);
            }else {
                bos.add(fg);
            }
        });
        List<TemplateGroupVo> results = new ArrayList<>();
        coverMap.forEach((k, v) ->{
            TemplateGroupVo.builder().id(k).name(v.g)
        });
        return null;
    }
}
