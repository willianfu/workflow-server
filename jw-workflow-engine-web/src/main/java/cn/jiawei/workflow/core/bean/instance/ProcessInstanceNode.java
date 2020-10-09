package cn.jiawei.workflow.core.bean.instance;

import cn.jiawei.workflow.core.bean.instance.node.BaseUserNode;
import cn.jiawei.workflow.core.enums.ApprovalModeEnum;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : willian fu
 * @version : 1.0
 * 流程实例节点
 */
@Data
@Builder
@NoArgsConstructor
public class ProcessInstanceNode {

    //节点ID
    private String taskId;

    //节点名称
    private String taskName;

    //是审批结点
    private Boolean isApprove;

    //审批模式 会签/或签/依次顺序签署
    private ApprovalModeEnum mode;

    //该节点下的人员
    private List<BaseUserNode> userNode;
}
