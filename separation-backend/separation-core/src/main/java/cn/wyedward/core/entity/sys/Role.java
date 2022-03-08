package cn.wyedward.core.entity.sys;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;

/**
 * 角色实体类
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -5068356549484959834L;

    @TableId(type = IdType.AUTO)
    private Integer roleId; //角色自增id
    private Long uniqueId;  //角色唯一id
    private String roleName; //角色名字
    private String roleDescription; //角色描述
    private Integer roleEnable; //角色是否删除
}
