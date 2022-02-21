package cn.wyedward.manage.test.service.impl;


import cn.wyedward.core.entity.UserDo;
import cn.wyedward.core.mapper.UserMapper;
import cn.wyedward.manage.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDo> listUsers() {
        return userMapper.listUsers();
    }
}
