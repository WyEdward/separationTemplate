package cn.wyedward.core.entity.hour;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class UserJob implements Serializable {
    private static final long serialVersionUID = 2204365624193969686L;
    private int userJobId;
    private long userJobUniqueId;
    private int uId;
    private int jId;
    private Date firstTime;
    private Date lastTime;
    private Date totalTIme;
    private Date pushTime;
    private int userJobEnable;
    private int isAudit;
    private int auditId;
    private Date updateTime;
}
