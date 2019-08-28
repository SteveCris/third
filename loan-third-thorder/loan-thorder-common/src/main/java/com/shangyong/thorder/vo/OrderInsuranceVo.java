package com.shangyong.thorder.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderInsuranceVo implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 保单申请单号
     */
    private String applyNo;

    /**
     * 保单号
     */
    private String policyNo;

    /**
     * 取消保单的单号（只针对见费出单才有）
     */
    private String endorseNo;

    /**
     * 保单下载连接
     */
    private String policyUrl;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 保额
     */
    private BigDecimal sumAmount;

    /**
     * 保费
     */
    private BigDecimal sumPremium;

    /**
     * 生效日期
     */
    private Date startDate;

    /**
     * 终止日期
     */
    private Date endDate;

    /**
     * 状态 -1：投保失败 0：初始化 1：投保中 2：投保完成 3：取消投保
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getEndorseNo() {
		return endorseNo;
	}

	public void setEndorseNo(String endorseNo) {
		this.endorseNo = endorseNo;
	}

	public String getPolicyUrl() {
		return policyUrl;
	}

	public void setPolicyUrl(String policyUrl) {
		this.policyUrl = policyUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public BigDecimal getSumPremium() {
		return sumPremium;
	}

	public void setSumPremium(BigDecimal sumPremium) {
		this.sumPremium = sumPremium;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderInsuranceVo [applyNo=");
		builder.append(applyNo);
		builder.append(", policyNo=");
		builder.append(policyNo);
		builder.append(", endorseNo=");
		builder.append(endorseNo);
		builder.append(", policyUrl=");
		builder.append(policyUrl);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", sumAmount=");
		builder.append(sumAmount);
		builder.append(", sumPremium=");
		builder.append(sumPremium);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", state=");
		builder.append(state);
		builder.append(", remark=");
		builder.append(remark);
		builder.append("]");
		return builder.toString();
	}
    
    
	
}
