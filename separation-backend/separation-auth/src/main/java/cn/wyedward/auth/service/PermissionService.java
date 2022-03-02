package cn.wyedward.auth.service;

import cn.wyedward.core.entity.sys.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    List<Permission> findByUserName(String userName);
}
