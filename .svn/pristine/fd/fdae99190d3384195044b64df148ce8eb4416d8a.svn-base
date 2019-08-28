package com.shangyong.thjdq.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 订单状态推送对象
 * Created by ybds on 2019-03-22.
 */
public class FeedbackOrderStatusVo implements Serializable {

    private static final long serialVersionUID = 5928506956528719690L;

    /**
     * 借点钱订单号
     */
    private String jdq_order_id;
    /**
     * 订单状态：0:待审核，1:已取消，2:审核失败，4:待签约，5:签约失败，6:待放款，7:已放款，8:已还清，10:逾期还清，13:已逾期，15:放款失败（最终状态）,16:额度失效，22：待绑卡，24：待开户，25：待授权
     */
    private int status;
    /**
     * 审批金额，单位元，审批通过后必传
     */
    private String approval_amount;
    /**
     * 审批期数，审批通过后必传
     */
    private int approval_periods;
    /**
     * 审批每期天数，审批通过后必传 （月按30计算）
     */
    private int approval_period_days;
    /**
     * 确认借款/合同金额，单位元，确认借款后必传
     */
    private String loan_amount;
    /**
     * 确认借款/合同期数，确认借款后必传
     */
    private int loan_periods;
    /**
     * 确认借款/合同每期天数，确认借款后必传（月按30计算）
     */
    private int loan_period_days;
    /**
     * 实际到手金额，确认借款后必传
     */
    private String card_amount;
    /**
     * 是否可换卡，1-是，0-否
     */
    private int change_card_flag;
    /**
     * 0-不需要存管账户提款，1-需要去存管账户提款，用户已经去存管提款了，不需要再次去提时需再传0过来。放款后必传
     */
    private int withdraw_flag;
    /**
     * 1-支持还款账户于还款日进行自动划扣(如果机构支持主动还款，用户也可进行主动还款)。 2-不支持还款账户于还款日进行自动划扣(机构必须支持用户主动还款)。 放款后必传
     */
    private int autopay_flag;
    /**
     * 用户放款/还款银行卡，绑卡后必传。返回当前用户最新一条有效借记卡/信用卡。注意类型为数组。如果机构告知平台方无需绑卡，即用户在机构方已有绑卡信息，也必须传。
     */
    private List<BankCardInfoVo> bank_card_info;
    /**
     * 还款计划，放款后必传。注意类型为数组。
     */
    private List<RepaymentPlanVo> repayment_plan;

    public String getJdq_order_id() {
        return jdq_order_id;
    }

    public void setJdq_order_id(String jdq_order_id) {
        this.jdq_order_id = jdq_order_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getApproval_amount() {
        return approval_amount;
    }

    public void setApproval_amount(String approval_amount) {
        this.approval_amount = approval_amount;
    }

    public int getApproval_periods() {
        return approval_periods;
    }

    public void setApproval_periods(int approval_periods) {
        this.approval_periods = approval_periods;
    }

    public int getApproval_period_days() {
        return approval_period_days;
    }

    public void setApproval_period_days(int approval_period_days) {
        this.approval_period_days = approval_period_days;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public int getLoan_periods() {
        return loan_periods;
    }

    public void setLoan_periods(int loan_periods) {
        this.loan_periods = loan_periods;
    }

    public int getLoan_period_days() {
        return loan_period_days;
    }

    public void setLoan_period_days(int loan_period_days) {
        this.loan_period_days = loan_period_days;
    }

    public String getCard_amount() {
        return card_amount;
    }

    public void setCard_amount(String card_amount) {
        this.card_amount = card_amount;
    }

    public int getChange_card_flag() {
        return change_card_flag;
    }

    public void setChange_card_flag(int change_card_flag) {
        this.change_card_flag = change_card_flag;
    }

    public int getWithdraw_flag() {
        return withdraw_flag;
    }

    public void setWithdraw_flag(int withdraw_flag) {
        this.withdraw_flag = withdraw_flag;
    }

    public int getAutopay_flag() {
        return autopay_flag;
    }

    public void setAutopay_flag(int autopay_flag) {
        this.autopay_flag = autopay_flag;
    }

    public List<BankCardInfoVo> getBank_card_info() {
        return bank_card_info;
    }

    public void setBank_card_info(List<BankCardInfoVo> bank_card_info) {
        this.bank_card_info = bank_card_info;
    }

    public List<RepaymentPlanVo> getRepayment_plan() {
        return repayment_plan;
    }

    public void setRepayment_plan(List<RepaymentPlanVo> repayment_plan) {
        this.repayment_plan = repayment_plan;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FeedbackOrderStatusVo{");
        sb.append("jdq_order_id='").append(jdq_order_id).append('\'');
        sb.append(", status=").append(status);
        sb.append(", approval_amount='").append(approval_amount).append('\'');
        sb.append(", approval_periods=").append(approval_periods);
        sb.append(", approval_period_days=").append(approval_period_days);
        sb.append(", loan_amount='").append(loan_amount).append('\'');
        sb.append(", loan_periods=").append(loan_periods);
        sb.append(", loan_period_days=").append(loan_period_days);
        sb.append(", card_amount='").append(card_amount).append('\'');
        sb.append(", change_card_flag=").append(change_card_flag);
        sb.append(", withdraw_flag=").append(withdraw_flag);
        sb.append(", autopay_flag=").append(autopay_flag);
        sb.append(", bank_card_info=").append(bank_card_info);
        sb.append(", repayment_plan=").append(repayment_plan);
        sb.append('}');
        return sb.toString();
    }
}
