package cn.jiawei.workflow.core.bean.instance;

import lombok.Data;

import java.util.List;

/**
 * @author : willian fu
 * @version : 1.0
 * 流程实例节点
 */
@Data
public class ProcessInstanceNode {

    //节点ID
    private String nodeId;

    //节点名称
    private String nodeName;

    //该节点下的审批人
    private List<ProcessInstanceUserNode> userNode;
}
