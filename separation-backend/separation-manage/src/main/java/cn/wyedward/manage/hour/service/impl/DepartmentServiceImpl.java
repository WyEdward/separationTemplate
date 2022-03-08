package cn.wyedward.manage.hour.service.impl;

import cn.wyedward.core.entity.hour.Department;
import cn.wyedward.core.mapper.hour.DepartmentMapper;
import cn.wyedward.manage.hour.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("departmentService")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
}
