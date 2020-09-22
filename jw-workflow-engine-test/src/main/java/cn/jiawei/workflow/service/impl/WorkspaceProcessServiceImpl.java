package cn.jiawei.workflow.service.impl;

import cn.jiawei.workflow.service.SettingService;
import cn.jiawei.workflow.service.WorkspaceProcessService;
import cn.jiawei.workflow.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author : willian fu
 * @version : 1.0
 */
@Service
public class WorkspaceProcessServiceImpl implements WorkspaceProcessService {

    @Autowired
    private SettingService settingService;

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
}
