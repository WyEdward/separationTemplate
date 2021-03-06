package cn.wyedward.core.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志实体类
 */
@Data
public class SysLog implements Serializable {
    private static final long serialVersionUID = -4057673078106388677L;
    @TableId(type = IdType.AUTO)
    private Integer sysLogId;
    private Long SysLogUniqueId;
    private String userName;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private Date createTime;
    private Integer sysLogEnable;
}
