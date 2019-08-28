package com.shangyong.thcore.event;

import java.io.Serializable;

import com.shangyong.thcore.event.dto.EventSign;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;

/**
 * 订单授信事件
 * 
 * @author caijunjun
 * @date 2019年3月13日
 */
public class CreditEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2446399491237378324L;

	private EventHeader eventHeader;

	private EventSign eventSign;

	private EventFailureResult eventFailureResult;

	public EventHeader getEventHeader() {
		return eventHeader;
	}

	public void setEventHeader(EventHeader eventHeader) {
		this.eventHeader = eventHeader;
	}

	public EventSign getEventCredit() {
		return eventSign;
	}

	public void setEventCredit(EventSign eventSign) {
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
		builder.append("CreditEvent [eventHeader=");
		builder.append(eventHeader);
		builder.append(", eventCredit=");
		builder.append(eventSign);
		builder.append(", eventFailureResult=");
		builder.append(eventFailureResult);
		builder.append("]");
		return builder.toString();
	}

}
