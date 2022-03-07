package cn.wyedward.manage.hour.service.impl;

import cn.wyedward.core.entity.hour.Job;
import cn.wyedward.core.mapper.hour.JobMapper;
import cn.wyedward.manage.hour.service.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("jobService")
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {
}
