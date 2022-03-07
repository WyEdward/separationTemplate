package cn.wyedward.core.entity.hour;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class UserJob implements Serializable {
    private static final long serialVersionUID = 2204365624193969686L;
    private Integer userJobId;
    private long userJobUniqueId;
    private Integer uId;
    private Integer jId;
    private Date firstTime;
    private Date lastTime;
    private Date totalTIme;
    private Date pushTime;
    private Integer userJobEnable;
    private Integer isAudit;
    private Integer auditId;
    private Date updateTime;
}
