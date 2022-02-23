package cn.wyedward.manage.test.service;

import cn.wyedward.core.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserService extends IService<User>{
    //获取用户列表
    List<User> listUsers();
    //获取分页
    List<User> listUsersPage();

}