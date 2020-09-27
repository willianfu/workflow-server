package cn.jiawei.workflow.bean.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * (ProcessTemplates)实体类
 *
 * @author makejava
 * @since 2020-09-21 22:50:29
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "process_templates")
public class ProcessTemplates implements Serializable {
    private static final long serialVersionUID = -95829441258242072L;

    @Id
    /**
    * 审批摸板ID
    */
    private String templateId;
    /**
    * 摸板名称
    */
    private String templateName;
    /**
    * 摸板表单
    */
    private String formItems;

    private String process;
    /**
    * 图标
    */
    private String icon;
    /**
    * 图标背景色
    */
    private String background;

    private String notify;
    /**
    * 谁能提交
    */
    private String whoCommit;
    /**
    * 谁能编辑
    */
    private String whoEdit;
    /**
    * 谁能导出数据
    */
    private String whoExport;

    private String remark;
    /**
    * 是否已停用
    */
    private Boolean isStop;
    /**
    * 创建时间
    */
    private Date created;
    /**
    * 更新时间
    */
    private Date updated;

}
