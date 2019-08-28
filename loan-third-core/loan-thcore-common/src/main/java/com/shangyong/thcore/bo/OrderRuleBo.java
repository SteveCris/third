package com.shangyong.thcore.bo;

import java.io.Serializable;

public class OrderRuleBo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7545940493607960564L;

	private String rule;
	
	private String sceneRule;
	
	private String orderId;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderRuleBo [rule=");
		builder.append(rule);
		builder.append(", sceneRule=");
		builder.append(sceneRule);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append("]");
		return builder.toString();
	}

	
}
