package com.shangyong.thcore.vo;

import java.io.Serializable;

public class OrderRuleVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3213984353076206435L;

	private String rule;

	private String sceneRule;

	private String orderId;

	private int ssType;

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getSceneRule() {
		return sceneRule;
	}

	public void setSceneRule(String sceneRule) {
		this.sceneRule = sceneRule;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getSsType() {
		return ssType;
	}

	public void setSsType(int ssType) {
		this.ssType = ssType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderRuleVo [rule=");
		builder.append(rule);
		builder.append(", sceneRule=");
		builder.append(sceneRule);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", ssType=");
		builder.append(ssType);
		builder.append("]");
		return builder.toString();
	}


}
