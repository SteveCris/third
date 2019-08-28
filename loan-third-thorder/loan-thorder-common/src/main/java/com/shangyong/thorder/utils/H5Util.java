package com.shangyong.thorder.utils;

import com.shangyong.thcore.util.ThOrderStateUtils;
import com.shangyong.thcore.vo.StatusErrorVo;

public class H5Util {

	private static String[] descriptions = new String[] { "状态更新中，请稍候再试...", "重复操作!","订单已完结!", "状态异常!" };

	/**
	 * 通用处理
	 * 
	 * @param expectStatus
	 *            期望状态
	 * @param actualStatus
	 *            实际状态
	 * @param descriptions
	 *            描述信息
	 * @return
	 */
	public static StatusErrorVo statusErrorProcess(int expectStatus, int actualStatus) {
		int pointer = ThOrderStateUtils.orderStatusCompare(expectStatus, actualStatus);
		String description;
		if (pointer == 0) {
			return null;
		} else if (pointer == -1) {
			description = descriptions[0];
		} else if (pointer == 1) {
			description = descriptions[1];
		} else if (pointer == 1000) {
			description = descriptions[2];
		} else {
			description = descriptions[3];
		}
		StatusErrorVo statusErrorVo = new StatusErrorVo();
		statusErrorVo.setDescription(description);
		statusErrorVo.setPointer(pointer);
		return statusErrorVo;
	}

}
