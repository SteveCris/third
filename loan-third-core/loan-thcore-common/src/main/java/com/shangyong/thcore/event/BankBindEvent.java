package com.shangyong.thcore.event;

import java.io.Serializable;

import com.shangyong.thcore.event.dto.EventBankBind;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;

/**
 * 银行卡绑定事件
 * 
 * @author caijunjun
 * @date 2019年3月13日
 */
public class BankBindEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141247846950881072L;

	private EventHeader eventHeader;

	private EventBankBind eventBankBind;

	private EventFailureResult eventFailureResult;

	public EventHeader getEventHeader() {
		return eventHeader;
	}

	public void setEventHeader(EventHeader eventHeader) {
		this.eventHeader = eventHeader;
	}

	public EventBankBind getEventBankBind() {
		return eventBankBind;
	}

	public void setEventBankBind(EventBankBind eventBankBind) {
		this.eventBankBind = eventBankBind;
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
		builder.append("BankBindEvent [eventHeader=");
		builder.append(eventHeader);
		builder.append(", eventBankBind=");
		builder.append(eventBankBind);
		builder.append(", eventFailureResult=");
		builder.append(eventFailureResult);
		builder.append("]");
		return builder.toString();
	}

}
