package cn.wyedward.core.mapper.sys;

import cn.wyedward.core.entity.common.PageQueryWrapper;
import cn.wyedward.core.entity.sys.Permission;
import cn.wyedward.core.entity.sys.dto.PermissionDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> findByUserName(@Param("userName") String userName);
    //查找数据库数据封装到permissionDto
    List<PermissionDto> findDtoByPage(@Param("start") int start, @Param("end") int end, @Param("wrapper") PageQueryWrapper<PermissionDto> wrapper);
    //统计dto查找条数
    Integer countDto(@Param("wrapper") PageQueryWrapper<PermissionDto> wrapper);
}
