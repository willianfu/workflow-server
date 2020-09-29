package cn.jiawei.workflow.core.bean.instance.node;

import cn.jiawei.workflow.core.bean.instance.ApproveOpinion;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author : willian fu
 * @date : 2020/9/29
 * 评论用户结点
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OpinionUserNode extends BaseUserNode {

    //意见评论
    private ApproveOpinion opinion;

    //时间
    private Date created;
}
