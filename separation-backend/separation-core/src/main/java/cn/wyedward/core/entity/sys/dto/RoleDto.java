package cn.wyedward.core.entity.sys.dto;

import cn.wyedward.core.entity.sys.Permission;
import cn.wyedward.core.entity.sys.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleDto extends Role implements Serializable {
    private static final long serialVersionUID = 460858786915059001L;
    private List<Permission> permissionList;
}
