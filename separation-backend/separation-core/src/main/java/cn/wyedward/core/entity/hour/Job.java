package cn.wyedward.core.entity.hour;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Job implements Serializable {
    private static final long serialVersionUID = 2398797359346226253L;
    @TableId(type = IdType.AUTO)
    private Integer jobId; //jobId
    private long jobUniqueId;
    private String jobName;
    private String jobDescription;
    private Integer jobFid;
    private Integer jobEnable;
    private Date createTime;
    private Date updateTime;
}
