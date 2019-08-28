package com.shangyong.thcore.bussiness.event;

import java.io.Serializable;

import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.event.dto.EventSafe;

/**
 * 保全业务事件
 * 
 * @author caijunjun
 * @date 2019年3月13日
 */
public class SafeEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -517796206727023707L;

	private EventHeader eventHeader;

	private EventSafe eventSafe;

	public EventHeader getEventHeader() {
		return eventHeader;
	}

	public void setEventHeader(EventHeader eventHeader) {
		this.eventHeader = eventHeader;
	}

	public EventSafe getEventSafe() {
		return eventSafe;
	}

	public void setEventSafe(EventSafe eventSafe) {
		this.eventSafe = eventSafe;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SafeEvent [eventHeader=");
		builder.append(eventHeader);
		builder.append(", eventSafe=");
		builder.append(eventSafe);
		builder.append("]");
		return builder.toString();
	}

}
