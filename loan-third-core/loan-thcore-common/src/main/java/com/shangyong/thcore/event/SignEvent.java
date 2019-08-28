package com.shangyong.thcore.event;

import java.io.Serializable;

import com.shangyong.thcore.event.dto.EventSign;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;

/**
 * 签约事件
 * @author caijunjun
 * @date 2019年3月13日
 */
public class SignEvent implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6242450816752085083L;

	private EventHeader eventHeader;

	private EventSign eventSign;

	private EventFailureResult eventFailureResult;

	public EventHeader getEventHeader() {
		return eventHeader;
	}

	public void setEventHeader(EventHeader eventHeader) {
		this.eventHeader = eventHeader;
	}

	public EventSign getEventSign() {
		return eventSign;
	}

	public void setEventSign(EventSign eventSign) {
		this.eventSign = eventSign;
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
		builder.append("SignEvent [eventHeader=");
		builder.append(eventHeader);
		builder.append(", eventSign=");
		builder.append(eventSign);
		builder.append(", eventFailureResult=");
		builder.append(eventFailureResult);
		builder.append("]");
		return builder.toString();
	}

}
