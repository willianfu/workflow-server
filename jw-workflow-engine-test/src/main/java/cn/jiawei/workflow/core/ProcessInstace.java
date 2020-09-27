package cn.jiawei.workflow.core;

import java.util.LinkedList;

/**
 * @author : willian fu
 * @version : 1.0
 */
public interface ProcessInstace {


    LinkedList<Process> getProcessLink();

    boolean create();

    /**
     * 撤销流程
     * @param instanceId 流程实例ID
     * @return 撤回操作结果
     */
    boolean revoke(String instanceId);


}
