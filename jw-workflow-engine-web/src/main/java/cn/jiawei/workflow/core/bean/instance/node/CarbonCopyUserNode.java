package cn.jiawei.workflow.core.bean.instance.node;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author : willian fu
 * @date : 2020/9/29
 * 抄送结点
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CarbonCopyUserNode extends BaseUserNode {

    //是否已读
    private Boolean read;

    //抄送时间
    private Date created;
}
