package cn.jiawei.workflow.core;

import java.util.LinkedList;

/**
 * @author : willian fu
 * @version : 1.0
 */
public interface ProcessInstance {


    LinkedList<Process> getProcessLink();

    /**
     * 同意流程
     */
    void agree();

    /**
     * 驳回流程
     */
    void refuse();

    /**
     * 撤销流程
     */
    void revoke();

    /**
     * 将处理流程反转
     */
    void reverse();
}
