package cn.jiawei.workflow.core.enums;

/**
 * @author : willian fu
 * @version : 1.0
 * 当审批人为空时执行逻辑
 */
public enum ApprovalUserEmptyEnum {

    //自动通过
    TO_PASS,

    //转交给管理员
    TO_ADMIN,

    //转交到指定人员
    TO_USER;
}
