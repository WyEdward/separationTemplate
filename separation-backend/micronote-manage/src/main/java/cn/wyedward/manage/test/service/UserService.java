package cn.wyedward.manage.test.service;

import cn.wyedward.core.entity.UserDo;

import java.util.List;

public interface UserService {
    //获取用户列表
    List<UserDo> listUsers();
}