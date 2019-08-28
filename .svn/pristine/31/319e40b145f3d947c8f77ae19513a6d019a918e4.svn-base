package com.shangyong.thryt.bo;

import java.io.Serializable;

public class ThirdRytResultBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	private String msg;

	private Object data;

	public boolean isSuccess() {
		return "0".equals(code);
	}
	
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ThirdRytResultBo [code=");
		builder.append(code);
		builder.append(", msg=");
		builder.append(msg);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

}
