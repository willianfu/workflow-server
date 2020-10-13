package cn.jiawei.workflow.bean.vo;

import cn.jiawei.workflow.core.bean.instance.ProcessInstanceNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author : willian fu
 * @version : 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessInstancesVo {

    /**
     * 发起人id
     */
    private String commitUser;
    /**
     * 表单内容
     */
    private String forms;

    /**
     * 审批流程链
     */
    private List<ProcessInstanceNode> instanceNodes;

}
