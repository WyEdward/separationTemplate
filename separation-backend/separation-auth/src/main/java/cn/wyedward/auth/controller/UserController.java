package cn.wyedward.auth.controller;

import cn.wyedward.auth.service.UserRoleService;
import cn.wyedward.auth.service.UserService;
import cn.wyedward.auth.utils.MD5Utils;
import cn.wyedward.core.common.ResponseBo;
import cn.wyedward.core.entity.sys.User;
import cn.wyedward.core.entity.sys.UserRole;
import cn.wyedward.core.entity.sys.vo.UserAddRoleVo;
import cn.wyedward.core.entity.sys.vo.UserVo;
import cn.wyedward.core.utils.SnowflakeIdUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;
    /**
     * 分页查询
     * @return
     */
    @ApiOperation(value = "分页查询用户")
    @PostMapping("/listByPage")
    @Transactional
    public ResponseBo listByPage(@RequestBody UserVo userVo){
        //定义查询器
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        if(!"".equals(userVo.getDataForm().getUserName())){
            queryWrapper.like(User::getUserName, userVo.getDataForm().getUserName());
        }
        //当前页的页号, 和 每页显示的页数  查询条件
        IPage<User> page = userService.page(new Page<>(userVo.getCurrPage(), userVo.getPageSize()), queryWrapper);
        //定义返回对象
        ResponseBo bo = new ResponseBo();
        bo.put("record", page.getRecords());
        bo.put("total", page.getTotal());
        bo.put("pages", page.getPages());
        bo.put("current", page.getCurrent());
        bo.put("size", page.getSize());
        return bo;
    }

    @ApiOperation(value = "创建更新用户")
    @PostMapping("/insertOrUpdate")
    @Transactional
    public ResponseBo insertOrUpdate(@RequestBody UserAddRoleVo userAddRoleVo){
        User user = new User();
        user.setUserName(userAddRoleVo.getUserName());
        user.setUserNick(userAddRoleVo.getUserNick());
        if(userAddRoleVo.getUserId() == null || "0".equals(String.valueOf(userAddRoleVo.getUserId()))){
            String password = MD5Utils.encrypt(user.getUserName(), "123456");//设置默认密码为123456
            user.setPassword(password); //设置密码
            SnowflakeIdUtils idWorker = new SnowflakeIdUtils(1, 1); //雪花生成全局唯一id
            user.setUserUniqueId(idWorker.nextId());
            //user.setCreateTime(DateUtils.parse(new Date(), "yyyy-MM-dd HH:mm:ss"));
            user.setCreateTime(new Date());
            boolean result = userService.save(user);

            ArrayList<Integer> roleList = userAddRoleVo.getRoleList();
            //设置用户角色中间表联系
            for(int i = 0; i < roleList.size(); i++){
                UserRole userRole = new UserRole(user.getUserId(), roleList.get(i));
                boolean res = userRoleService.save(userRole);
                if(!res){
                    return ResponseBo.error("中间数据创建失败");
                }
            }
            if(result){
                return ResponseBo.ok("创建成功");
            }else{
                return ResponseBo.error("创建失败");
            }
        }else{
            user.setUserId(userAddRoleVo.getUserId());
            //user.setUpdateTime(DateUtils.parse(new Date(), "yyyy-MM-dd HH:mm:ss"));
            user.setUpdateTime(new Date());
            boolean result = userService.updateById(user);
            //定义查询器
            LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserRole::getUid, user.getUserId());
            userRoleService.remove(queryWrapper);       //移除中间表中之前的关联数据

            ArrayList<Integer> roleList = userAddRoleVo.getRoleList();
            //设置用户角色中间表联系
            for(int i = 0; i < roleList.size(); i++){
                UserRole userRole = new UserRole(user.getUserId(), roleList.get(i));
                boolean res = userRoleService.save(userRole);
                if(!res){
                    return ResponseBo.error("中间数据创建失败");
                }
            }
            if(result){
                return ResponseBo.ok("更新成功");
            }else{
                return ResponseBo.error("更新失败");
            }
        }
    }

    @PostMapping("/remove")
    public ResponseBo remove(@RequestParam("userId") Integer userId){
        boolean res = userService.removeById(userId);
        if(res){
            return new ResponseBo();
        }else{
            return ResponseBo.error();
        }
    }

    @PostMapping("/removes")
    @Transactional
    public ResponseBo removes(@RequestBody List<Integer> userIds){
        boolean res = userService.removeByIds(userIds);
        if(res){
            return new ResponseBo();
        }else{
            return ResponseBo.error();
        }
    }

    @PostMapping("/queryGroupByLike")
    @Transactional
    public ResponseBo queryGroupByLike(@RequestParam("queryString") String queryString){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(User::getUserName, queryString);
        IPage<User> page =  userService.page(new Page<>( 1, 10), queryWrapper);
        ResponseBo bo = new ResponseBo();
        bo.put("record", page.getRecords());
        return bo;
    }
}
