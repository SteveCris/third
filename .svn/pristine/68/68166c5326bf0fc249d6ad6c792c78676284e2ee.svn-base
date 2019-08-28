package com.shangyong.thorder.service.process;

import com.shangyong.center.dto.CompositeDto;
import com.shangyong.center.vo.CompositeVo;
import com.shangyong.center.vo.ContractApplyVo;
import com.shangyong.thcore.vo.ProductConfigVo;

public interface CompositeCreditProcess {

	/**
	 * 授信借款 前置申请
	 * 
	 * @param appid
	 * @param orderId
	 * @param compositeDto
	 * @return
	 */
	CompositeVo apply(String appid, String orderId, CompositeDto compositeDto);

	/**
	 * 保全方法
	 * 
	 * @param appid
	 * @param orderId
	 * @param type
	 * @param sceneId
	 * @param productConfigVo
	 * @return
	 */
	ContractApplyVo safety(String appid, String orderId, int type, int sceneId, ProductConfigVo productConfigVo);

}
