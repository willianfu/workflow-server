package cn.jiawei.workflow.service;

/**
 * @author : willian fu
 * @version : 1.0
 */
public interface OrgUserAndDeptService {

    /**
     * 查询组织架构树
     * @param deptId 部门id
     * @param isDept 只查询部门架构
     * @param showLeave 是否显示离职员工
     * @return 组织架构树数据
     */
    Object getOrgTreeData(Integer deptId, Boolean isDept, Boolean showLeave);


}
