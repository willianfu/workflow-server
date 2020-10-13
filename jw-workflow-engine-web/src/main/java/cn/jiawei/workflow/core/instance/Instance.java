package cn.jiawei.workflow.core.instance;

import cn.jiawei.workflow.core.Process;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : willian fu
 * @version : 1.0
 */
public abstract class Instance {

    //进行中的审批实例
    protected static final Map<String, Process> instanceCache = new ConcurrentHashMap<>();

    protected Map<String, Process> getInstance(){return instanceCache;};

    public static Process getInstance(String instanceId){
        return instanceCache.get(instanceId);
    }
}
