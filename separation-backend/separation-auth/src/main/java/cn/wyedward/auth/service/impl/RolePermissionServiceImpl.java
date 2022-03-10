package cn.wyedward.auth.service.impl;

import cn.wyedward.auth.service.RolePermissionService;
import cn.wyedward.core.entity.sys.RolePermission;
import cn.wyedward.core.mapper.sys.RolePermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

}
