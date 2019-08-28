package com.shangyong.thcore.event;

import java.io.Serializable;

import com.shangyong.thcore.event.dto.EventActualLoan;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;

/**
 * 放款事件
 * 
 * @author caijunjun
 * @date 2019年3月13日
 */
public class ActualLoanEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -789425623567145683L;

	private EventHeader eventHeader;

	private EventFailureResult eventFailureResult;

	private EventActualLoan eventActualLoan;

	public EventHeader getEventHeader() {
		return eventHeader;
	}

	public void setEventHeader(EventHeader eventHeader) {
		this.eventHeader = eventHeader;
	}

	public EventFailureResult getEventFailureResult() {
		return eventFailureResult;
	}

	public void setEventFailureResult(EventFailureResult eventFailureResult) {
		this.eventFailureResult = eventFailureResult;
	}

	public EventActualLoan getEventActualLoan() {
		return eventActualLoan;
	}

	public void setEventActualLoan(EventActualLoan eventActualLoan) {
		this.eventActualLoan = eventActualLoan;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActualLoanEvent [eventHeader=");
		builder.append(eventHeader);
		builder.append(", eventFailureResult=");
		builder.append(eventFailureResult);
		builder.append(", eventActualLoan=");
		builder.append(eventActualLoan);
		builder.append("]");
		return builder.toString();
	}

}
