package com.shangyong.thorder.service;

import java.math.BigDecimal;
import java.util.List;

import com.shangyong.center.vo.CenterSceneCommonVo;
import com.shangyong.center.vo.CenterSceneInsuranceVo;
import com.shangyong.center.vo.CenterSceneVo;
import com.shangyong.center.vo.SceneSuperVo;

public interface OrderRuleService {

	/**
	 * 获取场景列表
	 * 
	 * @param appid
	 * @param sceneId
	 * @return
	 */
	List<CenterSceneVo> listCenterSceneVo(String appid, int sceneId);

	/**
	 * 获得单个场景
	 * 
	 * @param centerSceneVoList
	 * @param creditLine
	 * @return
	 */
	CenterSceneVo getCenterSceneVo(List<CenterSceneVo> centerSceneVoList, BigDecimal creditLine);

	/**
	 * 获取保险场景列表详情信息
	 * 
	 * @param appid
	 * @param sCode
	 * @return
	 */
	List<CenterSceneInsuranceVo> listCenterSceneInsuranceVo(String appid, String sCode);

	/**
	 * 获取场景大类列表
	 * 
	 * @param appid
	 * @param limit
	 * @return
	 */
	List<SceneSuperVo> contractSceneSuper(String appid, BigDecimal limit);

	/**
	 * 获取通用场景列表详情信息
	 * 
	 * @param appid
	 * @param subType
	 * @param sCode
	 * @return
	 */
	List<CenterSceneCommonVo> listSceneCommonVo(String appid, int subType, String sCode);

}
