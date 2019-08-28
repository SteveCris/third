package com.shangyong.interact.utils;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "分页查询参数定义")
public class PageDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "第几页,从第1页开始", required = true)
	private Integer pageNum;

	@ApiModelProperty(value = "每页多少条记录", required = true)
	private Integer pageSize;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageDto [pageNum=" + pageNum + ", pageSize=" + pageSize + "]";
	}
	
	
}