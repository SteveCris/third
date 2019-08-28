package com.shangyong.thcore.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderBorrowBo implements Serializable {




	/**
	 * 
	 */
	private static final long serialVersionUID = 4826138689830418321L;

	private String appid;

	private String orderId;

	private String financeOrderId;

	private String creditUseUuid;

	private BigDecimal borrowAmount;
	
	private BigDecimal preBorrowAmount;
	
	private int cycle;
	
	private int periods;
	
	private Date createTime;
	
	private Date repaymentPlanTime;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFinanceOrderId() {
		return financeOrderId;
	}

	public void setFinanceOrderId(String financeOrderId) {
		this.financeOrderId = financeOrderId;
	}

	public String getCreditUseUuid() {
		return creditUseUuid;
	}

	public void setCreditUseUuid(String creditUseUuid) {
		this.creditUseUuid = creditUseUuid;
	}

	public BigDecimal getBorrowAmount() {
		return borrowAmount;
	}

	public void setBorrowAmount(BigDecimal borrowAmount) {
		this.borrowAmount = borrowAmount;
	}

	public BigDecimal getPreBorrowAmount() {
		return preBorrowAmount;
	}

	public void setPreBorrowAmount(BigDecimal preBorrowAmount) {
		this.preBorrowAmount = preBorrowAmount;
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public int getPeriods() {
		return periods;
	}

	public void setPeriods(int periods) {
		this.periods = periods;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getRepaymentPlanTime() {
		return repaymentPlanTime;
	}

	public void setRepaymentPlanTime(Date repaymentPlanTime) {
		this.repaymentPlanTime = repaymentPlanTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderBorrowBo [appid=");
		builder.append(appid);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", financeOrderId=");
		builder.append(financeOrderId);
		builder.append(", creditUseUuid=");
		builder.append(creditUseUuid);
		builder.append(", borrowAmount=");
		builder.append(borrowAmount);
		builder.append(", preBorrowAmount=");
		builder.append(preBorrowAmount);
		builder.append(", cycle=");
		builder.append(cycle);
		builder.append(", periods=");
		builder.append(periods);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", repaymentPlanTime=");
		builder.append(repaymentPlanTime);
		builder.append("]");
		return builder.toString();
	}



}
