package com.shangyong.interact.utils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonNodeUtil {

	private ObjectNode objectNode;

	public JsonNodeUtil(ObjectNode objectNode) {
		super();
		this.objectNode = objectNode;
	}

	public static ObjectNode data() {
		return new JsonNodeUtil(new ObjectNode(JsonNodeFactory.withExactBigDecimals(true))).getObjectNode();
	}

	public static ObjectNode parse(Object obj) {
		return JacksonUtil.parseToObjectNode(obj);
	}
	
	public ObjectNode getObjectNode() {
		return objectNode;
	}

}
