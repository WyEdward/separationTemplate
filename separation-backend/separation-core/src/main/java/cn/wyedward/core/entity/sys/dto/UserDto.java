package cn.wyedward.core.entity.sys.dto;


import cn.wyedward.core.entity.sys.Permission;
import cn.wyedward.core.entity.sys.Role;
import cn.wyedward.core.entity.sys.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDto extends User implements Serializable{
    private static final long serialVersionUID = -7634202375255867796L;
    private List<Permission> permissionList;  //用户权限集
    private List<Role> roleList; //用户角色集
}
