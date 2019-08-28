package com.shangyong.interact.enums;

import java.io.Serializable;

/**
 * 统一返回空对象
 * 
 * @author caijunjun
 * @date 2019年5月26日
 */
public class CoreVoid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CoreVoid []");
		return builder.toString();
	}

}
