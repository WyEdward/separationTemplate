package cn.wyedward.core.entity.sys.vo;

import cn.wyedward.core.entity.sys.User;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class UserAddRoleVo extends User implements Serializable {
    private static final long serialVersionUID = 1932346043908415849L;
    private ArrayList<Integer> roleList;
}
