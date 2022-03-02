package cn.wyedward.core.entity.sys.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginVo implements Serializable {
    private static final long serialVersionUID = 8055812066707408259L;
    private String userName;
    private String password;
    private Boolean rememberMe;

}
