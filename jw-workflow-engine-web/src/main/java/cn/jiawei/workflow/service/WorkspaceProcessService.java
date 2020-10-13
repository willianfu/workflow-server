package cn.jiawei.workflow.service;

import cn.jiawei.workflow.bean.entity.ProcessInstances;
import cn.jiawei.workflow.bean.vo.ProcessInstancesVo;

/**
 * @author : willian fu
 * @version : 1.0
 */
public interface WorkspaceProcessService {

    /**
     * 获取用户可见表单
     * @return
     */
    Object getFormGroups(String name);

    /**
     * 提交一个审批流程
     * @param instancesVo 流程信息
     * @return 提交结果
     */
    Object createApproveInstance(ProcessInstancesVo instancesVo);
}
