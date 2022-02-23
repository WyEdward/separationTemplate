package cn.wyedward.core.mapper;

import cn.wyedward.core.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    UserRole getUserRoleById(@Param("roleId") int roleId);
}
