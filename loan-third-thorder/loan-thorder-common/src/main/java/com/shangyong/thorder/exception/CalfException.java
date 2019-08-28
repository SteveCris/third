package com.shangyong.thorder.exception;

import com.shangyong.thorder.enums.CoreResultEnum;

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

	private final CoreResultEnum coreResultEnum;
	
	private Object body;

	public CoreResultEnum getCoreResultEnum() {
		return coreResultEnum;
	}

	public CalfException(String message, Throwable cause, CoreResultEnum coreResultEnum) {
		super(message, cause);
		this.coreResultEnum = coreResultEnum;
	}

	public CalfException(String message, CoreResultEnum coreResultEnum) {
		super(message);
		this.coreResultEnum = coreResultEnum;
	}

	public CalfException(CoreResultEnum coreResultEnum) {
		super(coreResultEnum.getMessage());
		this.coreResultEnum = coreResultEnum;
	}

	public CalfException(String message, Throwable cause) {
		super(message, cause);
		coreResultEnum = CoreResultEnum.FAILURE;
	}

	public CalfException(String message) {
		super(message);
		coreResultEnum = CoreResultEnum.FAILURE;
	}

	public Object getBody() {
		return body;
	}

	public CalfException withBody(Object body) {
		this.body = body;
		return this;
	}
	
	

}
