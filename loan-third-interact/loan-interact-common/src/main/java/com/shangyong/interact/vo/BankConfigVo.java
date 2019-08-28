package com.shangyong.interact.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("银行卡配置对象")
public class BankConfigVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "卡类型(1借记卡2信用卡)", example = "1",required=true)
	private Integer cardType;

	@ApiModelProperty(value = "银行名称", example = "工商银行",required=true)
	private String bankName;

	@ApiModelProperty(value = "银行code", example = "ICBC",required=true)
	private String bankCode;

	@ApiModelProperty(value = "银行卡背景颜色代码", example = "#44144",required=false)
	private String bgColor;

	@ApiModelProperty(value = "银行logo图标url", example = "https://pinganedai.vip/htmls/appv3/banklogo/40x40/00dcfba270294767a0534f7c2261db67.png",required=true)
	private String logoUrl;

	@ApiModelProperty(value = "银行logo图标url", example = "https://pinganedai.vip/htmls/appv3/banklogo/80x80/00dcfba270294767a0534f7c2261db67.png",required=true)
	private String bgLogoUrl;

	@ApiModelProperty(value = "备注", example = "备注信息",required=false)
	private String remark;

	@ApiModelProperty(value = "银行单笔限额", example = "50000.00",required=true)
	private BigDecimal singleLimit;

	@ApiModelProperty(value = "银行每日限额", example = "50000.00",required=true)
	private BigDecimal dailyLimit;

	@ApiModelProperty(value = "银行每月限额", example = "1000000.00",required=true)
	private BigDecimal monthlyLimit;

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getBgLogoUrl() {
		return bgLogoUrl;
	}

	public void setBgLogoUrl(String bgLogoUrl) {
		this.bgLogoUrl = bgLogoUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getSingleLimit() {
		return singleLimit;
	}

	public void setSingleLimit(BigDecimal singleLimit) {
		this.singleLimit = singleLimit;
	}

	public BigDecimal getDailyLimit() {
		return dailyLimit;
	}

	public void setDailyLimit(BigDecimal dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

	public BigDecimal getMonthlyLimit() {
		return monthlyLimit;
	}

	public void setMonthlyLimit(BigDecimal monthlyLimit) {
		this.monthlyLimit = monthlyLimit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankConfigVo [cardType=");
		builder.append(cardType);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", bankCode=");
		builder.append(bankCode);
		builder.append(", bgColor=");
		builder.append(bgColor);
		builder.append(", logoUrl=");
		builder.append(logoUrl);
		builder.append(", bgLogoUrl=");
		builder.append(bgLogoUrl);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", singleLimit=");
		builder.append(singleLimit);
		builder.append(", dailyLimit=");
		builder.append(dailyLimit);
		builder.append(", monthlyLimit=");
		builder.append(monthlyLimit);
		builder.append("]");
		return builder.toString();
	}

}
