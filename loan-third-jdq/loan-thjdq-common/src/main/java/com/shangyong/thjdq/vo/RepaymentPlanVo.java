package com.shangyong.thjdq.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 还款计划
 * Created by ybds on 2019-03-22.
 */
public class RepaymentPlanVo implements Serializable {

    private static final long serialVersionUID = 4602215046324329250L;
    /**
     * 实际还款日期 ，格式：yyyy-MM-dd注：在实际还款完成必传
     */
    private String true_repayment_time;
    /**
     * 计划还款日期，格式：yyyy-MM-dd
     */
    private String plan_repayment_time;
    /**
     * 本期账单金额中的本金，单位元，数值
     */
    private BigDecimal amount;
    /**
     * 前期费用(如果机构前期费用扣取失败，需要用户主动支付，需更新此字段)，单位元，数值
     */
    private BigDecimal member_fee;
    /**
     * 本期手续（利息）费，单位元，数值
     */
    private BigDecimal period_fee;
    /**
     * 已还金额，单位元，还清后传已还总金额
     */
    private BigDecimal repaid_amount;
    /**
     * 期数
     */
    private int period;
    /**
     * 还款计划状态：1-待还款，2-结清，4-逾期
     */
    private int status;
    /**
     * 逾期罚款，单位元，数值
     */
    private BigDecimal overdue_fee;
    /**
     * 逾期天数(如：10)
     */
    private int overdue_day;
    /**
     * 是否逾期，0-未逾期，1-逾期
     */
    private int overdue;

    public String getTrue_repayment_time() {
        return true_repayment_time;
    }

    public void setTrue_repayment_time(String true_repayment_time) {
        this.true_repayment_time = true_repayment_time;
    }

    public String getPlan_repayment_time() {
        return plan_repayment_time;
    }

    public void setPlan_repayment_time(String plan_repayment_time) {
        this.plan_repayment_time = plan_repayment_time;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getMember_fee() {
        return member_fee;
    }

    public void setMember_fee(BigDecimal member_fee) {
        this.member_fee = member_fee;
    }

    public BigDecimal getPeriod_fee() {
        return period_fee;
    }

    public void setPeriod_fee(BigDecimal period_fee) {
        this.period_fee = period_fee;
    }

    public BigDecimal getRepaid_amount() {
        return repaid_amount;
    }

    public void setRepaid_amount(BigDecimal repaid_amount) {
        this.repaid_amount = repaid_amount;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getOverdue_fee() {
        return overdue_fee;
    }

    public void setOverdue_fee(BigDecimal overdue_fee) {
        this.overdue_fee = overdue_fee;
    }

    public int getOverdue_day() {
        return overdue_day;
    }

    public void setOverdue_day(int overdue_day) {
        this.overdue_day = overdue_day;
    }

    public int getOverdue() {
        return overdue;
    }

    public void setOverdue(int overdue) {
        this.overdue = overdue;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RepaymentPlanVo{");
        sb.append("true_repayment_time='").append(true_repayment_time).append('\'');
        sb.append(", plan_repayment_time='").append(plan_repayment_time).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", member_fee=").append(member_fee);
        sb.append(", period_fee=").append(period_fee);
        sb.append(", repaid_amount=").append(repaid_amount);
        sb.append(", period=").append(period);
        sb.append(", status=").append(status);
        sb.append(", overdue_fee=").append(overdue_fee);
        sb.append(", overdue_day=").append(overdue_day);
        sb.append(", overdue=").append(overdue);
        sb.append('}');
        return sb.toString();
    }
}
