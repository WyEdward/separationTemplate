package cn.wyedward.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String userName;
    private String passWord;
    private Integer age;
    private Integer roleId;
    @TableField(exist = false)
    private UserRole userRole;
}
