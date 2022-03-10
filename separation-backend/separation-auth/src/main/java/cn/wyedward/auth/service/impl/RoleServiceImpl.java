package cn.wyedward.auth.service.impl;

import cn.wyedward.auth.service.RoleService;
import cn.wyedward.core.entity.sys.Role;
import cn.wyedward.core.entity.sys.dto.RoleDto;
import cn.wyedward.core.mapper.sys.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public RoleDto findDtoById(Integer roleId) {
        return roleMapper.findDtoById(roleId);
    }

    @Override
    public List<Role> findByUserName(String userName) {
        return roleMapper.findByUserName(userName);
    }
}
