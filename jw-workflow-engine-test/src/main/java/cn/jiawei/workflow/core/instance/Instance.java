package cn.jiawei.workflow.core.instance;

import cn.jiawei.workflow.core.ProcessInstace;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : willian fu
 * @version : 1.0
 */
public abstract class Instance {

    //审批实例
    protected static final Map<String, ProcessInstace> instanceCache = new ConcurrentHashMap<>();

    public abstract Map<String, ProcessInstace> getInstance();
}
