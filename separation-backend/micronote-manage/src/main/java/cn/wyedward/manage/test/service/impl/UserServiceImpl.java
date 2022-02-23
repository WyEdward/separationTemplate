package cn.wyedward.manage.test.service.impl;


import cn.wyedward.core.entity.User;
import cn.wyedward.core.mapper.UserMapper;
import cn.wyedward.manage.test.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        return userMapper.listUsers();
    }

    @Override
    public List<User> listUsersPage() {
        IPage<User> userPage = new Page<>(1, 2);//参数一是当前页，参数二是每页个数
        userPage = userMapper.selectPage(userPage, null);
        List<User> list = userPage.getRecords();
        return list;
    }


}
