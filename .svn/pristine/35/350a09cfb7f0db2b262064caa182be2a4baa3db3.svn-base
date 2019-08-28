package com.shangyong.thryt.service;

import com.shangyong.thryt.event.RytPushEvent;

public interface RytConsumerService {

	/**
	 * 处理 超时的资源
	 * 
	 * @param rytPushEvent
	 * @return
	 */
	boolean processRytPushTime(RytPushEvent rytPushEvent);

	/**
	 * 处理基本数据落库
	 * 
	 * @param rytPushEvent
	 * @return
	 */
	boolean processRytPushFall(RytPushEvent rytPushEvent);

	/**
	 * 处理审核事件
	 * 
	 * @param rytPushEvent
	 * @return
	 */
	boolean processRytPushAudit(RytPushEvent rytPushEvent);

}
