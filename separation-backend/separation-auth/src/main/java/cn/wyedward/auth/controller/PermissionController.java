package cn.wyedward.auth.controller;

import cn.wyedward.auth.service.PermissionService;
import cn.wyedward.auth.service.RolePermissionService;
import cn.wyedward.core.common.ResponseBo;
import cn.wyedward.core.entity.common.PageBean;
import cn.wyedward.core.entity.common.PageQueryWrapper;
import cn.wyedward.core.entity.sys.Permission;
import cn.wyedward.core.entity.sys.RolePermission;
import cn.wyedward.core.entity.sys.UserRole;
import cn.wyedward.core.entity.sys.dto.PermissionDto;
import cn.wyedward.core.entity.sys.vo.PermissionVo;
import cn.wyedward.core.utils.SnowflakeIdUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/admin/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;
    /**
     * 分页查询
     * @return
     */
    @ApiOperation(value = "分页查询权限")
    @PostMapping("/listByPage")
    @Transactional
    public ResponseBo listByPage(@RequestBody PermissionVo permissionVo){
        //定义查询器
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        if(!"".equals(permissionVo.getDataForm().getPermissionName())){
            queryWrapper.like(Permission::getPermissionName, permissionVo.getDataForm().getPermissionName());
        }
        //当前页的页号, 和 每页显示的页数  查询条件
        IPage<Permission> page = permissionService.page(new Page<>(permissionVo.getCurrPage(), permissionVo.getPageSize()), queryWrapper);
        //定义返回对象
        ResponseBo bo = new ResponseBo();
        bo.put("record", page.getRecords());
        bo.put("total", page.getTotal());
        bo.put("pages", page.getPages());
        bo.put("current", page.getCurrent());
        bo.put("size", page.getSize());
        return bo;
    }

    /**
     * 查询搜索下拉框
     * @param queryString
     * @return
     */
    @PostMapping("/queryGroupByLike")
    @Transactional
    public ResponseBo queryGroupByLike(@RequestParam("queryString") String queryString){
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Permission::getPermissionName, queryString);
        IPage<Permission> page =  permissionService.page(new Page<>( 1, 10), queryWrapper);
        ResponseBo bo = new ResponseBo();
        bo.put("record", page.getRecords());
        return bo;
    }

    @PostMapping("/lists")
    @Transactional
    public ResponseBo lists(){
        List<Permission> lists = permissionService.list();
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("lists", lists);
        return responseBo;
    }

    /**
     * 查询dto的数据 分页
     * @param permissionVo
     * @return
     */
    @PostMapping("/listDtoByPage")
    public ResponseBo listDtoByPage(@RequestBody PermissionVo permissionVo){
        //定义自定义查询器
        PageQueryWrapper<PermissionDto> wrapper = new PageQueryWrapper<PermissionDto>();
        if(permissionVo.getDataForm().getPermissionName() != null){  //如果参数有传权限名称 则
            wrapper.put("permissionName", permissionVo.getDataForm().getPermissionName());
        }
        //设置自定义分页器  传当前页 和 一页展示多少数
        PageBean<PermissionDto> dtoPageBean = new PageBean<>(permissionVo.getCurrPage(), permissionVo.getPageSize());
        List<PermissionDto> dtoList = permissionService.listDtoByPage(dtoPageBean, wrapper); //返回查询结果集合
        Integer total = permissionService.countDto(wrapper);  //返回条数
        //定义返回对象
        ResponseBo bo = new ResponseBo();
        bo.put("record",dtoList);
        bo.put("total", total);
        return bo;
    }

    @ApiOperation(value = "创建更新项目")
    @PostMapping("/insertOrUpdate")
    @Transactional
    public ResponseBo insertOrUpdate(@RequestBody Permission permission){
        if(permission.getPermissionId() == null || "0".equals(String.valueOf(permission.getPermissionId())) || "null".equals(String.valueOf(permission.getPermissionId()))){
            //permission.setCreateTime(DateUtils.parse(new Date(), "yyyy-MM-dd HH:mm:ss"));
            SnowflakeIdUtils idWorker = new SnowflakeIdUtils(1, 1); //雪花生成全局唯一id
            permission.setPermissionUniqueId(idWorker.nextId());
            boolean result = permissionService.save(permission);
            if(result){
                return ResponseBo.ok("创建成功");
            }else{
                return ResponseBo.error("创建失败");
            }
        }else{
            //permission.setUpdateTime(DateUtils.parse(new Date(), "yyyy-MM-dd HH:mm:ss"));
            boolean result = permissionService.updateById(permission);
            if(result){
                return ResponseBo.ok("更新成功");
            }else{
                return ResponseBo.error("更新失败");
            }
        }
    }

    @PostMapping("/remove")
    public ResponseBo remove(@RequestParam("permissionId") Integer permissionId){
        boolean res = permissionService.removeById(permissionId);

        //定义查询器
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getPid, permissionId);
        boolean result = rolePermissionService.remove(queryWrapper);       //移除中间表中之前的关联数据

        if(res && result){
            return new ResponseBo();
        }else{
            return ResponseBo.error();
        }
    }

    @PostMapping("/removes")
    @Transactional
    public ResponseBo removes(@RequestBody List<Integer> permissionIds){
        boolean res = permissionService.removeByIds(permissionIds);

        for(int i = 0; i < permissionIds.size(); i++){
            //定义查询器
            LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(RolePermission::getPid, permissionIds.get(i));
            boolean result = rolePermissionService.remove(queryWrapper);       //移除中间表中之前的关联数据
            if(!result){
                return ResponseBo.error();
            }
        }
        if(res){
            return new ResponseBo();
        }else{
            return ResponseBo.error();
        }
    }

}
