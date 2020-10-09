package cn.jiawei.workflow.core.bean.template;

import cn.jiawei.workflow.core.enums.ApprovalConditionTypeEnum;
import cn.jiawei.workflow.core.enums.MathSymbolEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : willian fu
 * @version : 1.0
 * 审批条件
 */
@Data
public class ProcessCondition {

    //审批条件ID
    private Long id;

    //审批条件名
    private String name;

    //条件数据类型
    private ApprovalConditionTypeEnum type;

    //条件运算符
    private MathSymbolEnum symbol;

    //左值 / 唯一值
    private BigDecimal left;

    //右值
    private BigDecimal right;

    //多选值，值选择器
    private List<String> values;

    //多选值，组织机构选择器
    private List<IdAndNameVo> orgValues;
}
