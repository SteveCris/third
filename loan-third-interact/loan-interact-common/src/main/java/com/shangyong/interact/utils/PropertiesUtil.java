package com.shangyong.interact.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {

	private static Properties properties;

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	private static ConcurrentHashMap<String, String> cachedProperties = new ConcurrentHashMap<>();

	static {
		properties = new Properties();
		try (InputStream in = PropertiesUtil.class.getResourceAsStream("/config.properties")) {
			properties.load(new InputStreamReader(in, "UTF-8"));
		} catch (IOException e) {
			logger.error("初始化属性工具类失败", e);
		}
	}

	public static long getLong(String key) {
		return Long.parseLong(get(key));
	}

	public static int getInt(String key) {
		return Integer.parseInt(get(key));
	}

	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(get(key));
	}

	public static double getDoubleItem(String key) {
		return Double.parseDouble(get(key));
	}

	public static String get(String key) {
		return get(key, "");
	}

	public static String get(String key, String defaultValue) {

		if (key == null || key.length() == 0) {
			throw new IllegalArgumentException("参数key不能为空");
		}

		// 返回值
		String configValue;

		// 判断缓存中是否有
		if (cachedProperties.containsKey(key)) {// 缓存有
			// 从缓存获取
			configValue = cachedProperties.get(key);
		} else {// 缓存没有
			// 从文件获取
			configValue = getConfigValue(key).orElse("").trim();
			// 放入缓存
			cachedProperties.putIfAbsent(key, configValue);
		}

		// 处理默认值
		if (configValue == null || configValue.length() == 0) {
			configValue = defaultValue;
		}

		return configValue;

	}

	private static Optional<String> getConfigValue(String key) {
		String configValue = properties.getProperty(key);
		return Optional.ofNullable(configValue);
	}

	private PropertiesUtil() {

	}
}
