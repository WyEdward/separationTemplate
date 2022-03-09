package cn.wyedward.core.entity.sys.dto;

import cn.wyedward.core.entity.sys.Permission;
import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionDto extends Permission implements Serializable {
    private static final long serialVersionUID = -768870242390871104L;
    private Permission permissionFu;  //父权限
}
