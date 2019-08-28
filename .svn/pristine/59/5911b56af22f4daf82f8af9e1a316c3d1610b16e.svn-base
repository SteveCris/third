package com.shangyong.thjdq.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Map;

public class JacksonUtil {

	private JacksonUtil() {

	}

	public static String parseToJsonString(Object obj) {
		return parse(obj, String.class);
	}

	public static ObjectNode parseToObjectNode(Object obj) {
		return parse(obj, ObjectNode.class);
	}

	public static ArrayNode parseToArray(Object obj) {
		return parse(obj, ArrayNode.class);
	}
	
	public static <T> T parseToBean(Map<String, String> map, Class<T> clazz) {
		return parse(map, clazz);
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
			throw new IllegalArgumentException("jackson 转换失败",e);
		}
	}

}
