package cn.wyedward.core.entity.sys.vo;

import cn.wyedward.core.entity.sys.dto.RoleDataForm;
import lombok.Data;

import java.io.Serializable;
@Data
public class RoleVo implements Serializable {
    private static final long serialVersionUID = 5873842894583648028L;
    private Integer currPage; //前台传过来的当前页
    private Integer pageSize; //页面条数
    private RoleDataForm dataForm; //前台查询结果
}
