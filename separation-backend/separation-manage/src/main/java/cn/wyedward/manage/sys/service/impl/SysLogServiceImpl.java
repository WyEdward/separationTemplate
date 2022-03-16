package cn.wyedward.manage.sys.service.impl;

import cn.wyedward.core.entity.sys.SysLog;
import cn.wyedward.core.mapper.sys.SysLogMapper;
import cn.wyedward.manage.sys.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService{
}
