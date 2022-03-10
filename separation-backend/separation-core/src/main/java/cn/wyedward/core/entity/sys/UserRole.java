package cn.wyedward.core.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
@Data
public class UserRole implements Serializable {
    private static final long serialVersionUID = -4694500783565547544L;
    @TableId(type = IdType.AUTO)
    private Integer urId;
    private Integer uid;
    private Integer rid;
}
