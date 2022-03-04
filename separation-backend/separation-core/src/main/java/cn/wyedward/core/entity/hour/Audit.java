package cn.wyedward.core.entity.hour;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Audit implements Serializable {
    private static final long serialVersionUID = 2848067662713382927L;
    private int auditId;
    private long auditUniqueId;
    private int userJobId;
    private Date auditTime;
    private int auditPersonId;
    private int auditEnable;
}
