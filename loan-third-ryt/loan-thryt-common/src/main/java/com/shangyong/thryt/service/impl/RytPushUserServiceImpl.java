package com.shangyong.thryt.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.common.entity.RestResult;
import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.rest.feign.OrderCloudHystrixService;
import com.shangyong.thcore.dto.OrderLoanDto;
import com.shangyong.thcore.vo.OrderLoanVo;
import com.shangyong.thryt.contants.CoreContants;
import com.shangyong.thryt.entity.RytOrder;
import com.shangyong.thryt.event.RytPushEvent;
import com.shangyong.thryt.send.Sender;
import com.shangyong.thryt.service.MongodbService;
import com.shangyong.thryt.service.RytPushUserService;
import com.shangyong.thryt.service.RytOrderService;
import com.shangyong.thryt.utils.JacksonUtil;
import com.shangyong.thryt.utils.RytUtil;

@Service
public class RytPushUserServiceImpl implements RytPushUserService {

	private Logger logger = LoggerFactory.getLogger(RytPushUserServiceImpl.class);

	@Autowired
	private Sender sender;

	@Autowired
	private MongodbService mongodbService;

	@Autowired
	private RytOrderService rytOrderService;

	@Autowired
	private OrderCloudHystrixService orderCloudHystrixService;

	@SleuthLoggerExclude(excludeInput = true, excludeOut = false)
	@Override
	public boolean accept(String orderNo, ObjectNode data) {

		// 判断订单存在否
		if (rytOrderService.isExist(orderNo)) {
			logger.info("订单已经存在，重复推送，订单号：{}", orderNo);
			return true;
		}

		// 创建远程订单
		OrderLoanDto orderLoanDto = new OrderLoanDto();
		orderLoanDto.setOtherOrderId(orderNo);
		// TODO 本地缓存调用成功结果
		RestResult<OrderLoanVo> result = orderCloudHystrixService.orderCreate(RytUtil.getAppid(), orderLoanDto);
		if (result == null || !result.isSuccess()) {
			logger.info("订单系统服务异常请稍候再试：{}", orderNo);
			return false;
		}

		OrderLoanVo orderLoanVo = result.getData().getBody();
		// 创建 mongodb订单
		if (mongodbService.saveData(CoreContants.PUSH_COLLECTION, orderNo, data)) {
			// 创建本地订单
			RytOrder rytOrder = JacksonUtil.parseWithSnakeCase(data.get("order_info"), RytOrder.class);
			rytOrder.setIfFinish(false);
			rytOrder.setCreateTime(new Date());
			rytOrder.setStatus(0);
			rytOrder.setOrderId(orderLoanVo.getOrderId());
			rytOrderService.create(rytOrder);
			RytPushEvent rytPushEvent = new RytPushEvent();
			rytPushEvent.setOrderNo(orderNo);
			sender.sendMq("/third", "ex.push.ryt.time", "push.rKey", rytPushEvent, true);
			return true;
		} else {
			return false;
		}
	}

}
