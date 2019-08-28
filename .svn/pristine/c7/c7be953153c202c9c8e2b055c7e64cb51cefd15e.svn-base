package com.shangyong.interact.utils;

import org.springframework.util.StringUtils;

import com.shangyong.interact.exception.ParamException;

public class AssertUtils {

	private AssertUtils() {

	}

	/**
	 * 断言入参不能为空
	 * 
	 * @param param
	 *            传入参数
	 * @param msg
	 *            异常信息
	 */
	public static void notNullOrBlank(Object param, String msg) {
		if (param == null) {
			throw new ParamException(msg);
		}
		if (param instanceof String && !StringUtils.hasText((String) param)) {
			throw new ParamException(msg);
		}
	}

	
	/**
	 * 判断是否相等
	 * @param obj1
	 * @param obj2
	 * @param msg
	 */
	public static void isEquals(Object obj1, Object obj2, String msg) {
		notNullOrBlank(obj1, "第一个参数不能为空");
		notNullOrBlank(obj2, "第二个参数不能为空");
		if (!obj1.equals(obj2)) {
			throw new ParamException(msg);
		}
	}

	/**
	 * 断言分页参数在正常范围
	 * 
	 * @param start
	 * @param pageSize
	 */
	public static void pageInRange(int start, int pageNum) {
		if (!(start >= 0 && pageNum > 0 && pageNum < 200)) {
			throw new ParamException(
					"start->" + start + "，pageSize->" + pageNum + "：分页参数start，pageNum必须大于0，且pageSize小于200");
		}
	}

	/**
	 * 断言分页参数在正常范围
	 * 
	 * @param start
	 * @param pageSize
	 */
	public static void pageInRange(PageDto page) {
		if (page.getPageNum() == null || page.getPageSize() == null) {
			throw new ParamException("分页参数pageNum和pageSize不能为空");
		}
		if (!(page.getPageNum() > 0 && page.getPageNum() < 200)) {
			throw new ParamException("pageNum->" + page.getPageNum() + "，pageNum->" + page.getPageSize()
					+ "：分页参数pageNum，pageSize必须大于0，且pageNum小于200");
		}
	}

}
