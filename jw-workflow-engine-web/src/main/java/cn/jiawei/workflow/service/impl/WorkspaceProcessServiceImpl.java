package cn.jiawei.workflow.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.jiawei.workflow.bean.entity.ProcessInstances;
import cn.jiawei.workflow.bean.vo.ProcessInstancesVo;
import cn.jiawei.workflow.core.enums.ProcessInstanceStatusEnum;
import cn.jiawei.workflow.core.exceptions.NotFoundApprovalUserException;
import cn.jiawei.workflow.core.exceptions.ProcessNoAuthorityException;
import cn.jiawei.workflow.core.instance.ProcessInstance;
import cn.jiawei.workflow.mapper.ProcessInstancesMapper;
import cn.jiawei.workflow.service.SettingService;
import cn.jiawei.workflow.service.WorkspaceProcessService;
import cn.jiawei.workflow.utils.CookieUtil;
import cn.jiawei.workflow.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * @author : willian fu
 * @version : 1.0
 */
@Service
public class WorkspaceProcessServiceImpl implements WorkspaceProcessService {

    @Autowired
    private SettingService settingService;

    @Autowired
    private ProcessInstancesMapper instancesMapper;

    /**
     * 获取用户可见表单
     *
     * @return
     */
    @Override
    public Object getFormGroups(String name) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = CookieUtil.getCookieValue(servletRequestAttributes.getRequest(), "token");

        return null;
    }

    /**
     * 提交一个审批流程
     *
     * @param instancesVo 流程信息
     * @return 提交结果
     */
    @Override
    @Transactional
    public Object createApproveInstance(ProcessInstancesVo instancesVo) {
        Date time = GregorianCalendar.getInstance().getTime();
        ProcessInstances processInstances = ProcessInstances.builder()
                .instanceId(UUID.randomUUID().toString())
                .commitUser(instancesVo.getCommitUser())
                .forms(instancesVo.getForms())
                .instanceNodes(JSONUtil.toJsonStr(instancesVo.getInstanceNodes()))
                .status(ProcessInstanceStatusEnum.RUNNING)
                .created(time)
                .updated(time)
                .build();
        instancesMapper.insertSelective(processInstances);
        ProcessInstance instance = new ProcessInstance();
        instance.setProcessLinked(instancesVo.getInstanceNodes());
        instance.setInstanceId(processInstances.getInstanceId());
        instance.startup();
        return R.ok("提交审批成功");
    }
}
