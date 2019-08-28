package com.shangyong.thcore.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderPreCreditVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -881527845996392465L;

	//授信申请订单uuid
    private String creditUuid;

    //产品编号
    private String productCode;

    //合同编号
    private String contractCode;

    //授信金额
    private BigDecimal creditAmount;
    
    //前置授信创建时间
    private Date createTime;

	public String getCreditUuid() {
		return creditUuid;
	}

	public void setCreditUuid(String creditUuid) {
		this.creditUuid = creditUuid;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
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
		builder.append("OrderPreCreditVo [creditUuid=");
		builder.append(creditUuid);
		builder.append(", productCode=");
		builder.append(productCode);
		builder.append(", contractCode=");
		builder.append(contractCode);
		builder.append(", creditAmount=");
		builder.append(creditAmount);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}





	
}
