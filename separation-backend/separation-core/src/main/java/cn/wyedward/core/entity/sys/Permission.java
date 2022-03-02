package cn.wyedward.core.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
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
    private int permissionId; //权限自增id
    private long permissionUnique; //权限唯一id
    private String permissionUrl; //前端router_url
    private String permissionName; //后端shiro权限命名
    private String permissionDescription; //描述int
    private int permissionFid; //父权限id
    private int permissionEnable; //是否删除 1就是删除
}
