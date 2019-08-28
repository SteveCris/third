package com.shangyong.thjdq.event;

import java.io.Serializable;

/**
 * 借点钱推送订单状态事件
 * Created by ybds on 2019-03-24.
 */
public class PushJdqOrderEvent implements Serializable {
    private static final long serialVersionUID = 8939327023826450298L;

    /**
     * 订单状态
     */
    private int status;
    /**
     * 本地订单号
     */
    private String localOrderId;
    /**
     * 借点钱订单号
     */
    private String jdqOrderId;
    /**
     * 还款状态
     */
    private int repaymentStatus;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLocalOrderId() {
        return localOrderId;
    }

    public void setLocalOrderId(String localOrderId) {
        this.localOrderId = localOrderId;
    }

    public String getJdqOrderId() {
        return jdqOrderId;
    }

    public void setJdqOrderId(String jdqOrderId) {
        this.jdqOrderId = jdqOrderId;
    }

    public int getRepaymentStatus() {
        return repaymentStatus;
    }

    public void setRepaymentStatus(int repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PushJdqOrderEvent{");
        sb.append("status=").append(status);
        sb.append(", localOrderId='").append(localOrderId).append('\'');
        sb.append(", jdqOrderId='").append(jdqOrderId).append('\'');
        sb.append(", repaymentStatus=").append(repaymentStatus);
        sb.append('}');
        return sb.toString();
    }
}
