package com.shangyong.thorder.config;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shangyong.center.autoconfigure.properties.CenterClientProperties;
import com.shangyong.center.client.CenterClientProcess;
import com.shangyong.common.entity.RestResult;
import com.shangyong.rest.feign.BaseCloudHystrixService;
import com.shangyong.thcore.vo.CenterConfigVo;

@Component
public class MyCenterClientProcess implements CenterClientProcess {

	
	@Autowired
	private BaseCloudHystrixService baseCloudHystrixService;	
 
	@Override
	public CenterClientProperties produce(String appid) {
		RestResult<CenterConfigVo> restResult= baseCloudHystrixService.getCenterConfigVo(appid);
		if(restResult==null) {
			return null;
		}
		
		CenterClientProperties centerClientProperties = new CenterClientProperties();
		BeanUtils.copyProperties(restResult.getData().getBody(), centerClientProperties);
		return centerClientProperties;
	}

}
