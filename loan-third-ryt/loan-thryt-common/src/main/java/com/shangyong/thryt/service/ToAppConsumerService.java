package com.shangyong.thryt.service;

public interface ToAppConsumerService {

	/**
	 * 推送到app
	 * @param orderNo
	 * @return
	 */
	boolean push(String orderNo);

	
	
}
