package com.shangyong.thcore.vo;

/**
 * 状态异常对象
 * @author caijunjun
 * @date 2019年3月28日
 */
public class StatusErrorVo {
	
	private int pointer;
	
	private String description;

	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StatusErrorVo [pointer=");
		builder.append(pointer);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	
	

}
