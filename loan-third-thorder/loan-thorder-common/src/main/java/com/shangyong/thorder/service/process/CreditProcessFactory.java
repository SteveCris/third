package com.shangyong.thorder.service.process;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditProcessFactory {

	private static final String NAME_PREFIX = "creditProcess.";
	/**
	 * 捷融处理器名称
	 */
	public static final String JR_NAME_TYPE = NAME_PREFIX + "2";

	/**
	 * 星河处理器名称
	 */
	public static final String XH_NAME_TYPE = NAME_PREFIX + "3";

	/**
	 * 丰茂处理器名称
	 */
	public static final String FM_NAME_TYPE = NAME_PREFIX + "4";

	@Autowired
	private Map<String, CreditProcess> map;

	public CreditProcess getCreditProcess(int type) {
		return map.get(NAME_PREFIX + type);
	}
}
