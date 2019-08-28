package com.shangyong.rest.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.shangyong.rest.feign.OrderCreditCloudHystrixService.HystrixClientFallbackFactory;

import feign.hystrix.FallbackFactory;

/**
 * 订单授信服务模块
 * 
 * @author caijunjun
 * @date 2019年3月15日
 */
@FeignClient(name = "api-thorder", path = "/thorder/orderCredit", fallbackFactory = HystrixClientFallbackFactory.class)
public interface OrderCreditCloudHystrixService {


	public static class HystrixClientFallbackFactory implements FallbackFactory<OrderCreditCloudHystrixService> {


		@Override
		public OrderCreditCloudHystrixService create(Throwable cause) {
			return new OrderCreditCloudHystrixService() {

				

			};
		}

	}
}
