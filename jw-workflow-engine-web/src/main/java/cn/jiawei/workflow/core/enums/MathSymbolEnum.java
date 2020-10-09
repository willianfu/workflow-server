package cn.jiawei.workflow.core.enums;

/**
 * @author : willian fu
 * @version : 1.0
 * 数学比较符号
 */
public enum MathSymbolEnum {
    //大于
    GT(">"),

    LT("<"),

    EQ("="),

    GT_EQ(">="),

    LT_EQ("<="),

    //包含
    IN("in"),

    //在两者之间
    BETWEEN("between"),

    //在两者之间并包括两者
    BETWEEN_EQ("between-eq");



    private String symbol;

    MathSymbolEnum(String symbol) {
        this.symbol = symbol;
    }

    public MathSymbolEnum getByType(String type){
        for (MathSymbolEnum value : MathSymbolEnum.values()) {
            if (value.symbol.equals(type)){
                return value;
            }
        }
        return null;
    }
}
