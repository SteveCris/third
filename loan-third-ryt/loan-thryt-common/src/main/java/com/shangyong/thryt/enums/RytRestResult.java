package com.shangyong.thryt.enums;

import java.io.Serializable;

public class RytRestResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 返回信息 唯一编号
	 */
	private String code;

	/**
	 * 返回信息 描述
	 */
	private String msg;


	/**
	 * 返回信息 具体数据
	 */
	private T data;


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


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RestResult [code=");
		builder.append(code);
		builder.append(", msg=");
		builder.append(msg);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
	
	

}
