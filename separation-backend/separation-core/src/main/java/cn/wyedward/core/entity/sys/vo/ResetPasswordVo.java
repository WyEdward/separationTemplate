package cn.wyedward.core.entity.sys.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class ResetPasswordVo implements Serializable {
    private static final long serialVersionUID = -2115606466458635935L;
    private String userName;
    private String oldPassword;
    private String newPassword;
    private String repeatNewPassword;
}
