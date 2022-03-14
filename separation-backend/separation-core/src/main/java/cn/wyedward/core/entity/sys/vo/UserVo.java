package cn.wyedward.core.entity.sys.vo;

import cn.wyedward.core.entity.sys.dto.UserDataForm;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 8621506635772286528L;
    private Integer currPage; //前台传过来的当前页
    private Integer pageSize; //页面条数
    private UserDataForm dataForm; //前台查询结果
    //private Long total; //总记录数
}
