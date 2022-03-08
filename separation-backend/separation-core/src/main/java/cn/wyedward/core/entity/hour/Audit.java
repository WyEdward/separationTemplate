package cn.wyedward.core.entity.hour;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Audit implements Serializable {
    private static final long serialVersionUID = 2848067662713382927L;
    private Integer auditId;
    private Long auditUniqueId;
    private Integer userJobId;
    private Date auditTime;
    private Integer auditPersonId;
    private Integer auditEnable;
}
