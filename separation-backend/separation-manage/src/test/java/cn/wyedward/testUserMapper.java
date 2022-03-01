package cn.wyedward;

import cn.wyedward.core.entity.sys.User;
import cn.wyedward.core.mapper.sys.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testUserMapper {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void listUser(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }
}
