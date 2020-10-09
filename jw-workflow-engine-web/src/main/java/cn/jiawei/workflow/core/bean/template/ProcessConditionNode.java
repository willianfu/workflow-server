package cn.jiawei.workflow.core.bean.template;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author : willian fu
 * @version : 1.0
 * 审批条件
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProcessConditionNode extends Node{

    //条件列表
    private List<ProcessCondition> condition;

    private List<Integer> cids;
}
