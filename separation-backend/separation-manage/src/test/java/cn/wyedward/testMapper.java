/*
package cn.wyedward;

import cn.wyedward.core.entity.UserInfo;
import cn.wyedward.core.entity.UserRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testMapper {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    */
/**
     * 测试自定义xml形式查询
     * 测试 user->role 一对一关联
     *//*

    @Test
    public void testXmlList(){
        List<UserInfo> userInfos = userInfoMapper.testXmlList();
        System.out.println(userInfos);
    }

    */
/**
     * 测试一对多
     *//*

    @Test
    public void testXmlOneToDuo(){
        UserRole userRoleById = userRoleMapper.getUserRoleById(1);
        System.out.println(userRoleById);
    }

    */
/**
     * User_Info测试根据id查询一条记录
     *//*

    @Test
    public void testSelectById(){
        UserInfo user = userInfoMapper.selectById(new Long("1496315034551222274"));
        System.out.println(user);
    }

    */
/**
     * 使用查询构造器，查询一条记录
     * 是数据库中符合传入条件的记录有多条，那就不能用这个方法，会报错。
     *//*

    @Test
    public void testSelectOne(){
        // 查询条件：名字中包含'ha'并且年龄小于40
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getAge, 24);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        System.out.println(userInfo);
    }

    */
/**
     * 测试 根据ID批量查询，返回一个List
     *//*

    @Test
    public void testSelectBatchIds(){
        List<Long> ids = new ArrayList<>();
        ids.add(new Long("1496315034551222274"));
        ids.add(new Long("1496322182182150145"));
        List<UserInfo> users = userInfoMapper.selectBatchIds(ids);
        System.out.println(users);
    }

    */
/**
     * 测试 根据Map封装的条件查询，返回一个List
     *//*

    @Test
    public void testSelectByMap(){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("user_name", "w");
        List<UserInfo> users = userInfoMapper.selectByMap(columnMap);
        System.out.println(users);
    }

    */
/**
     * 使用查询构造器，返回一个List
     * 模糊查询用户名带i的用户
     *//*

    @Test
    public void testSelectList(){
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(UserInfo::getUserName,"i");
        List<UserInfo> userInfos = userInfoMapper.selectList(queryWrapper);
        System.out.println(userInfos);
    }

    */
/**
     * 使用查询构造器，返回一个List<map>
     *//*

    @Test
    public void testSelectMaps(){
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(UserInfo::getUserName,"i");
        List<Map<String, Object>> userInfos = userInfoMapper.selectMaps(queryWrapper);
        System.out.println(userInfos);
    }

    */
/**
     * 使用查询构造器，返回一个List<object>
     *  List 里面只有返回的第一个字段值：
     *//*

    @Test
    public void testSelectObjs(){
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(UserInfo::getUserName,"i");
        List<Object> userInfos = userInfoMapper.selectObjs(queryWrapper);
        System.out.println(userInfos);
    }

    */
/**
     * 使用查询构造器，查询总记录数
     *//*

    @Test
    public void testSelectCount(){
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(UserInfo::getUserName,"i");
        Integer count = userInfoMapper.selectCount(queryWrapper);
        System.out.println(count);
    }

    */
/**
     * 测试UserInfo分页
     *//*

    @Test
    public void testUserInfoPage(){
        IPage<UserInfo> userInfoPage = new Page<>(1, 2);//参数一是当前页，参数二是每页个数
        userInfoPage = userInfoMapper.selectPage(userInfoPage, null);
        List<UserInfo> list = userInfoPage.getRecords();
        System.out.println(list);
    }

    */
/**
     * 测试UserInfo有条件分页
     * 查询年纪为23的所有分页数据
     *//*

    @Test
    public void testUserInfoPageByConditions(){
        //分页对象
        IPage<UserInfo> userInfoPage = new Page<>(1, 2);//参数一是当前页，参数二是每页个数
        //lanbda查询条件构造器
        LambdaQueryWrapper<UserInfo> UserInfoPageWrapper = new LambdaQueryWrapper<>();
        //查询条件
        Map<SFunction<UserInfo, ?>, Object> map = new HashMap<>();
        map.put(UserInfo::getAge, "23");
        //将查询条件传入查询构造器中
        UserInfoPageWrapper.allEq(map);
        //查询年纪为23的所有分页数据
        userInfoPage = userInfoMapper.selectPage(userInfoPage, UserInfoPageWrapper);
        List<UserInfo> list = userInfoPage.getRecords();
        System.out.println(list);
    }


    */
/**
     * 往user_info里插入一条数据
     *//*

    @Test
    public void testInsert(){
        UserInfo u = new UserInfo();
        u.setUserName("iiiii");
        u.setPassWord("123456");
        u.setAge(24);
        int l = userInfoMapper.insert(u);
        System.out.println(l);
        System.out.println(u.getId());
    }

    */
/**
     * user_info更新一条数据
     *//*

    @Test
    public void testUpdate(){
        UserInfo userInfo = new UserInfo();
        Long l = new Long("1496315034551222274");
        userInfo.setId(l);
        userInfo.setUserName("w");
        userInfoMapper.updateById(userInfo);
    }

    */
/**
     * 测试User_Info有条件更新
     * 写法1
     * 将用户名有i的 模糊查询 然后修改其年龄为24
     *//*

    @Test
    public void testUpdateByCondition(){
        //条件查询器
        LambdaUpdateWrapper<UserInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(UserInfo::getUserName,"i");
        //需要更新的数据
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(24);
        int i = userInfoMapper.update(userInfo, updateWrapper);
        System.out.println(i);
    }

    */
/**
     * 测试User_Info有条件更新
     * 写法2
     * 将用户名有i的 模糊查询 然后修改其年龄为24
     *//*

    @Test
    public void testUpdateByCondition2(){
        //条件查询器  并且设置要更改的值
        LambdaUpdateWrapper<UserInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(UserInfo::getUserName,"i").set(UserInfo::getAge, 23);
        int i = userInfoMapper.update(null, updateWrapper);
        System.out.println(i);
    }


    */
/**
     * 根据entity条件，删除记录
     *//*

    @Test
    public void testDeleteById(){
        */
/*LambdaQueryWrapper<Object> queryWrapper = new LambdaQueryWrapper<>();
        Long L = new Long("1496322448520413185");*//*

        int i = userInfoMapper.deleteById("1496322448520413185");
        System.out.println(i);

    }

    */
/**
     * 批量删除
     *//*

    @Test
    public void testDeleteBatchIds(){
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(new Long("1496334276331958274"));
        ids.add(new Long("1496333648952090625"));
        int i = userInfoMapper.deleteBatchIds(ids);
        System.out.println(i);

    }

    */
/**
     * 根据Map封装的内容进行删除
     * 删除age为24岁的数据
     *//*

    @Test
    public void testDeleteByMap(){
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("age", 24);
        int i = userInfoMapper.deleteByMap(columnMap);
        System.out.println(i);

    }

    */
/**
     * 有条件的删除
     * 删除年龄为24岁的
     *//*

    @Test
    public void testDeleteByCondition(){
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getAge, 24);
        int i = userInfoMapper.delete(queryWrapper);
        System.out.println(i);
    }
}
*/
