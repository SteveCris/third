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
import com.shangyong.rest.feign.OrderCloudHystrixService.HystrixClientFallbackFactory;
import com.shangyong.thcore.dto.OrderLoanDto;
import com.shangyong.thcore.vo.OrderLoanVo;

import feign.hystrix.FallbackFactory;

/**
 * 订单服务模块
 * 
 * @author caijunjun
 * @date 2019年3月15日
 */
@FeignClient(name = "api-thorder", path = "/thorder/order", fallbackFactory = HystrixClientFallbackFactory.class)
public interface OrderCloudHystrixService {

	/**
	 * 创建订单接口
	 * 
	 * @param appid
	 * @param orderLoanDto
	 * @return
	 */
	@RequestMapping(value = "/{appid}/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	RestResult<OrderLoanVo> orderCreate(//
			@PathVariable("appid") String appid, @RequestBody OrderLoanDto orderLoanDto);

	/**
	 * 查询订单
	 * 
	 * @param appid
	 * @param otherOrderId
	 * @return
	 */
	@RequestMapping(value = "/{appid}/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	RestResult<OrderLoanVo> orderSearch(@PathVariable("appid") String appid,
			@RequestParam("otherOrderId") String otherOrderId);

	/**
	 * 在途订单校验
	 * 
	 * @param appid
	 * @param identityNo
	 * @return
	 */
	@RequestMapping(value = "/{appid}/onWay/check", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	RestResult<Void> orderOnWayCheck(@PathVariable("appid") String appid,
			@RequestParam("identityNo") String identityNo);

	/**
	 * 推送订单至待审核
	 * 
	 * @param appid
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/{appid}/toWaitAudit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	RestResult<Void> orderToWaitAudit(@PathVariable("appid") String appid, @RequestParam("orderId") String orderId);

	/**
	 * 取消订单
	 * 
	 * @param appid
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/{appid}/cancel", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	RestResult<Void> orderCancel(@PathVariable("appid") String appid, @RequestParam("orderId") String orderId);

	public static class HystrixClientFallbackFactory implements FallbackFactory<OrderCloudHystrixService> {

		private Logger logger = LoggerFactory.getLogger(HystrixClientFallbackFactory.class);

		@Override
		public OrderCloudHystrixService create(Throwable cause) {
			return new OrderCloudHystrixService() {

				@Override
				public RestResult<OrderLoanVo> orderCreate(String appid, OrderLoanDto orderLoanDto) {
					logger.error("error cause orderCloudHystrixService orderCreate appid:{};orderLoanDto:{}", appid,
							orderLoanDto, cause);
					return null;
				}

				@Override
				public RestResult<OrderLoanVo> orderSearch(String appid, String otherOrderId) {
					logger.error("error cause orderCloudHystrixService ordersearch appid:{};otherOrderId:{}", appid,
							otherOrderId, cause);
					return null;
				}

				@Override
				public RestResult<Void> orderOnWayCheck(String appid, String identityNo) {
					logger.error("error cause orderCloudHystrixService orderOnWayCheck appid:{};identityNo:{}", appid,
							identityNo, cause);
					return null;
				}

				@Override
				public RestResult<Void> orderToWaitAudit(String appid, String orderId) {
					logger.error("error cause orderCloudHystrixService orderToWaitAudit appid:{};orderId:{}", appid,
							orderId, cause);
					return null;
				}

				@Override
				public RestResult<Void> orderCancel(String appid, String orderId) {
					logger.error("error cause orderCloudHystrixService orderCancel appid:{};orderId:{}", appid, orderId,
							cause);
					return null;
				}

			};
		}

	}
}
