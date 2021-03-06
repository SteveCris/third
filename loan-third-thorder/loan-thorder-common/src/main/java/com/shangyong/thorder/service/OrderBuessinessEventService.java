package com.shangyong.thorder.service;

import com.shangyong.thcore.bussiness.event.SafeEvent;
import com.shangyong.thcore.bussiness.event.dto.EventBorrow;
import com.shangyong.thcore.event.dto.EventHeader;

/**
 * 订单业务事件处理
 * 
 * @author caijunjun
 * @date 2019年3月25日
 */
public interface OrderBuessinessEventService {

	/**
	 * 处理借款事件
	 * 
	 * @param eventHeader
	 * @param eventBorrow
	 * @return
	 */
	boolean processBorrowEvent(EventHeader eventHeader, EventBorrow eventBorrow);

	/**
	 * 处理前置保全事件
	 * 
	 * @param eventHeader
	 * @param safeEvent
	 * @return
	 */
	boolean processPreSafeEvent(EventHeader eventHeader, SafeEvent safeEvent);

	/**
	 * 处理后置保全事件
	 * 
	 * @param eventHeader
	 * @param safeEvent
	 * @return
	 */
	boolean processSafeEvent(EventHeader eventHeader, SafeEvent safeEvent);

	/**
	 * 处理复合保全事件
	 * 
	 * @param eventHeader
	 * @param safeEvent
	 * @return
	 */
	boolean processCompositeSafeEvent(EventHeader eventHeader, SafeEvent safeEvent);

}
