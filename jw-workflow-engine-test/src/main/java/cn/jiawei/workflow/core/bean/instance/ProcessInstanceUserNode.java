package cn.jiawei.workflow.core.bean.instance;

import cn.jiawei.workflow.core.enums.ApprovalResultEnum;
import cn.jiawei.workflow.core.enums.NodeTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author : willian fu
 * @version : 1.0
 */
@Data
public class ProcessInstanceUserNode {

    private String userId;

    //节点类型，只有审批/抄送
    private NodeTypeEnum type;

    private String userName;

    private String avatar;

    //审批开始时间
    private Date start;

    //审批结束时间
    private Date end;

    //审批结果
    private ApprovalResultEnum result;

    //审批意见
    private String opinion;

}
