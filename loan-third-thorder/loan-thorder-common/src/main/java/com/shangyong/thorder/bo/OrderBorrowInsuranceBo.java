package com.shangyong.thorder.bo;

import java.io.Serializable;
import java.util.Date;

public class OrderBorrowInsuranceBo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String applyId;
	
	private String financeOrderId;
	
	private Date createTime;

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getFinanceOrderId() {
		return financeOrderId;
	}

	public void setFinanceOrderId(String financeOrderId) {
		this.financeOrderId = financeOrderId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderBorrowInsuranceBo [applyId=");
		builder.append(applyId);
		builder.append(", financeOrderId=");
		builder.append(financeOrderId);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
