package cn.jiawei.workflow.core.bean.instance.node;

import cn.jiawei.workflow.core.bean.instance.ApproveOpinion;
import cn.jiawei.workflow.core.enums.ApprovalResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author : willian fu
 * @version : 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApproveUserNode extends BaseUserNode {

    //审批开始时间
    private Date start;

    //审批结束时间
    private Date end;

    //审批结果
    private ApprovalResultEnum result;

    //审批意见/评论
    private ApproveOpinion opinion;

}
