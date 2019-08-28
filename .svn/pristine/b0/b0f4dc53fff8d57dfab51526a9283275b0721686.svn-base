package com.shangyong.thcore.bussiness.event;

import java.io.Serializable;

import com.shangyong.thcore.bussiness.event.dto.EventBorrow;
import com.shangyong.thcore.event.dto.EventHeader;

/**
 * 借款业务事件
 * 
 * @author caijunjun
 * @date 2019年3月13日
 */
public class BorrowEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3393048166077068679L;

	private EventHeader eventHeader;

	private EventBorrow eventBorrow;

	public EventHeader getEventHeader() {
		return eventHeader;
	}

	public void setEventHeader(EventHeader eventHeader) {
		this.eventHeader = eventHeader;
	}

	public EventBorrow getEventBorrow() {
		return eventBorrow;
	}

	public void setEventBorrow(EventBorrow eventBorrow) {
		this.eventBorrow = eventBorrow;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BorrowEvent [eventHeader=");
		builder.append(eventHeader);
		builder.append(", eventBorrow=");
		builder.append(eventBorrow);
		builder.append("]");
		return builder.toString();
	}

}
