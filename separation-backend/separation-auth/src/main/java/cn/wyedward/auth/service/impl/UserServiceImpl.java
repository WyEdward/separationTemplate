package cn.wyedward.auth.service.impl;

import cn.wyedward.auth.service.UserService;
import cn.wyedward.core.entity.common.PageBean;
import cn.wyedward.core.entity.common.PageQueryWrapper;
import cn.wyedward.core.entity.sys.User;
import cn.wyedward.core.entity.sys.dto.UserDto;
import cn.wyedward.core.mapper.sys.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto getUserRolePermission(String userName) {
        return userMapper.getUserRolePermission(userName);
    }

    @Override
    public ArrayList<UserDto> listDtoByPage(PageBean<UserDto> pageBean, PageQueryWrapper<UserDto> wrapper) {
        return userMapper.getUserRolePermissionByPage(pageBean.getStart(), pageBean.getEnd(), wrapper);
    }

    @Override
    public Integer countDto(PageQueryWrapper<UserDto> wrapper) {
        return userMapper.countDto(wrapper);
    }

    @Override
    public Integer resetPassword(String userName, String newPassword) {
        return userMapper.resetPassword(userName, newPassword);
    }

    @Override
    public String getPasswordByUserName(String userName) {
        return userMapper.getPasswordByUserName(userName);
    }

}
