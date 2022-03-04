package cn.wyedward.core.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 */
@Data
public class User implements Serializable{
    private static final long serialVersionUID = -8875987888356026988L;

    @TableId(type = IdType.AUTO)
    private int userId; //用户自增id
    private long userUniqueId; //用户唯一id
    private String userName; //用户登录名
    private String userNick; //用户昵称
    private String password; //用户密码
    private int status; //用户状态
    private Date createTime; //用户创建时间
    private Date UpdateTime; //用户更新时间
    private String userEmail; //用户邮箱
    private String userAvatar; //用户头像
    private int userEnable; //是否删除
    private int userDepartment; //用户部门
}
