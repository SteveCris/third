package com.shangyong.thzlqb.service.impl;

import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.shangyong.common.entity.RestResult;
import com.shangyong.loan.ext.exception.CalfException;
import com.shangyong.loan.ext.util.JsonNodeUtil;
import com.shangyong.loan.ext.util.ResultUtil;
import com.shangyong.rest.feign.OrderBorrowCloudHystrixService;
import com.shangyong.rest.feign.OrderRepaymentCloudHystrixService;
import com.shangyong.thcore.dto.OrderBorrowH5Dto;
import com.shangyong.thcore.vo.OrderBorrowH5Vo;
import com.shangyong.thcore.vo.OrderLoanVo;
import com.shangyong.thcore.vo.OrderRepaymentPlanVo;
import com.shangyong.thzlqb.entity.ZlqbOrderReview;
import com.shangyong.thzlqb.enums.ZlqbResultEnum;
import com.shangyong.thzlqb.service.CoreOrderService;
import com.shangyong.thzlqb.service.ZlqbContractService;
import com.shangyong.thzlqb.service.ZlqbOrderReviewService;
import com.shangyong.thzlqb.service.ZlqbOrderStatusService;
import com.shangyong.thzlqb.utils.DateUtil;
import com.shangyong.thzlqb.utils.OrderUtil;
import com.shangyong.thzlqb.zlqb.utils.ZlqbUtil;

@Service
public class ZlqbContractServiceImpl implements ZlqbContractService {

	@Autowired
	private OrderBorrowCloudHystrixService orderBorrowCloudHystrixService;

	@Autowired
	private CoreOrderService coreOrderService;

	@Autowired
	private ZlqbOrderStatusService zlqbOrderStatusService;


	@Autowired
	private OrderRepaymentCloudHystrixService orderRepaymentCloudHystrixService;

	@Autowired
	private ZlqbOrderReviewService reviewService;

	@Override
	public JsonNode getSignInfo(ObjectNode request) {

		ZlqbOrderReview zlqbOrderReview = reviewService.getOrderReviewDto(request.get("orderNo").asText());
		if(Objects.isNull(zlqbOrderReview)){
			return JsonNodeUtil.data().put("orderNo", request.get("orderNo").asText()).set("signInfors",
					JsonNodeUtil.arrayData());
		}
		OrderLoanVo orderLoanVo = coreOrderService.getRemoteOrder(zlqbOrderReview.getOrderNo());
		if (Objects.isNull(orderLoanVo)) {
			throw new CalfException(ZlqbResultEnum.NULL_ERROR.getMessage());
		}

		RestResult<OrderRepaymentPlanVo> pilotcalculation = orderRepaymentCloudHystrixService.pilotcalculation(ZlqbUtil.getAppid(), orderLoanVo.getOrderId());

		OrderRepaymentPlanVo haveRepayMent = ResultUtil.checkAndGet(pilotcalculation);
		if(Objects.isNull(haveRepayMent)){
			throw new CalfException(ZlqbResultEnum.NULL_ERROR.getMessage());
		}
		String approveDate = zlqbOrderReview.getApproveDate();
		if(StringUtils.isEmpty(approveDate)){
			return JsonNodeUtil.data().put("orderNo", request.get("orderNo").asText()).set("signInfors",
					JsonNodeUtil.arrayData());
		}
		return JsonNodeUtil.data().put("orderNo", request.get("orderNo").asText()).set("signInfors",
				JsonNodeUtil.arrayData().add(JsonNodeUtil.data().put("number",zlqbOrderReview.getLoanPeriod())//
						.put("repay_money",coverDto2Money(haveRepayMent))//
						.put("repay_date", DateUtil.plusDaysDate(approveDate,zlqbOrderReview.getLoanTerms()-1))));
	}


	@Override
	public int signContract(ObjectNode request) {
		String orderNo = request.get("orderNo").asText();
		OrderLoanVo orderLoanVo = coreOrderService.getRemoteOrder(orderNo);
		// 如果绑卡成功
		if (OrderUtil.isBindSuccess(orderLoanVo)) {
			boolean flag = coreOrderService.updateOrderisSign(orderNo);
			if (flag) {
				zlqbOrderStatusService.pushOrder(orderNo);
				return 1;
			}
			return 2;
		} else {
			return 3;
		}

	}

	@Override
	public JsonNode getCreditH5(ObjectNode request) {

		OrderBorrowH5Dto orderBorrowH5Dto = new OrderBorrowH5Dto();
		orderBorrowH5Dto.setAppName(ZlqbUtil.getAppName() + "");
		orderBorrowH5Dto.setSuccessReturnUrl(request.get("returnUrl").asText());
		orderBorrowH5Dto.setFailReturnUrl(request.get("returnUrl").asText());

		String orderNo = request.get("orderNo").asText();
		String url;
		OrderLoanVo orderLoanVo = coreOrderService.getRemoteOrder(orderNo);
		if (Objects.isNull(orderLoanVo)) {
			throw new CalfException("该订单开户时查询订单记录时返回空值");
		}
		if (OrderUtil.isWaitPreCredit(orderLoanVo)) {
			RestResult<OrderBorrowH5Vo> result = orderBorrowCloudHystrixService.sureorderH5Search(ZlqbUtil.getAppid(),
					orderLoanVo.getOrderId(), orderBorrowH5Dto);
			OrderBorrowH5Vo orderBorrowH5Vo = ResultUtil.checkAndGet(result);
			url = orderBorrowH5Vo.getH5Url();
		} else if (OrderUtil.isWaitCredit(orderLoanVo)) {
			RestResult<OrderBorrowH5Vo> result = orderBorrowCloudHystrixService.withdrawH5Search(ZlqbUtil.getAppid(),
					orderLoanVo.getOrderId(), orderBorrowH5Dto);
			OrderBorrowH5Vo orderBorrowH5Vo = ResultUtil.checkAndGet(result);
			url = orderBorrowH5Vo.getH5Url();
		} else {
			throw new CalfException("状态码不对");
		}
		return new TextNode(url);
	}


	private int coverDto2Money(OrderRepaymentPlanVo obj){
		if(Objects.isNull(obj)){
			return 0;
		}
		return  checkBigdecimalResultNum(obj.getPrincipal())+checkBigdecimalResultNum(obj.getInterestFee());
	}
	private int checkBigdecimalResultNum(BigDecimal value) {
		if(Objects.isNull(value)){
			return 0;
		}
		return value.setScale(2,BigDecimal.ROUND_FLOOR).multiply(new BigDecimal(100)).intValue();
	}

}
