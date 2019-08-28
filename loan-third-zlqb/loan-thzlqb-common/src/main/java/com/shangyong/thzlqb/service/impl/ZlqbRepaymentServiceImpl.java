package com.shangyong.thzlqb.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.shangyong.common.entity.RestResult;
import com.shangyong.loan.ext.exception.CalfException;
import com.shangyong.loan.ext.util.ResultUtil;
import com.shangyong.rest.feign.OrderRepaymentCloudHystrixService;
import com.shangyong.thcore.dto.OrderRepaymentH5Dto;
import com.shangyong.thcore.vo.OrderLoanVo;
import com.shangyong.thcore.vo.OrderRepaymentH5Vo;
import com.shangyong.thzlqb.service.CoreOrderService;
import com.shangyong.thzlqb.service.ZlqbRepaymentService;
import com.shangyong.thzlqb.utils.OrderUtil;
import com.shangyong.thzlqb.zlqb.utils.ZlqbUtil;

import java.util.Objects;

@Service
public class ZlqbRepaymentServiceImpl implements ZlqbRepaymentService {

	private Logger logger = LoggerFactory.getLogger(ZlqbRepaymentServiceImpl.class);

	@Autowired
	private CoreOrderService coreOrderService;

	@Autowired
	private OrderRepaymentCloudHystrixService orderRepaymentCloudHystrixService;

	@Override
	public JsonNode getRepaymentH5(ObjectNode request) {
		String orderNo = request.get("orderNo").asText();
		OrderLoanVo orderLoanVo = coreOrderService.getRemoteOrder(orderNo);
		if(Objects.isNull(orderLoanVo)){
			logger.error("该订单号不存在相关记录信息 订单号 {}",orderNo);
			throw new CalfException("该订单号不存在相关订单记录信息");
		}

		if (!OrderUtil.isWaitRepayment(orderLoanVo)) {
			throw new CalfException("订单状态异常");
		}
		OrderRepaymentH5Dto orderRepaymentH5Dto = new OrderRepaymentH5Dto();
		orderRepaymentH5Dto.setAppName(ZlqbUtil.getAppName()+"");
		orderRepaymentH5Dto.setSuccessReturnUrl(request.get("returnUrl").asText());
		orderRepaymentH5Dto.setFailReturnUrl(request.get("returnUrl").asText());
		RestResult<OrderRepaymentH5Vo> result = orderRepaymentCloudHystrixService.repaymentH5Search(ZlqbUtil.getAppid(),
				orderLoanVo.getOrderId(), orderRepaymentH5Dto);
		logger.info("还款时返回的结果result {}",result);
		return new TextNode(ResultUtil.checkAndGet(result).getH5Url());
	}

}
