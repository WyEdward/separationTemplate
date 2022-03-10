package cn.wyedward.core.entity.sys.vo;

import cn.wyedward.core.entity.sys.dto.PermissionDataForm;
import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionVo implements Serializable {
    private static final long serialVersionUID = -6542502750218332481L;
    private Integer currPage; //前台传过来的当前页
    private Integer pageSize; //页面条数
    private PermissionDataForm dataForm; //前台查询结果
    //private Long total; //总记录数
}
