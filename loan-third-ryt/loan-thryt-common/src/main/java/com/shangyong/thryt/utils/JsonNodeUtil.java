package com.shangyong.thryt.utils;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonNodeUtil {

	private ObjectNode objectNode;

	private ArrayNode arrayNode;

	public JsonNodeUtil(ObjectNode objectNode) {
		super();
		this.objectNode = objectNode;
	}

	public JsonNodeUtil(ArrayNode arrayNode) {
		super();
		this.arrayNode = arrayNode;
	}

	public static ObjectNode getNodeWithSnakeCase(String url) {
		return JacksonUtil.parseToObjectNodeWithSnakeCase(FileUtil.readeJsonWithNet(url));
	}

	public static ObjectNode getNode(String url) {
		return JacksonUtil.parseToObjectNode(FileUtil.readeJsonWithNet(url));
	}

	public static ObjectNode data() {
		return new JsonNodeUtil(new ObjectNode(JsonNodeFactory.withExactBigDecimals(true))).getObjectNode();
	}

	public static ArrayNode arrayData() {
		return new JsonNodeUtil(new ArrayNode(JsonNodeFactory.withExactBigDecimals(true))).getArrayNode();
	}

	public ObjectNode getObjectNode() {
		return objectNode;
	}

	public ArrayNode getArrayNode() {
		return arrayNode;
	}
}
