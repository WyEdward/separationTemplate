package cn.wyedward.core.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限中间表实体类
 */
@Data
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 7133870928037092880L;
    @TableId(type = IdType.AUTO)
    private Integer rpId;
    private Integer rid; //roleId
    private Integer pid; //permisisonId
    public RolePermission(){

    }
    public RolePermission(int rid, int pid){
        this.rid = rid;
        this.pid = pid;
    }
}
