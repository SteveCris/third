package com.shangyong.thzlqb.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface PushTwoService {

	
	boolean pushTwo(String orderNo, ObjectNode node);
}
