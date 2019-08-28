package com.shangyong.thcore.event;

import java.io.Serializable;

import com.shangyong.thcore.event.dto.EventHeader;

/**
 * 前置授信失效事件
 * @author caijunjun
 * @date 2019年3月26日
 */
public class PreCreditExpireEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5807266657422418846L;
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
		builder.append("PreCreditExpireEvent [eventHeader=");
		builder.append(eventHeader);
		builder.append("]");
		return builder.toString();
	}
	
	
}
