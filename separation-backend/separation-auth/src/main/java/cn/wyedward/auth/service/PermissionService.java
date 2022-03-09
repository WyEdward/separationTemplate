package cn.wyedward.auth.service;
import cn.wyedward.core.entity.common.PageBean;
import cn.wyedward.core.entity.common.PageQueryWrapper;
import cn.wyedward.core.entity.sys.Permission;
import cn.wyedward.core.entity.sys.dto.PermissionDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    List<Permission> findByUserName(String userName);
    List<PermissionDto> listDtoByPage(PageBean<PermissionDto> pageBean, PageQueryWrapper<PermissionDto> wrapper);
    Integer countDto(PageQueryWrapper<PermissionDto> wrapper);
}
