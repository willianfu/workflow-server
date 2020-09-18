package cn.jiawei.workflow.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.PinyinUtil;
import cn.jiawei.workflow.bean.entity.Departments;
import cn.jiawei.workflow.bean.entity.Users;
import cn.jiawei.workflow.bean.vo.OrgTreeVo;
import cn.jiawei.workflow.mapper.DepartmentsMapper;
import cn.jiawei.workflow.mapper.UsersMapper;
import cn.jiawei.workflow.service.OrgUserAndDeptService;
import cn.jiawei.workflow.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author : willian fu
 * @version : 1.0
 */
@Service
public class OrgUserAndDeptServiceImpl implements OrgUserAndDeptService {

    @Autowired
    private DepartmentsMapper departsMapper;

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 查询组织架构树
     *
     * @param deptId    部门id
     * @param isDept    只查询部门架构
     * @param showLeave 是否显示离职员工
     * @return 组织架构树数据
     */
    @Override
    public Object getOrgTreeData(Integer deptId, Boolean isDept, Boolean showLeave) {
        //查询所有部门及员工
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        if (!showLeave) {
            criteria.andIsNull("leaveDate");
        }
        if (!ObjectUtils.isEmpty(deptId)) {
            Example exampleDept = new Example(Departments.class);
            exampleDept.createCriteria().andEqualTo("parentId", deptId);
            Example.Criteria orLike = example.createCriteria()
                    .andLike("departmentIds", "%" + deptId + "%")
                    .orLike("departmentIds", "%" + deptId + ",%")
                    .orLike("departmentIds", "%," + deptId + "%");
            example.and(orLike);
            List<OrgTreeVo> orgTreeVos = new LinkedList<>();
            departsMapper.selectByExample(exampleDept).forEach(dept -> {
                orgTreeVos.add(OrgTreeVo.builder()
                        .id(dept.getDeptId())
                        .name(dept.getDeptName())
                        .selected(false)
                        .type("dept")
                        .build());
            });
            usersMapper.selectByExample(example).forEach(user -> {
                orgTreeVos.add(OrgTreeVo.builder()
                        .id(user.getUserId())
                        .name(user.getUserName())
                        .avatar(user.getAvatar())
                        .sex(user.getSex())
                        .type("user")
                        .selected(false)
                        .build());
            });
            return R.ok(orgTreeVos);
        }
        List<Users> users = usersMapper.selectByExample(example);
        List<Departments> departments = departsMapper.selectAll();
        //将人员按部门归类分组
        Map<Long, List<Users>> deptUsers = new HashMap<>();
        users.forEach(user -> {
            for (String did : user.getDepartmentIds().split(",")) {
                List<Users> usersList = deptUsers.get(Long.parseLong(did));
                if (null == usersList) {
                    List<Users> list = new ArrayList<>();
                    list.add(user);
                } else {
                    usersList.add(user);
                }
            }
        });
        //将部门及员工进行转换嵌套树形结构
        departments.forEach(dept -> {

        });
        return R.ok(Collections.EMPTY_LIST);
    }

    /**
     * 模糊搜索用户
     *
     * @param userName 用户名/拼音/首字母
     * @return 匹配到的用户
     */
    @Override
    public Object getOrgTreeUser(String userName) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria().andIsNull("leaveDate");
        if (Validator.isChinese(userName)) {
            criteria.andLike("userName", "%" + userName + "%");
        } else {
            criteria.andLike("pingyin", "%" + userName + "%");
        }
        List<OrgTreeVo> list = new LinkedList<>();
        usersMapper.selectByExample(example).forEach(user -> {
            list.add(OrgTreeVo.builder().type("user")
                    .sex(user.getSex())
                    .avatar(user.getAvatar())
                    .name(user.getUserName())
                    .id(user.getUserId())
                    .selected(false).build());
        });
        return R.ok(list);
    }
}
