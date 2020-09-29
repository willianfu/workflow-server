package cn.jiawei.workflow.core.instance;

import cn.jiawei.workflow.core.Process;

import java.util.LinkedList;
import java.util.Map;

/**
 * @author : willian fu
 * @version : 1.0
 */
public class ProcessInstance extends Instance implements Process {

    private Boolean direction = false;

    @Override
    public LinkedList<java.lang.Process> getProcessLink() {
        return null;
    }

    /**
     * 同意流程
     */
    @Override
    public void agree() {

    }

    /**
     * 驳回流程
     */
    @Override
    public void refuse() {

    }

    /**
     * 撤销流程
     */
    @Override
    public void revoke() {

    }

    /**
     * 将处理流程反转
     */
    @Override
    public void reverse() {

    }

    @Override
    public Map<String, Process> getInstance() {
        return super.getInstance();
    }
}
