package com.shangyong.thbase.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.thbase.contants.RedisPrefix;
import com.shangyong.thbase.dao.ThCenterConfigMapper;
import com.shangyong.thbase.dao.ThParamConfigMapper;
import com.shangyong.thbase.dao.ThProductConfigMapper;
import com.shangyong.thbase.service.ConfigService;
import com.shangyong.thcore.vo.CenterConfigVo;
import com.shangyong.thcore.vo.ParamConfigVo;
import com.shangyong.thcore.vo.ProductConfigVo;

@Service
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private ThCenterConfigMapper thCenterConfigMapper;

	@Autowired
	private ThProductConfigMapper thProductConfigMapper;

	@Autowired
	private ThParamConfigMapper thParamConfigMapper;

	@SleuthLoggerExclude(excludeOut = true)
	@Cacheable(key = "'" + RedisPrefix.CONFIG_CENTER + "'+#appid", value = "COMMON#600", unless = "#result==null")
	@Override
	public CenterConfigVo getCenterConfigVo(String appid) {
		return thCenterConfigMapper.getCenterConfigVo(appid);
	}

	@SleuthLoggerExclude(excludeOut = true)
	@Cacheable(key = "'" + RedisPrefix.CONFIG_PRODUCT + "'+#appid", value = "COMMON#600", unless = "#result==null")
	@Override
	public ProductConfigVo getProductConfigVo(String appid) {
		return thProductConfigMapper.getProductConfigVo(appid);
	}

	@SleuthLoggerExclude(excludeOut = true)
	@Cacheable(key = "'" + RedisPrefix.CONFIG_PARAM
			+ "'+#appid+':'+#paramKey", value = "COMMON#600", unless = "#result==null")
	@Override
	public ParamConfigVo getParamConfigVo(String appid, String paramKey) {
		return thParamConfigMapper.getParamConfigVo(appid, paramKey);
	}

	@SleuthLoggerExclude(excludeOut = true)
	@Cacheable(key = "'" + RedisPrefix.CONFIG_PARAMS
			+ "'+#appid+':'+#paramKeys", value = "COMMON#600", unless = "#result==null")
	@Override
	public Map<String, ParamConfigVo> getListParamConfigVo(String appid, String paramKeys) {

		String[] arrParamKeys = new String[] { paramKeys };
		if (paramKeys.contains(",")) {
			arrParamKeys = paramKeys.split(",");
		}

		List<ParamConfigVo> paramConfigVoList = thParamConfigMapper.getParamConfigVoList(appid, arrParamKeys);
		if (paramConfigVoList == null || paramConfigVoList.isEmpty()) {
			return null;
		}

		Map<String, ParamConfigVo> map = new HashMap<>();
		for (int i = 0; i < paramConfigVoList.size(); i++) {
			map.put(paramConfigVoList.get(i).getParamKey(), paramConfigVoList.get(i));
		}
		return map;
	}

}
