package com.shangyong.thorder.service.process;

import com.shangyong.center.dto.CompositeBehindDto;
import com.shangyong.center.dto.CompositeDto;
import com.shangyong.center.vo.CompositeVo;
import com.shangyong.center.vo.ContractApplyVo;
import com.shangyong.thcore.vo.ProductConfigVo;

public interface CreditProcess {

	/**
	 * 授信借款 前置申请
	 * 
	 * @param appid
	 * @param orderId
	 * @param compositeDto
	 * @return
	 */
	CompositeVo applyBefore(String appid, String orderId, CompositeDto compositeDto);

	/**
	 * 授信借款 后置申请
	 * 
	 * @param appid
	 * @param orderId
	 * @param compositeBehindDto
	 * @return
	 */
	CompositeVo applyBehind(String appid, String orderId, CompositeBehindDto compositeBehindDto);

	/**
	 * 保全方法
	 * 
	 * @param appid
	 * @param orderId
	 * @param index
	 * @param sceneId
	 * @param productConfigVo
	 * @return
	 */
	ContractApplyVo safety(String appid, String orderId, int index, int sceneId, ProductConfigVo productConfigVo);

}
