package com.shangyong.thryt.service.impl;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.common.entity.RestResult;
import com.shangyong.common.utils.LocalDateUtil;
import com.shangyong.rest.feign.BaseCloudHystrixService;
import com.shangyong.rest.feign.OrderBankCloudHystrixService;
import com.shangyong.thcore.event.dto.EventBankBind;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.event.dto.EventUserInfo;
import com.shangyong.thcore.vo.OrderBankVo;
import com.shangyong.thcore.vo.ProductConfigVo;
import com.shangyong.thryt.bo.RytAuditUserInfoBo;
import com.shangyong.thryt.bo.RytOrderSimpleBo;
import com.shangyong.thryt.enums.RytResultEnum;
import com.shangyong.thryt.exception.CalfException;
import com.shangyong.thryt.service.CallBackService;
import com.shangyong.thryt.service.RytOrderService;
import com.shangyong.thryt.service.RytUserService;
import com.shangyong.thryt.service.ToAppConsumerService;
import com.shangyong.thryt.service.client.RytClient;
import com.shangyong.thryt.utils.JsonNodeUtil;

@Service
public class CallBackServiceImpl implements CallBackService {

	@Autowired
	private RytClient rytClient;

	@Autowired
	private RytUserService rytUserService;

	@Autowired
	private RytOrderService rytOrderService;

	@Autowired
	private BaseCloudHystrixService baseCloudHystrixService;

	@Autowired
	private OrderBankCloudHystrixService orderBankCloudHystrixService;

	@Autowired
	private ToAppConsumerService toAppConsumerService;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processAuditSuccess(EventHeader eventHeader, EventUserInfo eventUserInfo) {

		// 获取订单简要信息
		RytOrderSimpleBo rytOrderSimpleBo = rytOrderService.getRytOrderSimpleBoByOrderId(eventHeader.getOrderId());
		String orderNo = rytOrderSimpleBo.getOrderNo();

		if (!rytOrderService.updateOrderStatus(orderNo, 40, 30, false, null)) {
			return rytOrderService.checkStatus(orderNo, 40);
		}

		String appid = eventHeader.getAppid();
		// 获取产品配置信息
		RestResult<ProductConfigVo> productResult = baseCloudHystrixService.getProductConfigVo(appid);
		if (productResult == null || !productResult.isSuccess()) {
			throw new CalfException(RytResultEnum.REMOTE_ERROR);
		}
		ProductConfigVo productConfigVo = productResult.getData().getBody();

		RytAuditUserInfoBo rytAuditUserInfoBo = rytUserService.getRytAuditUserInfoBo(orderNo);

		Date happenDate = new Date(eventHeader.getHappenTime());
		Date expiredDate = LocalDateUtil.plus(happenDate, Integer.valueOf(productConfigVo.getExt2()), ChronoUnit.DAYS);
		ObjectNode data = JsonNodeUtil.data()//
				.put("mobile", rytAuditUserInfoBo.getUserMobile())//
				.put("conclusion", 10)//
				.put("term_unit", 1)//
				.put("term_type", 0)//
				.put("approval_term", productConfigVo.getCycle())//
				.put("amount_type", 0)//
				.put("approval_amount", productConfigVo.getPrice().multiply(new BigDecimal(100)))//
				.put("approved_at", LocalDateUtil.dateToString(happenDate, "yyyy-MM-dd HH:mm:ss"))//
				.put("expired_at", LocalDateUtil.dateToString(expiredDate, "yyyy-MM-dd HH:mm:ss"))//
				.put("order_no", orderNo);

		rytClient.pushAuditResult(data);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processAuditFailure(EventHeader eventHeader, EventFailureResult eventFailureResult) {

		// 获取订单简要信息
		RytOrderSimpleBo rytOrderSimpleBo = rytOrderService.getRytOrderSimpleBoByOrderId(eventHeader.getOrderId());
		String orderNo = rytOrderSimpleBo.getOrderNo();

		if (!rytOrderService.updateOrderStatus(orderNo, 50, 30, true, null)) {
			return rytOrderService.checkStatus(orderNo, 50);
		}

		RytAuditUserInfoBo rytAuditUserInfoBo = rytUserService.getRytAuditUserInfoBo(orderNo);

		Date happenDate = new Date(eventHeader.getHappenTime());
		Date canLoanTime = LocalDateUtil.plus(happenDate, 1, ChronoUnit.YEARS);
		ObjectNode data = JsonNodeUtil.data()//
				.put("mobile", rytAuditUserInfoBo.getUserMobile())//
				.put("conclusion", 40)//
				.put("remark", "audit error")//
				.put("can_loan_time", LocalDateUtil.dateToString(canLoanTime, "yyyy-MM-dd"));
		rytClient.pushAuditResult(data);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processBankBindSuccess(EventHeader eventHeader, EventBankBind eventBankBind) {

		// 获取订单简要信息
		RytOrderSimpleBo rytOrderSimpleBo = rytOrderService.getRytOrderSimpleBoByOrderId(eventHeader.getOrderId());
		String orderNo = rytOrderSimpleBo.getOrderNo();

		if (!rytOrderService.updateOrderStatus(orderNo, 60, 40, false, null)) {
			return rytOrderService.checkStatus(orderNo, 60);
		}

		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();

		RestResult<OrderBankVo> bankResult = orderBankCloudHystrixService.orderBankSearch(appid, orderId);
		if (bankResult == null || !bankResult.isSuccess()) {
			throw new CalfException(RytResultEnum.REMOTE_ERROR);
		}
		OrderBankVo orderBankVo = bankResult.getData().getBody();

		RytAuditUserInfoBo rytAuditUserInfoBo = rytUserService.getRytAuditUserInfoBo(orderNo);

		ObjectNode objectNode = JsonNodeUtil.data()//
				.put("order_no", orderNo)//
				.put("mobile", rytAuditUserInfoBo.getUserMobile())//
				.put("state", 1)//
				.put("card_no", orderBankVo.getBankCardNo())//
				.put("bank_name", orderBankVo.getBankName())//
				.put("bank_code", orderBankVo.getBankCode())//
				.put("bank_mobile", orderBankVo.getReservedMobile());
		rytClient.pushBankBindResult(objectNode);
		toAppConsumerService.push(orderNo);
		return true;
	}

	@Override
	public boolean processCancelOrder(EventHeader eventHeader) {

		// 获取订单简要信息
		RytOrderSimpleBo rytOrderSimpleBo = rytOrderService.getRytOrderSimpleBoByOrderId(eventHeader.getOrderId());
		String orderNo = rytOrderSimpleBo.getOrderNo();

		if(rytOrderSimpleBo.getStatus()==1000) {
			return true;
		}
		
		if (!rytOrderService.updateOrderStatus(orderNo, 1000, rytOrderSimpleBo.getStatus(), true,
				"订单取消：" + rytOrderSimpleBo.getStatus() + "->1000")) {
			return rytOrderService.checkStatus(orderNo, 1000);
		}

		String happenTime = String.valueOf(eventHeader.getHappenTime());
		ObjectNode objectNode = JsonNodeUtil.data()//
				.put("order_no", orderNo)//
				.put("state", 230)//
				.put("updated_at", happenTime.substring(0, happenTime.length() - 3))//
				.put("receive_amount", 0)//
				.put("pay_amount", 0)//
				.put("remark", "审核失效")//
				.put("reapply_at", LocalDateUtil.dateToString(new Date(), "yyyy-MM-dd"));

		rytClient.pushOrderStatusResult(objectNode);
		return true;
	}

}
