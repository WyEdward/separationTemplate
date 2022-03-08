package cn.wyedward.core.entity.hour.vo;

import cn.wyedward.core.entity.hour.dataForm.DepartmentDataForm;
import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentVo implements Serializable {
    private static final long serialVersionUID = -6689599935936352575L;
    private Integer currPage; //前台传过来的当前页
    private Integer pageSize; //页面条数
    private Long total; //总记录数
    private DepartmentDataForm dataForm; //前台查询结果
}
