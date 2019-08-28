package com.shangyong.thcore.event.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class EventActualLoan implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String creditUseUuid;
	
	private BigDecimal loanAmount;
	
	private String financeOrderId;
	
	private String applyId;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 扩展字段
	 */
	private String ext;
	
	/**
	 * 扩展字段2
	 */
	private String ext2;


	public String getCreditUseUuid() {
		return creditUseUuid;
	}

	public void setCreditUseUuid(String creditUseUuid) {
		this.creditUseUuid = creditUseUuid;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getFinanceOrderId() {
		return financeOrderId;
	}

	public void setFinanceOrderId(String financeOrderId) {
		this.financeOrderId = financeOrderId;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventActualLoan [creditUseUuid=");
		builder.append(creditUseUuid);
		builder.append(", loanAmount=");
		builder.append(loanAmount);
		builder.append(", financeOrderId=");
		builder.append(financeOrderId);
		builder.append(", applyId=");
		builder.append(applyId);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", ext=");
		builder.append(ext);
		builder.append(", ext2=");
		builder.append(ext2);
		builder.append("]");
		return builder.toString();
	}

	
	
}
