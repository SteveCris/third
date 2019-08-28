package com.shangyong.rest.feign;

import com.shangyong.common.entity.RestResult;
import com.shangyong.rest.feign.BaseUserCloudHystrixService.HystrixClientFallbackFactory;
import com.shangyong.thcore.dto.CheckUserInfoDto;
import com.shangyong.thcore.vo.BaseUserInfoVo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 基础配置服务模块
 * 
 * @author caijunjun
 * @date 2019年3月15日
 */
@FeignClient(name = "api-thbase", path = "/thbase/baseUser", fallbackFactory = HystrixClientFallbackFactory.class)
public interface BaseUserCloudHystrixService {

	@RequestMapping(value = "/{appid}/check/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<BaseUserInfoVo> checkAndSearch(@PathVariable("appid") String appid,@RequestBody CheckUserInfoDto checkUserInfoDto);

	public static class HystrixClientFallbackFactory implements FallbackFactory<BaseUserCloudHystrixService> {

		private Logger logger = LoggerFactory.getLogger(HystrixClientFallbackFactory.class);

		@Override
		public BaseUserCloudHystrixService create(Throwable cause) {
			return new BaseUserCloudHystrixService() {

				@Override
				public RestResult<BaseUserInfoVo> checkAndSearch(String appid,CheckUserInfoDto checkUserInfoDto) {
					logger.error("error cause baseUserCloudHystrixService checkAndSearch appid:{};checkUserInfoDto:{}",appid, checkUserInfoDto, cause);
					return null;
				}

			};
		}

	}
}
