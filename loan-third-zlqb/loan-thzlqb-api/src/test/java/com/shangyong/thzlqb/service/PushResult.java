package com.shangyong.thzlqb.service;

import java.io.Serializable;

public class PushResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	private String msg;

	private boolean success;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PushResult [code=");
		builder.append(code);
		builder.append(", msg=");
		builder.append(msg);
		builder.append(", success=");
		builder.append(success);
		builder.append("]");
		return builder.toString();
	}

}
