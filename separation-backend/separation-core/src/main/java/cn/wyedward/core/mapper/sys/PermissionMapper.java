package cn.wyedward.core.mapper.sys;

import cn.wyedward.core.entity.sys.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> findByUserName(@Param("userName") String userName);
}
