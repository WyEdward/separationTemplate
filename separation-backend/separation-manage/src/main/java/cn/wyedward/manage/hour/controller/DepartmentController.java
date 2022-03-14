package cn.wyedward.manage.hour.controller;

import cn.wyedward.core.common.ResponseBo;
import cn.wyedward.core.entity.hour.Department;
import cn.wyedward.core.entity.hour.vo.DepartmentVo;
import cn.wyedward.core.entity.sys.Permission;
import cn.wyedward.core.utils.SnowflakeIdUtils;
import cn.wyedward.manage.hour.service.DepartmentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 分页查询
     * @return
     */
    @ApiOperation(value = "分页查询项目")
    @PostMapping("/listByPage")
    @Transactional
    public ResponseBo listByPage(@RequestBody DepartmentVo departmentVo){
        //定义查询器
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        if(!"".equals(departmentVo.getDataForm().getDepartmentName())){
            queryWrapper.like(Department::getDepartmentName, departmentVo.getDataForm().getDepartmentName());
        }
        //当前页的页号, 和 每页显示的页数  查询条件
        IPage<Department> page =  departmentService.page(new Page<>( departmentVo.getCurrPage(),  departmentVo.getPageSize()), queryWrapper);
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
    public ResponseBo insertOrUpdate(@RequestBody Department department){
        if(department.getDepartmentId() == null || "0".equals(String.valueOf(department.getDepartmentId())) || "null".equals(String.valueOf(department.getDepartmentId()))){
            SnowflakeIdUtils idWorker = new SnowflakeIdUtils(1, 1); //雪花生成全局唯一id
            department.setDepartmentUniqueId(idWorker.nextId());
            //department.setCreateTime(DateUtils.parse(new Date(), "yyyy-MM-dd HH:mm:ss"));
            department.setCreateTime(new Date());
            //System.out.println("创建啦");
            boolean result = departmentService.save(department);
            if(result){
                return ResponseBo.ok("创建成功");
            }else{
                return ResponseBo.error("创建失败");
            }
        }else{
            //department.setUpdateTime(DateUtils.parse(new Date(), "yyyy-MM-dd HH:mm:ss"));
            department.setUpdateTime(new Date());
            boolean result = departmentService.updateById(department);
            if(result){
                return ResponseBo.ok("更新成功");
            }else{
                return ResponseBo.error("更新失败");
            }
        }
    }

    @PostMapping("/remove")
    public ResponseBo remove(@RequestParam("departmentId") Integer departmentId){
        boolean res = departmentService.removeById(departmentId);
        if(res){
            return new ResponseBo();
        }else{
            return ResponseBo.error();
        }
    }

    @PostMapping("/removes")
    @Transactional
    public ResponseBo removes(@RequestBody List<Integer> departmentIds){
        boolean res = departmentService.removeByIds(departmentIds);
        if(res){
            return new ResponseBo();
        }else{
            return ResponseBo.error();
        }
    }

    @PostMapping("/queryGroupByLike")
    @Transactional
    public ResponseBo queryGroupByLike(@RequestParam("queryString") String queryString){
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Department::getDepartmentName, queryString);
        IPage<Department> page =  departmentService.page(new Page<>( 1, 10), queryWrapper);
        ResponseBo bo = new ResponseBo();
        bo.put("record", page.getRecords());
        return bo;
    }

    @PostMapping("/lists")
    @Transactional
    public ResponseBo lists(){
        List<Department> lists = departmentService.list();
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("lists", lists);
        return responseBo;
    }
}
