package cn.wyedward.core.mapper.sys;

import cn.wyedward.core.entity.sys.Permission;
import cn.wyedward.core.entity.sys.Role;
import cn.wyedward.core.entity.sys.dto.RoleDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户名去查找角色集合
     * @param userName
     * @return
     */
    List<Role> findByUserName(@Param("userName") String userName);

    /**
     * 根据角色id去查询权限集合
     * @param roleId
     * @return
     */
    RoleDto findDtoById(@Param("roleId") Integer roleId);
}
