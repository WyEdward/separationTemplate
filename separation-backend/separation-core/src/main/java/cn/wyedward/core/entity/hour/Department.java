package cn.wyedward.core.entity.hour;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 部门表
 */
@Data
public class Department implements Serializable {
    private static final long serialVersionUID = -3527055115002518368L;
    @TableId(type = IdType.AUTO)
    private Integer departmentId;
    private Long departmentUniqueId;
    private String departmentName;
    private String departmentDescription;
    private Integer departmentFid;
    private Integer departmentEnable;
    private Date createTime;
    private Date updateTime;
}
