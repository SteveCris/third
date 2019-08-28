package com.shangyong.thcore.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ybds on 2019-03-27.
 */
public class UserQuarantineVo implements Serializable {

    private static final long serialVersionUID = -4344936723017038723L;

    /**
     * 是否处于隔离期
     */
    private boolean ifQuarantine;

    /**
     * 隔离结束时间
     */
    private Date quarantineEndTime;

    /**
     * 隔离原因
     */
    private String reason;


    public boolean isIfQuarantine() {
        return ifQuarantine;
    }

    public void setIfQuarantine(boolean ifQuarantine) {
        this.ifQuarantine = ifQuarantine;
    }

    public Date getQuarantineEndTime() {
        return quarantineEndTime;
    }

    public void setQuarantineEndTime(Date quarantineEndTime) {
        this.quarantineEndTime = quarantineEndTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserQuarantineVo{");
        sb.append("ifQuarantine=").append(ifQuarantine);
        sb.append(", quarantineEndTime=").append(quarantineEndTime);
        sb.append(", reason='").append(reason).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
