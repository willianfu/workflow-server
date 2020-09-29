package cn.jiawei.workflow.core;

import cn.jiawei.workflow.core.bean.instance.ProcessInstanceNode;
import cn.jiawei.workflow.core.exceptions.ProcessNoAuthorityException;

import java.util.LinkedList;

/**
 * @author : willian fu
 * @version : 1.0
 */
public interface Process {

    /**
     * 获取流程链
     * @return
     */
    LinkedList<ProcessInstanceNode> getProcessLink();

    /**
     * 获取当前结点
     * @return
     */
    ProcessInstanceNode getCurrentNode();

    /**
     * 同意流程
     */
    void agree(String userId) throws ProcessNoAuthorityException;

    /**
     * 驳回流程
     */
    void refuse(String userId);

    /**
     * 撤销流程
     */
    void revoke(String userId);

    /**
     * 将处理流程反转
     */
    void reverse(String userId);

    /**
     * 添加一个结点
     * @param instanceNode 结点数据
     */
    void appendNode(ProcessInstanceNode instanceNode);
}
