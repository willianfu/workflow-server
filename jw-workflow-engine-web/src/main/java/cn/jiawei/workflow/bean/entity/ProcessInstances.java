package cn.jiawei.workflow.bean.entity;

import java.util.Date;
import java.io.Serializable;

import cn.jiawei.workflow.core.enums.ApprovalResultEnum;
import cn.jiawei.workflow.core.enums.ProcessInstanceStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * (ProcessInstance)实体类
 *
 * @author makejava
 * @since 2020-10-02 14:55:13
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "process_instances")
public class ProcessInstances implements Serializable {
    private static final long serialVersionUID = -68887731634085079L;
    
    @Id
    /**
    * 流程实例ID
    */
    private Integer instanceId;
    /**
    * 流程标题
    */
    private String title;
    /**
    * 发起人id
    */
    private String commitUser;
    /**
    * 表单内容
    */
    private String forms;
    /**
    * 流程状态
    */
    private ProcessInstanceStatusEnum status;
    /**
    * 流程结果
    */
    private ApprovalResultEnum result;
    /**
    * 审批流程链
    */
    private String instanceNodes;
    /**
    * 发起时间
    */
    private Date created;
    /**
    * 流程变更时间
    */
    private Date updated;

}