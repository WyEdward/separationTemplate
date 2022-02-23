package cn.wyedward;

import cn.wyedward.core.entity.User;
import cn.wyedward.core.entity.UserInfo;
import cn.wyedward.core.mapper.UserInfoMapper;
import cn.wyedward.core.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Test
    public void test(){
        System.out.println("test");
    }

    /**
     * 测试User分页查询
     */
    @Test
    public void testPage(){
        IPage<User> userPage = new Page<>(1, 2);//参数一是当前页，参数二是每页个数
        userPage = userMapper.selectPage(userPage, null);
        List<User> list = userPage.getRecords();
        System.out.println(list);
    }

    /**
     * 往user_info里插入一条数据
     */
    @Test
    public void testInsert(){
        UserInfo u = new UserInfo();
        u.setUserName("j");
        u.setPassWord("123456");
        u.setAge(22);
        userInfoMapper.insert(u);
    }

    /**
     * user_info更新一条数据
     */
    @Test
    public void testUpdate(){
        UserInfo userInfo = new UserInfo();
        Long l = new Long("1496315034551222274");
        userInfo.setId(l);
        userInfo.setUserName("w");
        userInfoMapper.updateById(userInfo);
    }

    @Test
    public void testDelete(){

    }
}
