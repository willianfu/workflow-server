package cn.jiawei.workflow.core.enums;

/**
 * @author : willian fu
 * @version : 1.0
 * 审批人选项类型
 */
public enum ApprovalTypeEnum {

    //指定成员
    APPOINT("1"),

    //发起人自选
    SELECT("2"),

    //多级主管
    MORE_LEADER("3"),

    //主管
    LEADER("4"),

    //角色
    ROLE("5"),

    //发起人自己
    SELF("6");

    private String type;

    ApprovalTypeEnum(String type) {
        this.type = type;
    }

    public ApprovalTypeEnum getByType(String type){
        for (ApprovalTypeEnum value : ApprovalTypeEnum.values()) {
            if (value.type.equals(type)){
                return value;
            }
        }
        return null;
    }
}
