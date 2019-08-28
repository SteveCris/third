package com.shangyong.thcore.event;

import java.io.Serializable;

import com.shangyong.thcore.event.dto.EventHeader;

/**
 * 订单取消事件
 * @author caijunjun
 * @date 2019年3月13日
 */
public class CancelEvent implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4978215221827633036L;
	private EventHeader eventHeader;

	public EventHeader getEventHeader() {
		return eventHeader;
	}

	public void setEventHeader(EventHeader eventHeader) {
		this.eventHeader = eventHeader;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OverdueEvent [eventHeader=");
		builder.append(eventHeader);
		builder.append("]");
		return builder.toString();
	}
	
}

