package cn.jiawei.workflow.core.bean.template;

import cn.jiawei.workflow.core.enums.*;
import lombok.Data;

import java.util.List;

/**
 * @author : willian fu
 * @version : 1.0
 */
@Data
public class NodeProps {

    private Approval approval;

    @Data
    public static class Approval{

        //审批人选项类型
        private ApprovalTypeEnum type;

        //审批类型 会签/或签
        private ApprovalModeEnum mode;

        //当审批用户为空时如何做
        private ApprovalUserEmptyEnum userEmpty;

        //审批时限
        private ApprovalTimeLimitTypeEnum timeLimitType;

        //审批时限值
        private Double timeLimitVal;

        //审批超期事件
        private TimeoutEvent timeoutEvent;

        //是否需要手写签名
        private Boolean sign;

        //选择连续多级主管为审批人时的结束条件
        private ApprovalEndConditionEnum endCondition;

        //第几级主管
        private Integer leaderLevel;

        private User user;
    }

    /**
     * 超时事件
     */
    @Data
    public static class TimeoutEvent{

        //超时事件处理方式
        private ApprovalTimeoutEventEnum event;

        //是否循环提醒
        private Boolean loop;

        //每几天循环
        private Double loopTime;
    }

    /**
     * 审批用户
     */
    @Data
    public static class User{

        //选中的人员和部门
        private List<IdAndNameVo> users;

        //发起人自选  自选一个还是多个
        private Boolean multiple;

        //审批人选择指定角色时的角色
        private IdAndNameVo role;


    }
}
