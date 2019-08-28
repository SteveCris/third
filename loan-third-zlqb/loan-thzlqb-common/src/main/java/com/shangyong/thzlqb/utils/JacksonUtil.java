package com.shangyong.thzlqb.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;

public class JacksonUtil {

	private JacksonUtil() {

	}

	public static String parseToJsonString(Object obj) {
		if (obj == null) {
			return "{}";
		}
		return parse(obj, String.class, false);
	}

	public static String parseToJsonStringWithSnakeCase(Object obj) {
		if (obj == null) {
			return "{}";
		}
		return parse(obj, String.class, true);
	}

	public static ObjectNode parseToObjectNode(Object obj) {
		return parse(obj, ObjectNode.class, false);
	}

	public static ObjectNode parseToObjectNodeWithSnakeCase(Object obj) {
		return parse(obj, ObjectNode.class, true);
	}

	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> parseToMap(Object obj) {
		if (obj instanceof Map) {
			return (Map<K, V>) obj;
		}
		return parse(obj, Map.class, false);
	}

	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> parseToMapWithSnakeCase(Object obj) {
		if (obj instanceof Map) {
			return (Map<K, V>) obj;
		}
		return parse(obj, Map.class, true);
	}

	@SuppressWarnings("unchecked")
	public static <K, V> SortedMap<K, V> parseToSortedMap(Object obj) {
		if (obj instanceof Map) {
			return (SortedMap<K, V>) obj;
		}
		return parse(obj, SortedMap.class, false);
	}

	@SuppressWarnings("unchecked")
	public static <K, V> SortedMap<K, V> parseToSortedMapWithSnakeCase(Object obj) {
		if (obj instanceof Map) {
			return (SortedMap<K, V>) obj;
		}
		return parse(obj, SortedMap.class, true);
	}

	public static <T> T parse(Object obj, Class<T> clazz) {
		return parse(obj, clazz, false);
	}

	public static <T> T parseWithSnakeCase(Object obj, Class<T> clazz) {
		return parse(obj, clazz, true);
	}

	@SuppressWarnings("unchecked")
	private static <T> T parse(Object obj, Class<T> clazz, boolean snakeCase) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (obj instanceof String) {
				return mapper.readValue(obj.toString(), clazz);
			}
			// 反序列化的时候 忽略没有的属性值
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			if (snakeCase) {
				// 下划线转驼峰
				mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
			}

			String json = mapper.writeValueAsString(obj);
			if (clazz == String.class) {
				return (T) json;
			}
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new IllegalArgumentException("转换失败", e);
		}
	}

}
