package com.shangyong.thorder.utils;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JacksonUtil {

	private JacksonUtil() {

	}

	public static String parseToJsonString(Object obj) {
		if (obj == null) {
			return "{}";
		}
		return parse(obj, String.class);
	}

	public static ObjectNode parseToObjectNode(Object obj) {
		return parse(obj, ObjectNode.class);
	}

	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> parseToMap(Object obj) {
		if (obj instanceof Map) {
			return (Map<K, V>) obj;
		}
		return parse(obj, Map.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> T parse(Object obj, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (obj instanceof String) {
				return mapper.readValue(obj.toString(), clazz);
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
