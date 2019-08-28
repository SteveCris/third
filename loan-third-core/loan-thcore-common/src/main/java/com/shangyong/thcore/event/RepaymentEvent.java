package com.shangyong.thcore.event;

import java.io.Serializable;

import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.event.dto.EventRepayment;

/**
 * 还款事件
 * 
 * @author caijunjun
 * @date 2019年3月13日
 */
public class RepaymentEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1430998032471595775L;
	private EventHeader eventHeader;

	private EventRepayment eventRepayment;

	private EventFailureResult eventFailureResult;

	public EventHeader getEventHeader() {
		return eventHeader;
	}

	public void setEventHeader(EventHeader eventHeader) {
		this.eventHeader = eventHeader;
	}

	public EventRepayment getEventRepayment() {
		return eventRepayment;
	}

	public void setEventRepayment(EventRepayment eventRepayment) {
		this.eventRepayment = eventRepayment;
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
		builder.append("RepaymentEvent [eventHeader=");
		builder.append(eventHeader);
		builder.append(", eventRepayment=");
		builder.append(eventRepayment);
		builder.append(", eventFailureResult=");
		builder.append(eventFailureResult);
		builder.append("]");
		return builder.toString();
	}

}
