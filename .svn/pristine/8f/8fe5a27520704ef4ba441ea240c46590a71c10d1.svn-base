package com.shangyong.thcore.event;

import java.io.Serializable;

import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.event.dto.EventPreSign;

/**
 * 前置签约事件
 * @author caijunjun
 * @date 2019年3月13日
 */
public class PreSignEvent implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7049145767370308039L;

	private EventHeader eventHeader;

	private EventPreSign eventPreSign;

	private EventFailureResult eventFailureResult;

	public EventHeader getEventHeader() {
		return eventHeader;
	}

	public void setEventHeader(EventHeader eventHeader) {
		this.eventHeader = eventHeader;
	}


	public EventPreSign getEventPreSign() {
		return eventPreSign;
	}

	public void setEventPreSign(EventPreSign eventPreSign) {
		this.eventPreSign = eventPreSign;
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
		builder.append("PreSignEvent [eventHeader=");
		builder.append(eventHeader);
		builder.append(", eventPreSign=");
		builder.append(eventPreSign);
		builder.append(", eventFailureResult=");
		builder.append(eventFailureResult);
		builder.append("]");
		return builder.toString();
	}


}
