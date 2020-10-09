package cn.jiawei.workflow.core.enums;

/**
 * @author : willian fu
 * @version : 1.0
 * 流程节点类型
 */
public enum NodeTypeEnum {

    //发起人节点
    ROOT,

    //条件子节点
    TJ,

    //审批节点
    SP,

    //抄送节点
    CS,

    //条件分叉节点
    CONDITION,

    //评论节点
    COMMENT;
}
