package cn.wyedward.auth.service;

import cn.wyedward.core.entity.sys.Role;
import cn.wyedward.core.entity.sys.dto.RoleDto;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService extends IService<Role> {
    /**
     * 根据角色id去查询权限集合
     * @param roleId
     * @return
     */
    RoleDto findDtoById(Integer roleId);
    List<Role> findByUserName(String userName);
}
