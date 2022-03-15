package cn.wyedward.auth.service;

import cn.wyedward.core.entity.common.PageBean;
import cn.wyedward.core.entity.common.PageQueryWrapper;
import cn.wyedward.core.entity.sys.User;
import cn.wyedward.core.entity.sys.dto.PermissionDto;
import cn.wyedward.core.entity.sys.dto.UserDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

public interface UserService extends IService<User> {
    /**
     * 更根据用户名查询用户相关联的角色权限信息
     * @param userName
     * @return
     */
    UserDto getUserRolePermission(String userName);
    ArrayList<UserDto> listDtoByPage(PageBean<UserDto> pageBean, PageQueryWrapper<UserDto> wrapper);
    Integer countDto(PageQueryWrapper<UserDto> wrapper);
    Integer resetPassword(String userName, String newPassword);
    String getPasswordByUserName(String userName);
}
