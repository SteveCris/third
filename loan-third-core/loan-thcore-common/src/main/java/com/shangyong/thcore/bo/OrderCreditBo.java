package com.shangyong.thcore.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderCreditBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8417249679736789188L;

	// 授信uuid
	private String creditUseUuid;

	// 产品编号
	private String productCode;

	// 授信金额
	private BigDecimal creditAmount;

	// 创建时间
	private Date createTime;

	public String getCreditUseUuid() {
		return creditUseUuid;
	}

	public void setCreditUseUuid(String creditUseUuid) {
		this.creditUseUuid = creditUseUuid;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
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
		builder.append("OrderCreditBo [creditUseUuid=");
		builder.append(creditUseUuid);
		builder.append(", productCode=");
		builder.append(productCode);
		builder.append(", creditAmount=");
		builder.append(creditAmount);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}

}
