package cn.wyedward.auth.controller;

import cn.wyedward.auth.service.PermissionService;
import cn.wyedward.auth.service.RoleService;
import cn.wyedward.auth.service.UserService;
import cn.wyedward.auth.utils.JwtUtil;
import cn.wyedward.auth.utils.MD5Utils;
import cn.wyedward.core.common.ResponseBo;
import cn.wyedward.core.entity.sys.Permission;
import cn.wyedward.core.entity.sys.Role;
import cn.wyedward.core.entity.sys.User;
import cn.wyedward.core.entity.sys.dto.RoleDto;
import cn.wyedward.core.entity.sys.dto.UserDto;
import cn.wyedward.core.entity.sys.vo.UserLoginVo;
import cn.wyedward.core.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Api(value = "用户登录Controller")
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "用户登录", notes = "后台用户登录")
    @PostMapping("/login")
    @RequiresGuest
    public ResponseBo login(@RequestBody UserLoginVo userVo){
        //获取参数
        String username = userVo.getUserName();
        String password = userVo.getPassword();
        Boolean rememberMe = userVo.getRememberMe();
        //密码MD5加密
        password = MD5Utils.encrypt(username, password);
        //利用mybatis-plus的语法进行查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        queryWrapper.eq(User::getUserEnable, 0);
        User user = userService.getOne(queryWrapper);
        //获取数据库中用户密码
        //String realPassword= userService.getPasswordByUserName(username);
        String realPassword = user.getPassword();
        try {
            if (realPassword == null) {
                return ResponseBo.error("用户名或密码错误！");
            }else if (!realPassword.equals(password)) {
                return ResponseBo.error("用户名或密码错误！");
            }else {
                ResponseBo responseBo = new ResponseBo();
                Long currentTimeMillis = System.currentTimeMillis();
                String token = JwtUtil.createToken(username, currentTimeMillis);
                responseBo.put("token", token);

                List<Permission> permissionList = null;

                int flag = 0; //判断有没有从缓存拿去数据
                //根据用户名去获取用户的角色集合
                List<Role> roles = roleService.findByUserName(username);
                for(int i = 0; i < roles.size(); i++){
                    int roleIdNum = roles.get(i).getRoleId();
                    //如果redis 有 key
                    if(!RedisUtil.hasKey("roleId:"+ roleIdNum)){
                        flag = 1;
                    }
                }
                //没进缓存
                if(flag == 1){
                    //查用户的角色集合 然后redis存角色的权限集合。
                    //搜索用户权限 老 permissionList = permissionService.findByUserName(username);
                    //权限在redis里面保存的时间为5天
                    //RedisUtil.set("Permission:"+username, permissionList,60 * 60 * 24 * 5);
                    UserDto userDto = userService.getUserRolePermission(username);
                    List<Role> roleList = userDto.getRoleList();
                    for(int i = 0; i < roleList.size(); i++){
                        int roleId = roleList.get(i).getRoleId();
                        RoleDto dtoById = roleService.findDtoById(roleId);
                        List<Permission> p = dtoById.getPermissionList();
                        //存角色的权限
                        RedisUtil.set("roleId:"+roleId,  p,60 * 60 * 24 * 5);
                    }
                    //权限就用dto查找的权限集合
                   permissionList = userDto.getPermissionList();
                }else{
                    permissionList = new ArrayList<Permission>();
                    for(int i = 0; i < roles.size(); i++){
                        int roleIdNum = roles.get(i).getRoleId();
                        if(RedisUtil.hasKey("roleId:"+ roleIdNum)){
                            List<Permission> p = (List<Permission>) RedisUtil.get("roleId:"+roleIdNum);
                            for(int j = 0; j < p.size(); j++){
                                permissionList.add(p.get(j));
                            }
                        }
                    }
                }
                responseBo.put("permissionList", permissionList);
                //User user = userService.getUserIdAndNickByUserName(username);
                responseBo.put("userId", user.getUserId());
                responseBo.put("nickName", user.getUserNick());
                //添加验证AccessToken过期 刷新FreshToken
                RedisUtil.set("freshKey:"+username,currentTimeMillis, JwtUtil.REFRESH_EXPIRE_TIME);
                //response.setHeader("Authorization", token);
                //response.setHeader("Access-Control-Expose-Headers", "Authorization");
                return responseBo;
            }
        } catch (UnknownAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResponseBo.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBo.error("认证失败！");
        }
    }

    @ApiOperation(value = "根据用户名获取用户信息")
    @PostMapping("/getUser")
    @Transactional
    //@RequiresPermissions("user:list")
    public UserDto getUserByName(@RequestParam("userName") String userName){
        return userService.getUserRolePermission(userName);
    }
}
