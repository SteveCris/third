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

import com.shangyong.common.entity.RestResult;
import com.shangyong.rest.feign.OrderBorrowCloudHystrixService.HystrixClientFallbackFactory;
import com.shangyong.thcore.dto.OrderBorrowH5Dto;
import com.shangyong.thcore.vo.OrderBorrowH5Vo;
import com.shangyong.thcore.vo.OrderBorrowVo;

import feign.hystrix.FallbackFactory;

/**
 * 订单借款服务模块
 * 
 * @author caijunjun
 * @date 2019年3月15日
 */
@FeignClient(name = "api-thorder", path = "/thorder/orderBorrow", fallbackFactory = HystrixClientFallbackFactory.class)
public interface OrderBorrowCloudHystrixService {

	/**
	 * 获取订单借款确认信息
	 * 
	 * @param appid
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/{appid}/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<OrderBorrowVo> orderBorrowSearch(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId);

	/**
	 * 获得前置授信 h5链接
	 * 
	 * @param appid
	 * @param orderId
	 * @param orderBorrowH5Dto
	 * @return
	 */
	@RequestMapping(value = "/{appid}/sureorderH5Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<OrderBorrowH5Vo> sureorderH5Search(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId, @RequestBody OrderBorrowH5Dto orderBorrowH5Dto);

	/**
	 * 获得后置授信 h5链接
	 * 
	 * @param appid
	 * @param orderId
	 * @param orderBorrowH5Dto
	 * @return
	 */
	@RequestMapping(value = "/{appid}/withdrawH5Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<OrderBorrowH5Vo> withdrawH5Search(@PathVariable("appid") String appid,
			@RequestParam("orderId") String orderId, @RequestBody OrderBorrowH5Dto orderBorrowH5Dto);

	public static class HystrixClientFallbackFactory implements FallbackFactory<OrderBorrowCloudHystrixService> {

		private Logger logger = LoggerFactory.getLogger(HystrixClientFallbackFactory.class);

		@Override
		public OrderBorrowCloudHystrixService create(Throwable cause) {
			return new OrderBorrowCloudHystrixService() {

				@Override
				public RestResult<OrderBorrowH5Vo> sureorderH5Search(String appid, String orderId,
						OrderBorrowH5Dto orderBorrowH5Dto) {
					logger.error(
							"error cause orderBorrowCreditCloudHystrixService sureorderH5Search appid:{};orderId:{};orderBorrowH5Dto:{}",
							appid, orderId, orderBorrowH5Dto, cause);
					return null;
				}

				@Override
				public RestResult<OrderBorrowH5Vo> withdrawH5Search(String appid, String orderId,
						OrderBorrowH5Dto orderBorrowH5Dto) {
					logger.error(
							"error cause orderBorrowCreditCloudHystrixService withdrawH5Search appid:{};orderId:{};orderBorrowH5Dto:{}",
							appid, orderId, orderBorrowH5Dto, cause);
					return null;
				}

				@Override
				public RestResult<OrderBorrowVo> orderBorrowSearch(String appid, String orderId) {

					logger.error(
							"error cause orderBorrowCreditCloudHystrixService orderBorrowSearch appid:{};orderId:{}",
							appid, orderId, cause);
					return null;
				}

			};
		}

	}
}
