package com.shangyong.thryt.bo;

import java.util.Date;

public class RytOrderLinkBo {

    private Date createTime;
    
    private String ext1;

    private String ext2;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RytOrderLinkBo [createTime=");
		builder.append(createTime);
		builder.append(", ext1=");
		builder.append(ext1);
		builder.append(", ext2=");
		builder.append(ext2);
		builder.append("]");
		return builder.toString();
	}
    
    
}
