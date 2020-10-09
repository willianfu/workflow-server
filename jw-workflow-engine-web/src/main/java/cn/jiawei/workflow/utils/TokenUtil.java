package cn.jiawei.workflow.utils;

import cn.jiawei.workflow.bean.entity.Users;
import cn.jiawei.workflow.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : willian fu
 * @date : 2020/9/21
 */
@Component
public class TokenUtil {

    @Autowired
    private UsersMapper usersMapper;

    public Users getUserByToken(String token){
        return usersMapper.selectByPrimaryKey(Long.parseLong(token));
    }
}
