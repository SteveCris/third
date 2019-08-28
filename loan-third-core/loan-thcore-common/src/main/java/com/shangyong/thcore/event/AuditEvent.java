package com.shangyong.thcore.event;

import java.io.Serializable;

import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.event.dto.EventUserInfo;

/**
 * 审核事件
 * 
 * @author caijunjun
 * @date 2019年3月13日
 */
public class AuditEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -851372410826932842L;

	private EventHeader eventHeader;

	private EventUserInfo eventUserInfo;
	
	private EventFailureResult eventFailureResult;

	public EventHeader getEventHeader() {
		return eventHeader;
	}

	public void setEventHeader(EventHeader eventHeader) {
		this.eventHeader = eventHeader;
	}

	public EventUserInfo getEventUserInfo() {
		return eventUserInfo;
	}

	public void setEventUserInfo(EventUserInfo eventUserInfo) {
		this.eventUserInfo = eventUserInfo;
	}

	public EventFailureResult getEventFailureResult() {
		return eventFailureResult;
	}

	public void setEventFailureResult(EventFailureResult eventFailureResult) {
		this.eventFailureResult = eventFailureResult;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuditEvent [eventHeader=");
		builder.append(eventHeader);
		builder.append(", eventUserInfo=");
		builder.append(eventUserInfo);
		builder.append(", eventFailureResult=");
		builder.append(eventFailureResult);
		builder.append("]");
		return builder.toString();
	}


}
