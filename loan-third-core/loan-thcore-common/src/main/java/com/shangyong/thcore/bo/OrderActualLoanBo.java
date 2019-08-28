package com.shangyong.thcore.bo;

import java.io.Serializable;
import java.util.Date;

public class OrderActualLoanBo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8350456585917587471L;

	// 借款申请id
	private String applyId;

	// 账务系统订单id
	private String financeOrderId;

	// 创建时间
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
		builder.append("OrderActualLoanBo [applyId=");
		builder.append(applyId);
		builder.append(", financeOrderId=");
		builder.append(financeOrderId);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}
}
