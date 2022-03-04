package cn.wyedward.core.entity.hour;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
@Data
public class Job implements Serializable {
    private static final long serialVersionUID = 2398797359346226253L;
    @TableId(type = IdType.AUTO)
    private int jobId; //jobId
    private long jobUniqueId;
    private String jobName;
    private String jobDescription;
    private int jobFid;
    private int jobEnable;
}
