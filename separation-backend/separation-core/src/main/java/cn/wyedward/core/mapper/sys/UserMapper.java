package cn.wyedward.core.mapper.sys;

import cn.wyedward.core.entity.sys.User;
import cn.wyedward.core.entity.sys.dto.UserDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 更根据用户名查询用户相关联的角色权限信息
     * @param userName
     * @return
     */
    UserDto getUserRolePermission(@Param("userName") String userName);
}
