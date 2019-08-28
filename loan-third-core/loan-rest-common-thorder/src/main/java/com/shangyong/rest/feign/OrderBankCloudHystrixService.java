package com.shangyong.rest.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangyong.common.entity.RestResult;
import com.shangyong.rest.feign.OrderBankCloudHystrixService.HystrixClientFallbackFactory;
import com.shangyong.thcore.dto.OrderBankH5Dto;
import com.shangyong.thcore.vo.OrderBankH5Vo;
import com.shangyong.thcore.vo.OrderBankVo;

import feign.hystrix.FallbackFactory;

/**
 * 订单银行卡服务模块
 * 
 * @author caijunjun
 * @date 2019年3月15日
 */
@FeignClient(name = "api-thorder", path = "/thorder/orderBank", fallbackFactory = HystrixClientFallbackFactory.class)
public interface OrderBankCloudHystrixService {

	/**
	 * 获得银行绑定h5链接
	 * 
	 * @param appid
	 * @param orderId
	 * @param orderBankH5Dto
	 * @return
	 */
	@RequestMapping(value = "/{appid}/bindH5Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<OrderBankH5Vo> bindH5Search(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId, @RequestBody OrderBankH5Dto orderBankH5Dto);

	/**
	 * 查询订单绑定银行卡信息
	 * 
	 * @param appid
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{appid}/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<OrderBankVo> orderBankSearch(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId);

	public static class HystrixClientFallbackFactory implements FallbackFactory<OrderBankCloudHystrixService> {

		private Logger logger = LoggerFactory.getLogger(HystrixClientFallbackFactory.class);

		@Override
		public OrderBankCloudHystrixService create(Throwable cause) {
			return new OrderBankCloudHystrixService() {

				@Override
				public RestResult<OrderBankH5Vo> bindH5Search(String appid, String orderId,
						OrderBankH5Dto orderBankH5Dto) {
					logger.error(
							"error cause orderBankCloudHystrixService bindH5Search appid:{};orderId:{};orderBankH5Dto:{}",
							appid, orderId, orderBankH5Dto, cause);
					return null;
				}

				@Override
				public RestResult<OrderBankVo> orderBankSearch(String appid, String orderId) {
					logger.error("error cause orderBankCloudHystrixService orderBankSearch appid:{};orderId:{}", appid,
							orderId, cause);
					return null;
				}

			};
		}

	}
}
