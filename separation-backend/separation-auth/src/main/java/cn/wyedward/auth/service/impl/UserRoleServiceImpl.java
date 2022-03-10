package cn.wyedward.auth.service.impl;

import cn.wyedward.auth.service.UserRoleService;
import cn.wyedward.core.entity.sys.UserRole;
import cn.wyedward.core.mapper.sys.UserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
