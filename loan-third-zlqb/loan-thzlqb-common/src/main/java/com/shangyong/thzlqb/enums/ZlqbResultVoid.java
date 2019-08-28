package com.shangyong.thzlqb.enums;

public class ZlqbResultVoid extends ZlqbResult<Void> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean isSuccess() {
		return "200".equals(super.getCode());
	}

}
