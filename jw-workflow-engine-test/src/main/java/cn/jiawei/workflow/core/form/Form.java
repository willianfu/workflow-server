package cn.jiawei.workflow.core.form;

import lombok.Data;

import java.util.List;

/**
 * @author : willian fu
 * @version : 1.0
 * 表单
 */
@Data
public class Form {

    public Long formId;

    public String name;

    public List<Object> values;
}
