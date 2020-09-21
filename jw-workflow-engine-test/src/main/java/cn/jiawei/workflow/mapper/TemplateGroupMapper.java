package cn.jiawei.workflow.mapper;

import cn.jiawei.workflow.bean.bo.TemplateGroupBo;
import cn.jiawei.workflow.bean.entity.TemplateGroup;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author : willian fu
 * @date : 2020/9/21
 */
public interface TemplateGroupMapper extends Mapper<TemplateGroup> {

    /**
     * 查询所有表单及组
     * @return
     */
    @Select("SELECT tg.group_id, fg.group_name, tg.template_id, pt.template_name, pt.icon, pt.background \n" +
            "FROM template_group tg LEFT JOIN process_templates pt ON pt.template_id = tg.template_id\n" +
            "LEFT JOIN form_groups fg ON tg.group_id = fg.group_id ORDER BY fg.sort_num ASC, tg.sort_num ASC")
    List<TemplateGroupBo> getAllformAndGroups();
}
