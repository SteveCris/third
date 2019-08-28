package com.shangyong.thryt.bo;

import java.io.Serializable;

public class RytOrderBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderId;

	private int status;

	private boolean ifFinish;

	private String remark;

	private int loanAmount;

	private int termUnit;

	private int loanTerm;

	private String ext1;

	private String ext2;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isIfFinish() {
		return ifFinish;
	}

	public void setIfFinish(boolean ifFinish) {
		this.ifFinish = ifFinish;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getTermUnit() {
		return termUnit;
	}

	public void setTermUnit(int termUnit) {
		this.termUnit = termUnit;
	}

	public int getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
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
		builder.append("RytOrderBo [orderId=");
		builder.append(orderId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", ifFinish=");
		builder.append(ifFinish);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", loanAmount=");
		builder.append(loanAmount);
		builder.append(", termUnit=");
		builder.append(termUnit);
		builder.append(", loanTerm=");
		builder.append(loanTerm);
		builder.append(", ext1=");
		builder.append(ext1);
		builder.append(", ext2=");
		builder.append(ext2);
		builder.append("]");
		return builder.toString();
	}

}
