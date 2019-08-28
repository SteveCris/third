package com.shangyong.thorder.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.shangyong.center.client.CenterClientFactory;
import com.shangyong.center.dto.SceneSuperDto;
import com.shangyong.center.vo.CenterSceneCommonVo;
import com.shangyong.center.vo.CenterSceneInsuranceVo;
import com.shangyong.center.vo.CenterSceneVo;
import com.shangyong.center.vo.SceneSuperVo;
import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.thorder.contants.RedisPrefix;
import com.shangyong.thorder.service.OrderRuleService;

@Service
public class OrderRuleServiceImpl implements OrderRuleService {

	@Autowired
	private CenterClientFactory centerClientFactory;

	@SleuthLoggerExclude(excludeInput = false, excludeOut = true)
	@Cacheable(key = "'" + RedisPrefix.CONFIG_SCENE_SUPER
			+ "'+#appid+':'+#limit", value = "COMMON#600", unless = "#result==null")
	@Override
	public List<SceneSuperVo> contractSceneSuper(String appid, BigDecimal limit) {
		SceneSuperDto sceneSuperDto = new SceneSuperDto();
		sceneSuperDto.setLimit(limit);
		return centerClientFactory.getCenterClient(appid).contractSceneSuper(sceneSuperDto);
	}

	@Override
	@Cacheable(key = "'" + RedisPrefix.CONFIG_SCENE
			+ "'+#appid+':'+#sceneId", value = "COMMON#600", unless = "#result==null")
	@SleuthLoggerExclude(excludeInput = false, excludeOut = true)
	public List<CenterSceneVo> listCenterSceneVo(String appid, int sceneId) {
		return centerClientFactory.getCenterClient(appid).listScene(sceneId);
	}

	@Override
	@SleuthLoggerExclude
	public CenterSceneVo getCenterSceneVo(List<CenterSceneVo> centerSceneVoList, BigDecimal creditLine) {
		for (CenterSceneVo centerSceneVo : centerSceneVoList) {
			if (centerSceneVo.getPrice().compareTo(creditLine) == 0) {
				return centerSceneVo;
			}
		}
		return null;
	}

	@Override
	@Cacheable(key = "'" + RedisPrefix.CONFIG_SCENED
			+ "'+#appid+':'+#sCode", value = "COMMON#600", unless = "#result==null")
	@SleuthLoggerExclude(excludeInput = false, excludeOut = true)
	public List<CenterSceneInsuranceVo> listCenterSceneInsuranceVo(String appid, String sCode) {
		return centerClientFactory.getCenterClient(appid).listSceneInsurance(sCode);
	}

	@Override
	@Cacheable(key = "'" + RedisPrefix.CONFIG_SCENED_B
			+ "'+#appid+':'+#subType+':'+#sCode", value = "COMMON#600", unless = "#result==null")
	@SleuthLoggerExclude(excludeInput = false, excludeOut = true)
	public List<CenterSceneCommonVo> listSceneCommonVo(String appid, int subType, String sCode) {
		return centerClientFactory.getCenterClient(appid).listSceneCommon(subType, sCode);
	}
}
