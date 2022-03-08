package cn.wyedward.core.entity.sys.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class PermissionDataForm implements Serializable {
    private static final long serialVersionUID = -4622796128485894382L;
    private String permissionName;
}
