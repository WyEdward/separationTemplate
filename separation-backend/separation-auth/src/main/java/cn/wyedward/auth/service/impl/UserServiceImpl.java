package cn.wyedward.auth.service.impl;

import cn.wyedward.auth.service.UserService;
import cn.wyedward.core.entity.sys.User;
import cn.wyedward.core.entity.sys.dto.UserDto;
import cn.wyedward.core.mapper.sys.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto getUserRolePermission(String userName) {
        return userMapper.getUserRolePermission(userName);
    }
}
