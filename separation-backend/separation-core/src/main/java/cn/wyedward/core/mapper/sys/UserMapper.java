package cn.wyedward.core.mapper.sys;

import cn.wyedward.core.entity.common.PageQueryWrapper;
import cn.wyedward.core.entity.sys.User;
import cn.wyedward.core.entity.sys.dto.PermissionDto;
import cn.wyedward.core.entity.sys.dto.UserDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 更根据用户名查询用户相关联的角色权限信息
     * @param userName
     * @return
     */
    UserDto getUserRolePermission(@Param("userName") String userName);

    /**
     * 分页查找userDto
     * @param start
     * @param end
     * @param wrapper
     * @return
     */
    ArrayList<UserDto> getUserRolePermissionByPage(@Param("start") int start, @Param("end") int end, @Param("wrapper") PageQueryWrapper<UserDto> wrapper);

    /**
     * userDto的总条数
     * @param wrapper
     * @return
     */
    Integer countDto(@Param("wrapper") PageQueryWrapper<UserDto> wrapper);

    /**
     * 重置密码
     * @param newPassword
     * @param userName
     * @return
     */
    Integer resetPassword(@Param("userName") String  userName, @Param("newPassword") String newPassword);

    /**
     * 根据用户名查密码
     * @param userName
     * @return
     */
    String getPasswordByUserName(@Param("userName") String userName);
}
