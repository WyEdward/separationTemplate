package cn.wyedward.core.entity.sys.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleAddPermissionVo implements Serializable {
    private static final long serialVersionUID = 7154924314523808032L;
    private Integer roleId;
    private String roleName;
    private String roleDescription;
    private List<Integer> treeList;
}
