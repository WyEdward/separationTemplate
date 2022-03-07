package cn.wyedward.manage.hour.controller;

import cn.wyedward.core.common.ResponseBo;
import cn.wyedward.core.entity.hour.Job;
import cn.wyedward.core.entity.hour.vo.JobVo;
import cn.wyedward.core.utils.DateUtils;
import cn.wyedward.manage.hour.service.JobService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.javaws.IconUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController("jobController")
@CrossOrigin
@RequestMapping("/admin/job")
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * 分页查询
     * @return
     */
    @ApiOperation(value = "分页查询项目")
    @PostMapping("/listByPage")
    @Transactional
    public ResponseBo listByPage(@RequestBody JobVo jobVo){
        //定义查询器
        LambdaQueryWrapper<Job> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        if(!"".equals(jobVo.getDataForm().getJobName())){
            queryWrapper.like(Job::getJobName, jobVo.getDataForm().getJobName());
        }
        //当前页的页号, 和 每页显示的页数  查询条件
        IPage<Job> page = jobService.page(new Page<>(jobVo.getCurrPage(), jobVo.getPageSize()), queryWrapper);
        //定义返回对象
        ResponseBo bo = new ResponseBo();
        bo.put("record", page.getRecords());
        bo.put("total", page.getTotal());
        bo.put("pages", page.getPages());
        bo.put("current", page.getCurrent());
        bo.put("size", page.getSize());
        System.out.println(bo);
        return bo;
    }

    @ApiOperation(value = "创建更新项目")
    @PostMapping("/insertOrUpdate")
    @Transactional
    public ResponseBo insertOrUpdate(@RequestBody Job job){
        if("0".equals(String.valueOf(job.getJobId())) || "null".equals(String.valueOf(job.getJobId()))){
            //job.setCreateTime(DateUtils.parse(new Date(), "yyyy-MM-dd HH:mm:ss"));
            job.setCreateTime(new Date());
            boolean result = jobService.save(job);
            if(result){
                return ResponseBo.ok("创建成功");
            }else{
                return ResponseBo.error("创建失败");
            }
        }else{
            //job.setUpdateTime(DateUtils.parse(new Date(), "yyyy-MM-dd HH:mm:ss"));
            job.setUpdateTime(new Date());
            boolean result = jobService.updateById(job);
            if(result){
                return ResponseBo.ok("更新成功");
            }else{
                return ResponseBo.error("更新失败");
            }
        }
    }

    @PostMapping("/remove")
    public ResponseBo remove(@RequestParam("jobId") Integer jobId){
        boolean res = jobService.removeById(jobId);
        if(res){
            return new ResponseBo();
        }else{
            return ResponseBo.error();
        }
    }

    @PostMapping("/removes")
    @Transactional
    public ResponseBo removes(@RequestBody List<Integer> jobIds){
        boolean res = jobService.removeByIds(jobIds);
        if(res){
            return new ResponseBo();
        }else{
            return ResponseBo.error();
        }
    }

}
