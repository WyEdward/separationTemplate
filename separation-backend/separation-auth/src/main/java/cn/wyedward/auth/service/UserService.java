package cn.wyedward.auth.service;

import cn.wyedward.core.entity.sys.User;
import cn.wyedward.core.entity.sys.dto.UserDto;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    /**
     * 更根据用户名查询用户相关联的角色权限信息
     * @param userName
     * @return
     */
    UserDto getUserRolePermission(String userName);
}
