package com.shangyong.thryt.exception;

import com.shangyong.thryt.enums.RytResultEnum;

/**
 * 自定义系统异常
 * 
 * @author caijunjun
 * @date 2018年5月18日
 */
public class CalfException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6754475997412553833L;

	private final RytResultEnum rytResultEnum;
	
	private Object body;

	public RytResultEnum getCoreResultEnum() {
		return rytResultEnum;
	}

	public CalfException(String message, Throwable cause, RytResultEnum rytResultEnum) {
		super(message, cause);
		this.rytResultEnum = rytResultEnum;
	}

	public CalfException(String message, RytResultEnum rytResultEnum) {
		super(message);
		this.rytResultEnum = rytResultEnum;
	}

	public CalfException(RytResultEnum rytResultEnum) {
		super(rytResultEnum.getMessage());
		this.rytResultEnum = rytResultEnum;
	}

	public CalfException(String message, Throwable cause) {
		super(message, cause);
		rytResultEnum = RytResultEnum.FAILURE;
	}

	public CalfException(String message) {
		super(message);
		rytResultEnum = RytResultEnum.FAILURE;
	}

	public Object getBody() {
		return body;
	}

	public CalfException withBody(Object body) {
		this.body = body;
		return this;
	}
	
	

}
