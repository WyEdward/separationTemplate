package cn.wyedward.auth.controller;

import cn.wyedward.auth.service.RolePermissionService;
import cn.wyedward.auth.service.RoleService;
import cn.wyedward.auth.service.UserRoleService;
import cn.wyedward.core.common.ResponseBo;
import cn.wyedward.core.entity.sys.Permission;
import cn.wyedward.core.entity.sys.Role;
import cn.wyedward.core.entity.sys.RolePermission;
import cn.wyedward.core.entity.sys.UserRole;
import cn.wyedward.core.entity.sys.dto.RoleDto;
import cn.wyedward.core.entity.sys.vo.RoleAddPermissionVo;
import cn.wyedward.core.entity.sys.vo.RoleVo;
import cn.wyedward.core.utils.RedisUtil;
import cn.wyedward.core.utils.SnowflakeIdUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController("roleController")
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private UserRoleService userRoleService;
    /**
     * 分页查询
     * @return
     */
    @ApiOperation(value = "分页查询项目")
    @PostMapping("/listByPage")
    @Transactional
    public ResponseBo listByPage(@RequestBody RoleVo roleVo){
        //定义查询器
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        if(!"".equals(roleVo.getDataForm().getRoleName())){
            queryWrapper.like(Role::getRoleName, roleVo.getDataForm().getRoleName());
        }
        //当前页的页号, 和 每页显示的页数  查询条件
        IPage<Role> page = roleService.page(new Page<>(roleVo.getCurrPage(), roleVo.getPageSize()), queryWrapper);
        //定义返回对象
        ResponseBo bo = new ResponseBo();
        bo.put("record", page.getRecords());
        bo.put("total", page.getTotal());
        bo.put("pages", page.getPages());
        bo.put("current", page.getCurrent());
        bo.put("size", page.getSize());
        return bo;
    }

    @ApiOperation(value = "创建更新项目")
    @PostMapping("/insertOrUpdate")
    @Transactional
    public ResponseBo insertOrUpdate(@RequestBody RoleAddPermissionVo roleAddPermissionVo){
        Role role = new Role();
        role.setRoleDescription(roleAddPermissionVo.getRoleDescription());
        role.setRoleName(roleAddPermissionVo.getRoleName());
        if(roleAddPermissionVo.getRoleId() == null || "0".equals(String.valueOf(roleAddPermissionVo.getRoleId())) ){
            SnowflakeIdUtils idWorker = new SnowflakeIdUtils(1, 1); //雪花生成全局唯一id
            role.setRoleUniqueId(idWorker.nextId());
            //role.setCreateTime(DateUtils.parse(new Date(), "yyyy-MM-dd HH:mm:ss"));;
            boolean result = roleService.save(role); //增加角色

            //修改角色 权限中间表开始
            List<Integer> pList = roleAddPermissionVo.getTreeList();
            List<RolePermission> rpList= new ArrayList<>();
            for(int i = 0; i < pList.size(); i++){
                RolePermission rolePermission = new RolePermission(role.getRoleId(), pList.get(i));
                rpList.add(rolePermission);
            }
            boolean res = rolePermissionService.saveBatch(rpList); //角色关联权限表
            //修改角色权限中间表结束

            if(result && res){
                return ResponseBo.ok("创建成功");
            }else{
                return ResponseBo.error("创建失败");
            }
        }else{
            role.setRoleId(roleAddPermissionVo.getRoleId());
            boolean result = roleService.updateById(role); //修改角色表

            //把之前角色相关的权限记录全部删除
            //定义查询器
            LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
            //查询条件
            queryWrapper.eq(RolePermission::getRid, role.getRoleId());
            boolean removeRes= rolePermissionService.remove(queryWrapper);

            //再重新增加角色权限表
            List<Integer> pList = roleAddPermissionVo.getTreeList();
            List<RolePermission> rpList= new ArrayList<>();
            for(int i = 0; i < pList.size(); i++){
                RolePermission rolePermission = new RolePermission(role.getRoleId(), pList.get(i));
                rpList.add(rolePermission);
            }
            boolean res = rolePermissionService.saveBatch(rpList); //角色关联权限表
            RedisUtil.del("roleId:"+ role.getRoleId());
            if(result && removeRes && res){
                return ResponseBo.ok("更新成功");
            }else{
                return ResponseBo.error("更新失败");
            }
        }
    }

    @PostMapping("/remove")
    @Transactional
    public ResponseBo remove(@RequestParam("roleId") Integer roleId){
        //定义userRole查询器  查看用户角色下有没有绑定信息
        LambdaQueryWrapper<UserRole> queryUrWrapper = new LambdaQueryWrapper<>();
        //查询条件
        queryUrWrapper.eq(UserRole::getRid, roleId);
        if(userRoleService.count(queryUrWrapper) > 0){
            return ResponseBo.error("该角色下有绑定用户，请先将用户解绑角色");
        }
        boolean res = roleService.removeById(roleId);
        //把之前角色相关的权限记录全部删除
        //定义RolerPermission查询器
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        queryWrapper.eq(RolePermission::getRid, roleId);
        boolean removeRes= rolePermissionService.remove(queryWrapper);
        RedisUtil.del("roleId:"+roleId);
        if(res && removeRes){
            return new ResponseBo();
        }else{
            return ResponseBo.error();
        }
    }

    @PostMapping("/removes")
    @Transactional
    public ResponseBo removes(@RequestBody List<Integer> roleIds){
        for(int i = 0; i < roleIds.size(); i++){
            //定义userRole查询器  查看用户角色下有没有绑定信息
            LambdaQueryWrapper<UserRole> queryUrWrapper = new LambdaQueryWrapper<>();
            //查询条件
            queryUrWrapper.eq(UserRole::getRid, roleIds.get(i));
            if(userRoleService.count(queryUrWrapper) > 0){
                return ResponseBo.error("该角色下有绑定用户，请先将用户解绑角色");
            }
        }
        boolean res = roleService.removeByIds(roleIds);
        for(int i = 0; i < roleIds.size(); i++){
            //定义查询器
            LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
            //查询条件
            queryWrapper.eq(RolePermission::getRid, roleIds.get(i));
            RedisUtil.del("roleId:"+roleIds.get(i));
            boolean removeRes = rolePermissionService.remove(queryWrapper);
            if(!removeRes){
                return ResponseBo.error();
            }
        }
        if(res){
            return new ResponseBo();
        }else{
            return ResponseBo.error();
        }
    }

    @PostMapping("/queryGroupByLike")
    @Transactional
    public ResponseBo queryGroupByLike(@RequestParam("queryString") String queryString){
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Role::getRoleName, queryString);
        IPage<Role> page =  roleService.page(new Page<>( 1, 10), queryWrapper);
        ResponseBo bo = new ResponseBo();
        bo.put("record", page.getRecords());
        return bo;
    }

    /**
     * 根据角色id 查询权限数组对象
     * @param roleId
     * @return
     */
    @PostMapping("/listTreeByRoleId")
    @Transactional
    public ResponseBo listTreeByRoleId(@RequestParam("roleId") Integer roleId){
        //查询dto
        RoleDto dtoList= roleService.findDtoById(roleId);
        //设置返回对象
        ResponseBo bo = new ResponseBo();
        bo.put("lists", dtoList.getPermissionList());
        return bo;
    }

    /**
     * 根据角色id查询角色权限id数组
     * @param roleId
     * @return
     */
    @PostMapping("/listPermissionByRoleId")
    @Transactional
    public ResponseBo listPermissionByRoleId(@RequestParam("roleId") Integer roleId){
        //查询dto
        RoleDto dtoList= roleService.findDtoById(roleId);
        List<Permission> permissionList = dtoList.getPermissionList();
        ArrayList<Integer> lists = new ArrayList<>();
        int flag; //表示是否为叶子节点
        for(int i = 0; i < permissionList.size(); i++){
            flag = 1;
            int currentId = permissionList.get(i).getPermissionId();
            for(int j = 0; j < permissionList.size(); j++){
                if(currentId == permissionList.get(j).getPermissionFid()){
                    flag = 0;
                    break;
                }
            }
            if(flag == 1){
                //只讲叶子节点加到默认选中数组中，防止父节点选中，叶节点全部选中(但实际我们只想要一些叶子节点选中)
                lists.add(currentId);
            }
        }
        //设置返回对象
        ResponseBo bo = new ResponseBo();
        bo.put("lists", lists);
        return bo;
    }
}
