package cn.wyedward.auth.service.impl;

import cn.wyedward.auth.service.PermissionService;
import cn.wyedward.core.entity.common.PageBean;
import cn.wyedward.core.entity.common.PageQueryWrapper;
import cn.wyedward.core.entity.sys.Permission;
import cn.wyedward.core.entity.sys.dto.PermissionDto;
import cn.wyedward.core.mapper.sys.PermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> findByUserName(String userName) {
        return permissionMapper.findByUserName(userName);
    }

    @Override
    public List<PermissionDto> listDtoByPage(PageBean<PermissionDto> pageBean, PageQueryWrapper<PermissionDto> wrapper) {
        int start = pageBean.getStart();
        int end = pageBean.getEnd();
        return permissionMapper.findDtoByPage(start, end, wrapper);
    }

    @Override
    public Integer countDto(PageQueryWrapper<PermissionDto> wrapper) {
        return permissionMapper.countDto(wrapper);
    }
}
