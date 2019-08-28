package com.shangyong.thzlqb.enums;

import java.io.Serializable;

public class ZlqbRestResult<T> implements Serializable {

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
	private T response;


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


	public T getResponse() {
		return response;
	}


	public void setResponse(T response) {
		this.response = response;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ZlqbRestResult [code=");
		builder.append(code);
		builder.append(", msg=");
		builder.append(msg);
		builder.append(", response=");
		builder.append(response);
		builder.append("]");
		return builder.toString();
	}





}
