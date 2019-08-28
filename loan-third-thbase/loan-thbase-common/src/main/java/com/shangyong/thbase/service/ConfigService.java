package com.shangyong.thbase.service;

import java.util.Map;

import com.shangyong.thcore.vo.CenterConfigVo;
import com.shangyong.thcore.vo.ParamConfigVo;
import com.shangyong.thcore.vo.ProductConfigVo;

public interface ConfigService {

	

	/**
	 * 获得信贷中心相关配置信息
	 * 
	 * @param appid
	 * @return
	 */
	CenterConfigVo getCenterConfigVo(String appid);

	/**
	 * 获得产品相关配置信息
	 * 
	 * @param appid
	 * @return
	 */
	ProductConfigVo getProductConfigVo(String appid);

	/**
	 * 根据参数key获取参数配置
	 * 
	 * @param appid
	 * @param paramKey
	 * @return
	 */
	ParamConfigVo getParamConfigVo(String appid, String paramKey);

	/**
	 * 根据参数kes获取参数配置集合
	 * 
	 * @param appid
	 * @param paramKeys
	 * @return
	 */
	Map<String, ParamConfigVo> getListParamConfigVo(String appid, String paramKeys);

}
