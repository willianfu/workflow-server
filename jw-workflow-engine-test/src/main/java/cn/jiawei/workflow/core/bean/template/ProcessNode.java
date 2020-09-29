package cn.jiawei.workflow.core.bean.template;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author : willian fu
 * @version : 1.0
 * 普通流程节点
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProcessNode extends Node{

    private List<ProcessConditionNode> conditions;
}
