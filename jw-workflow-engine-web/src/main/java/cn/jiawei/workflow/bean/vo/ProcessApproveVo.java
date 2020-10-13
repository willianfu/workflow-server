package cn.jiawei.workflow.bean.vo;

import cn.jiawei.workflow.core.bean.instance.ApproveOpinion;
import cn.jiawei.workflow.core.enums.ApprovalResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : willian fu
 * @version : 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessApproveVo {

    private String userId;

    private String instanceId;

    private ApproveOpinion opinion;

    private ApprovalResultEnum result;
}
