package cn.jiawei.workflow.core.bean.instance.node;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author : willian fu
 * @date : 2020/9/29
 * 发起人结点
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RootUserNode extends BaseUserNode{

    private Date created;
}
