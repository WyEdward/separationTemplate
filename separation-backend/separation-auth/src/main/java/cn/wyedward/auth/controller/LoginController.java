package cn.wyedward.auth.controller;

import cn.wyedward.auth.service.PermissionService;
import cn.wyedward.auth.service.UserService;
import cn.wyedward.auth.utils.JwtUtil;
import cn.wyedward.auth.utils.MD5Utils;
import cn.wyedward.core.common.ResponseBo;
import cn.wyedward.core.entity.sys.Permission;
import cn.wyedward.core.entity.sys.User;
import cn.wyedward.core.entity.sys.vo.UserLoginVo;
import cn.wyedward.core.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "用户登录", notes = "后台用户登录")
    @PostMapping("/login")
    @RequiresGuest
    @ResponseBody
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
                if(RedisUtil.hasKey("Permission:"+username)){
                    permissionList = (List<Permission>) RedisUtil.get("Permission:"+username);
                }else{
                    //搜索权限
                    permissionList = permissionService.findByUserName(username);
                    //权限在redis里面保存的时间为5天
                    RedisUtil.set("Permission:"+username, permissionList,60 * 60 * 24 * 5);
                }
                responseBo.put("permissionList", permissionList);
                //User user = userService.getUserIdAndNickByUserName(username);
                responseBo.put("userId", user.getUserId());
                responseBo.put("nickrName", user.getUserNick());
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

}
