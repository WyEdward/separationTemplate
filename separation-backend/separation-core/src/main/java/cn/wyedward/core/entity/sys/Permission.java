package cn.wyedward.core.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限实体类
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 8467545221300902305L;
    @TableId(type = IdType.AUTO)
    private Integer permissionId; //权限自增id
    private Long permissionUniqueId; //权限唯一id
    private String permissionUrl; //前端router_url
    private String permissionName; //后端shiro权限命名
    private String permissionDescription; //描述int
    private Integer permissionFid; //父权限id
    private Integer permissionEnable; //是否删除 1就是删除
}
