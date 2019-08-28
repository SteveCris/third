package com.shangyong.interact.utils;

import org.apache.commons.lang3.RandomStringUtils;

import com.shangyong.common.entity.SnowFlake;

public class IdUtil {

	public static long getLongId() {
		return getInstance().nextId();
	}
	
	public static String getNumberUuid(String prefix) {
		return prefix+getLongId();
	}
	
	public static String getUuid() {
		return randomAlphanumeric(32);
	}
	
	public static String getUuid(int count) {
		return randomAlphanumeric(count);
	}

	private static String randomAlphanumeric(int count) {
		return RandomStringUtils.randomAlphanumeric(count);
	}

	private static SnowFlake getInstance() {
		return LazyHolder.instance;
	}

	private IdUtil() {

	}

	static class LazyHolder {

		private static final SnowFlake instance = new SnowFlake(PropertiesUtil.getLong("snowFlake.datacenterId"),
				PropertiesUtil.getLong("snowFlake.machineId"));

		private LazyHolder() {
		}

	}
}
