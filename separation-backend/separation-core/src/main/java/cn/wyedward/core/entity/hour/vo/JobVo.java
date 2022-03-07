package cn.wyedward.core.entity.hour.vo;

import cn.wyedward.core.entity.hour.dataForm.JobDataForm;
import lombok.Data;

import java.io.Serializable;

@Data
public class JobVo implements Serializable {
    private static final long serialVersionUID = -5312912786426024293L;
    private Integer currPage; //前台传过来的当前页
    private Integer pageSize; //页面条数
    private Long total; //总记录数
    private JobDataForm dataForm; //前台查询结果
}
