package com.shangyong.rest.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangyong.common.entity.RestResult;
import com.shangyong.rest.feign.OrderUserCloudHystrixService.HystrixClientFallbackFactory;

import feign.hystrix.FallbackFactory;

/**
 * 订单用户服务模块
 * 
 * @author caijunjun
 * @date 2019年3月15日
 */
@FeignClient(name = "api-thorder", path = "/thorder/orderUser", fallbackFactory = HystrixClientFallbackFactory.class)
public interface OrderUserCloudHystrixService {

	@ResponseBody
	@RequestMapping(value = "/{appid}/checkOlder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<Void> checkOlder(@PathVariable("appid") String appid,
			@RequestParam("identityNo") String identityNo);

	public static class HystrixClientFallbackFactory implements FallbackFactory<OrderUserCloudHystrixService> {

		private Logger logger = LoggerFactory.getLogger(HystrixClientFallbackFactory.class);

		@Override
		public OrderUserCloudHystrixService create(Throwable cause) {
			return new OrderUserCloudHystrixService() {

				@Override
				public RestResult<Void> checkOlder(String appid, String identityNo) {
					logger.error("error cause orderUserCloudHystrixService checkOlder appid:{};identityNo:{}", appid,
							identityNo, cause);
					return null;
				}

			};
		}

	}
}
