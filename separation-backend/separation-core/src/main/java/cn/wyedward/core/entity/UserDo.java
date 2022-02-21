package cn.wyedward.core.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserDo implements Serializable {
    int id; //用户id
    String userName; //用户名
}
