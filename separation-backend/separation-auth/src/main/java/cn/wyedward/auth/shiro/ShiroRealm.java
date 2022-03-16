package cn.wyedward.auth.shiro;

import cn.wyedward.auth.jwt.JwtToken;
import cn.wyedward.core.utils.JwtUtil;
import cn.wyedward.core.entity.sys.Permission;
import cn.wyedward.core.entity.sys.Role;
import cn.wyedward.core.entity.sys.User;
import cn.wyedward.core.mapper.sys.PermissionMapper;
import cn.wyedward.core.mapper.sys.RoleMapper;
import cn.wyedward.core.mapper.sys.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

public class ShiroRealm extends AuthorizingRealm{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
    /**
     * 角色和权限验证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //User user = (User) SecurityUtils.getSubject().getPrincipal();
        //String userName = user.getUserName();
        String userName = JwtUtil.getUsername(principalCollection.toString());
        //System.out.println("用户" + userName + "获取角色权限-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取用户角色集
        List<Role> roleList = roleMapper.findByUserName(userName);
        //System.out.println("角色集合");
        //System.out.println(roleList);
        HashSet<String> roleSet = new HashSet<>();
        for (Role r : roleList) {
            roleSet.add(r.getRoleName());
        }
        simpleAuthorizationInfo.setRoles(roleSet);
       // System.out.println("用户" + userName + "获取角色");
        //获取用户权限集
        List<Permission> permissionList = permissionMapper.findByUserName(userName);
        HashSet<String> permissionSet = new HashSet<>();
        for (Permission p : permissionList) {
            permissionSet.add(p.getPermissionName());
        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        //System.out.println("用户" + userName + "获取权限");
        //System.out.println(roleSet);
        //System.out.println(permissionSet);
        return simpleAuthorizationInfo;
    }


    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //String username =(String)authenticationToken.getPrincipal();
        //String password = new String((char[]) authenticationToken.getCredentials());
        String token = (String) authenticationToken.getCredentials();
        //System.out.println("token：");
        //System.out.println(token);
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        //System.out.println("username：");
        //System.out.println(username);
        //System.out.println("用户" + username + "认证-----ShiroRealm.doGetAuthenticationInfo");
        if (username == null || !JwtUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
        }
        //利用mybatis-plus的语法进行查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        queryWrapper.eq(User::getUserEnable, 0); //没有被删除的
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new AuthenticationException("用户不存在！");
        }
        if (user.getStatus() == '0') {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token, token, "MyRealm");
        return info;
    }
}
