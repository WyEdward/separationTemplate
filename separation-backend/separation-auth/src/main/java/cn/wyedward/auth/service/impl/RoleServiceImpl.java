package cn.wyedward.auth.service.impl;

import cn.wyedward.auth.service.RoleService;
import cn.wyedward.core.entity.sys.Role;
import cn.wyedward.core.mapper.sys.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("roleServiceImpl")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
