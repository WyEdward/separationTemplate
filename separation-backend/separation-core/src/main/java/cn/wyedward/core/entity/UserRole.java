package cn.wyedward.core.entity;
import cn.wyedward.core.entity.UserInfo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class UserRole {
    @TableId(type = IdType.ASSIGN_ID)
    private Long roleId;
    private String roleName;

    @TableField(exist = false)
    private List<UserInfo> userInfos;
}
