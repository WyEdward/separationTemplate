package cn.wyedward.auth.service.impl;

import cn.wyedward.auth.service.PermissionService;
import cn.wyedward.core.entity.sys.Permission;
import cn.wyedward.core.mapper.sys.PermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionServiceImpl")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> findByUserName(String userName) {
        return permissionMapper.findByUserName(userName);
    }
}
