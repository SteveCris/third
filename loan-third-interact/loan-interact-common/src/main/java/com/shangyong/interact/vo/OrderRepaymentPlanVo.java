package com.shangyong.interact.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("还款计划对象")
public class OrderRepaymentPlanVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7893882854625924403L;


	@ApiModelProperty(value = "借款日期", example = "2018-9-1",required=true)
	private String applyDate;

	@ApiModelProperty(value = "预还款日期", example = "2018-9-15",required=true)
	private String repayDate;

	@ApiModelProperty(value = "借款人姓名", example = "借款人姓名",required=true)
	private String borrowerName;


	@ApiModelProperty(value = "本金", example = "1000",required=true)
	private BigDecimal principal;

	@ApiModelProperty(value = "日利息", example = "20",required=true)
	private BigDecimal interest;

	@ApiModelProperty(value = "利息费", example = "10",required=true)
	private BigDecimal interestFee;

	@ApiModelProperty(value = "逾期日利息", example = "20",required=true)
	private BigDecimal overDueInterest;

	@ApiModelProperty(value = "逾期利息费", example = "10",required=true)
	private BigDecimal overDueInterestFee;

	@ApiModelProperty(value = "总利息=利息费+逾期利息费", example = "30",required=true)
	private BigDecimal totalInterestFee;

	@ApiModelProperty(value = "逾期日罚息", example = "20",required=true)
	private BigDecimal penaltyInterest;

	@ApiModelProperty(value = "逾期总罚息", example = "40",required=true)
	private BigDecimal totalPenaltyInterestFee;

	@ApiModelProperty(value = " 其他费用", example = "100",required=true)
	private BigDecimal otherFee;

	@ApiModelProperty(value = "借款总天数", example = "5",required=true)
	private Integer loanDays;

	@ApiModelProperty(value = "逾期天数", example = "10",required=true)
	private Integer overDueDays;

	@ApiModelProperty(value = "状态", example = "2",required=true)
	private String status;

	@ApiModelProperty(value = "描述信息", example = "描述",required=false)
	private String description;

	@ApiModelProperty(value = "实际还款时间", example = "2018-9-10",required=true)
	private String actualrepayDate;
	
	@ApiModelProperty(value = "是否逾期", example = "false",required=true)
	private boolean ifOverdue;

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getInterestFee() {
		return interestFee;
	}

	public void setInterestFee(BigDecimal interestFee) {
		this.interestFee = interestFee;
	}

	public BigDecimal getOverDueInterest() {
		return overDueInterest;
	}

	public void setOverDueInterest(BigDecimal overDueInterest) {
		this.overDueInterest = overDueInterest;
	}

	public BigDecimal getOverDueInterestFee() {
		return overDueInterestFee;
	}

	public void setOverDueInterestFee(BigDecimal overDueInterestFee) {
		this.overDueInterestFee = overDueInterestFee;
	}

	public BigDecimal getTotalInterestFee() {
		return totalInterestFee;
	}

	public void setTotalInterestFee(BigDecimal totalInterestFee) {
		this.totalInterestFee = totalInterestFee;
	}

	public BigDecimal getPenaltyInterest() {
		return penaltyInterest;
	}

	public void setPenaltyInterest(BigDecimal penaltyInterest) {
		this.penaltyInterest = penaltyInterest;
	}

	public BigDecimal getTotalPenaltyInterestFee() {
		return totalPenaltyInterestFee;
	}

	public void setTotalPenaltyInterestFee(BigDecimal totalPenaltyInterestFee) {
		this.totalPenaltyInterestFee = totalPenaltyInterestFee;
	}

	public BigDecimal getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(BigDecimal otherFee) {
		this.otherFee = otherFee;
	}

	public Integer getLoanDays() {
		return loanDays;
	}

	public void setLoanDays(Integer loanDays) {
		this.loanDays = loanDays;
	}

	public Integer getOverDueDays() {
		return overDueDays;
	}

	public void setOverDueDays(Integer overDueDays) {
		this.overDueDays = overDueDays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActualrepayDate() {
		return actualrepayDate;
	}

	public void setActualrepayDate(String actualrepayDate) {
		this.actualrepayDate = actualrepayDate;
	}

	public boolean isIfOverdue() {
		return ifOverdue;
	}

	public void setIfOverdue(boolean ifOverdue) {
		this.ifOverdue = ifOverdue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderRepaymentPlanVo [applyDate=");
		builder.append(applyDate);
		builder.append(", repayDate=");
		builder.append(repayDate);
		builder.append(", borrowerName=");
		builder.append(borrowerName);
		builder.append(", principal=");
		builder.append(principal);
		builder.append(", interest=");
		builder.append(interest);
		builder.append(", interestFee=");
		builder.append(interestFee);
		builder.append(", overDueInterest=");
		builder.append(overDueInterest);
		builder.append(", overDueInterestFee=");
		builder.append(overDueInterestFee);
		builder.append(", totalInterestFee=");
		builder.append(totalInterestFee);
		builder.append(", penaltyInterest=");
		builder.append(penaltyInterest);
		builder.append(", totalPenaltyInterestFee=");
		builder.append(totalPenaltyInterestFee);
		builder.append(", otherFee=");
		builder.append(otherFee);
		builder.append(", loanDays=");
		builder.append(loanDays);
		builder.append(", overDueDays=");
		builder.append(overDueDays);
		builder.append(", status=");
		builder.append(status);
		builder.append(", description=");
		builder.append(description);
		builder.append(", actualrepayDate=");
		builder.append(actualrepayDate);
		builder.append(", ifOverdue=");
		builder.append(ifOverdue);
		builder.append("]");
		return builder.toString();
	}
}
