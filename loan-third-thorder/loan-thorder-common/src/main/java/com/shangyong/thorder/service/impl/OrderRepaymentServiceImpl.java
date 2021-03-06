package com.shangyong.thorder.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.center.client.CenterClientFactory;
import com.shangyong.center.client.exception.RemoteServerException;
import com.shangyong.center.dto.AlipayRequestDto;
import com.shangyong.center.dto.ApplicationRequestDto;
import com.shangyong.center.dto.RepayRequestDto;
import com.shangyong.center.dto.WeChatRepayDto;
import com.shangyong.center.vo.AlipayQuickRepayVo;
import com.shangyong.center.vo.InsuranceRepaymentPlanInfosVo;
import com.shangyong.center.vo.RepayTypeVo;
import com.shangyong.center.vo.WeChatRepayVo;
import com.shangyong.common.entity.RestResult;
import com.shangyong.common.utils.LocalDateUtil;
import com.shangyong.rest.feign.BaseCloudHystrixService;
import com.shangyong.thcore.bo.OrderActualLoanBo;
import com.shangyong.thcore.bo.OrderRepaymentBo;
import com.shangyong.thcore.vo.OrderActualLoanVo;
import com.shangyong.thcore.vo.OrderBankVo;
import com.shangyong.thcore.vo.OrderRepaymentPlanVo;
import com.shangyong.thcore.vo.OrderRepaymentVo;
import com.shangyong.thcore.vo.ProductConfigVo;
import com.shangyong.thcore.vo.RepaymentShowVo;
import com.shangyong.thcore.vo.RepaymentTypeVo;
import com.shangyong.thorder.dao.OrderActualLoanMapper;
import com.shangyong.thorder.dao.OrderRepaymentMapper;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.exception.CalfException;
import com.shangyong.thorder.service.OrderBankService;
import com.shangyong.thorder.service.OrderCreditService;
import com.shangyong.thorder.service.OrderRepaymentService;

@Service
public class OrderRepaymentServiceImpl implements OrderRepaymentService {

	private Logger logger = LoggerFactory.getLogger(OrderRepaymentServiceImpl.class);

	@Autowired
	private CenterClientFactory centerClientFactory;

	@Autowired
	private OrderActualLoanMapper orderActualLoanMapper;

	@Autowired
	private OrderRepaymentMapper orderRepaymentMapper;

	@Autowired
	private OrderBankService orderBankService;

	@Autowired
	private OrderCreditService orderCreditService;

	@Autowired
	private BaseCloudHystrixService baseCloudHystrixService;

	@Override
	public OrderRepaymentPlanVo pilotcalculation(String appid, String orderId) {
		RestResult<ProductConfigVo> restResult = baseCloudHystrixService.getProductConfigVo(appid);
		if (restResult == null || !restResult.isSuccess()) {
			return null;
		}

		ProductConfigVo productConfigVo = restResult.getData().getBody();

		ApplicationRequestDto applicationRequestDto = new ApplicationRequestDto();

		applicationRequestDto.setAppID(appid);
		applicationRequestDto.setProductCode(productConfigVo.getCode());
		applicationRequestDto.setCycle(productConfigVo.getCycle());
		applicationRequestDto.setRealMoney(productConfigVo.getPrice().toString());
		applicationRequestDto.setInsuranceMoney(productConfigVo.getPrePrice().toString());

		InsuranceRepaymentPlanInfosVo insuranceRepaymentPlanInfosVo = centerClientFactory.getCenterClient(appid)
				.pilotcalculation(applicationRequestDto);
		OrderRepaymentPlanVo orderRepaymentPlanVo = new OrderRepaymentPlanVo();
		BeanUtils.copyProperties(insuranceRepaymentPlanInfosVo.getRepaymentPlanInfo(), orderRepaymentPlanVo);
		
		
		Date applyDate = new Date();
		int days = productConfigVo.getCycle() - 1;
		LocalDate localDate = LocalDateUtil.parse(applyDate).toLocalDate().plus(days, ChronoUnit.DAYS);
		Date repaymentPlanTime = LocalDateUtil.parse(localDate.atStartOfDay());
		String applyDateStr = LocalDateUtil.dateToString(applyDate, "yyyy-MM-dd");
		String repaymentPlanDateStr = LocalDateUtil.dateToString(repaymentPlanTime, "yyyy-MM-dd");
		orderRepaymentPlanVo.setApplyDate(applyDateStr);
		orderRepaymentPlanVo.setRepayDate(repaymentPlanDateStr);
		return orderRepaymentPlanVo;
	}

	@Override
	public OrderRepaymentPlanVo getOrderRepaymentPlanVo(String appid, String orderId) {
		OrderActualLoanVo orderActualLoanVo = orderActualLoanMapper.getOrderActualLoanVo(appid, orderId);
		RepayRequestDto repayRequestDto = new RepayRequestDto();
		repayRequestDto.setApplicationId(orderActualLoanVo.getApplyId());
		repayRequestDto.setStatement(orderActualLoanVo.getFinanceOrderId());
		InsuranceRepaymentPlanInfosVo insuranceRepaymentPlanInfosVo = centerClientFactory.getCenterClient(appid)
				.insuranceRepayplan(repayRequestDto);
		OrderRepaymentPlanVo orderRepaymentPlanVo = new OrderRepaymentPlanVo();
		BeanUtils.copyProperties(insuranceRepaymentPlanInfosVo.getRepaymentPlanInfo(), orderRepaymentPlanVo);

		return orderRepaymentPlanVo;
	}

	@Override
	public OrderRepaymentPlanVo getActualOrderRepaymentPlanVo(String appid, String orderId) {

		OrderRepaymentBo orderRepaymentBo = orderRepaymentMapper.getOrderRepaymentBo(appid, orderId);

		RepayRequestDto repayRequestDto = new RepayRequestDto();
		repayRequestDto.setApplicationId(orderRepaymentBo.getApplyId());
		repayRequestDto.setStatement(orderRepaymentBo.getFinanceOrderId());
		InsuranceRepaymentPlanInfosVo insuranceRepaymentPlanInfosVo = centerClientFactory.getCenterClient(appid)
				.repaysuccessRepayplan(repayRequestDto);
		OrderRepaymentPlanVo orderRepaymentPlanVo = new OrderRepaymentPlanVo();
		BeanUtils.copyProperties(insuranceRepaymentPlanInfosVo.getRepaymentPlanInfo(), orderRepaymentPlanVo);
		orderRepaymentPlanVo
				.setActualrepayDate(LocalDateUtil.dateToString(orderRepaymentBo.getCreateTime(), "yyyy-MM-dd"));
		orderRepaymentPlanVo.setIfOverdue(orderRepaymentBo.isIfOverdue());
		return orderRepaymentPlanVo;
	}

	@Override
	public OrderRepaymentVo getOrderRepaymentVo(String appid, String orderId) {
		// 获取放款信息
		OrderActualLoanBo orderActualLoanBo = orderActualLoanMapper.getOrderActualLoanBo(appid, orderId);
		RepayRequestDto repayRequestDto = new RepayRequestDto();
		repayRequestDto.setApplicationId(orderActualLoanBo.getApplyId());
		repayRequestDto.setStatement(orderActualLoanBo.getFinanceOrderId());
		// 获取还款类别
		List<RepayTypeVo> list = centerClientFactory.getCenterClient(appid).queryRepayType(repayRequestDto);
		List<RepaymentTypeVo> repaymentTypeVoList = new LinkedList<>();
		for (RepayTypeVo repayTypeVo : list) {
			RepaymentTypeVo repaymentTypeVo = new RepaymentTypeVo();
			BeanUtils.copyProperties(repayTypeVo, repaymentTypeVo);
			repaymentTypeVoList.add(repaymentTypeVo);
		}

		// 获取还款计划
		OrderRepaymentPlanVo orderRepaymentPlanVo = getOrderRepaymentPlanVo(appid, orderId);

		// 获取还款对象
		OrderRepaymentVo orderRepaymentVo = new OrderRepaymentVo();
		orderRepaymentVo.setRepaymentTypeVoList(repaymentTypeVoList);
		orderRepaymentVo.setTotalRepaymentAmount(orderRepaymentPlanVo.getPrincipal().add(
				orderRepaymentPlanVo.getTotalInterestFee().add(orderRepaymentPlanVo.getTotalPenaltyInterestFee())));

		// 还款绑定银行卡对象
		OrderBankVo orderBankVo = orderBankService.getOrderBankVo(appid, orderId);
		String bankCardNo = orderBankVo.getBankCardNo();
		orderBankVo.setBankCardNo(bankCardNo.substring(bankCardNo.length() - 4));

		orderRepaymentVo.setOrderBankVo(orderBankVo);

		orderRepaymentVo.setOrderRuleVo(orderCreditService.getOrderRuleVoHasCredit(appid, orderId));
		return orderRepaymentVo;
	}

	@Override
	public boolean oneKeyRepayment(String appid, String orderId) {
		OrderActualLoanBo orderActualLoanBo = orderActualLoanMapper.getOrderActualLoanBo(appid, orderId);
		RepayRequestDto repayRequestDto = new RepayRequestDto();
		repayRequestDto.setApplicationId(orderActualLoanBo.getApplyId());
		repayRequestDto.setStatement(orderActualLoanBo.getFinanceOrderId());

		try {
			centerClientFactory.getCenterClient(appid).repaymentApply(repayRequestDto);
		} catch (RemoteServerException e) {
			logger.error("一键还款调用失败，appid:{};orderId:{};message:{}", appid, orderId, e.getMessage());

			throw new CalfException(e.getMessage(), CoreResultEnum.REMOTE_ERROR);
		}

		return true;
	}

	@Override
	public RepaymentShowVo weChatRepayment(String appid, String orderId) {
		OrderActualLoanBo orderActualLoanBo = orderActualLoanMapper.getOrderActualLoanBo(appid, orderId);
		WeChatRepayDto weChatRepayDto = new WeChatRepayDto();
		weChatRepayDto.setFinanceId(orderActualLoanBo.getFinanceOrderId());
		weChatRepayDto.setCustomerId(orderActualLoanBo.getApplyId());

		WeChatRepayVo weChatRepayVo;
		try {
			weChatRepayVo = centerClientFactory.getCenterClient(appid).weChatRepay(weChatRepayDto);
		} catch (RemoteServerException e) {
			logger.error("快捷还款调用失败，appid:{};orderId:{};message:{}", appid, orderId, e.getMessage());
			throw new CalfException(e.getMessage(), CoreResultEnum.REMOTE_ERROR);
		}

		RepaymentShowVo repaymentShowVo = new RepaymentShowVo();
		repaymentShowVo.setCode(weChatRepayVo.getCode());
		repaymentShowVo.setMsg(weChatRepayVo.getMsg());
		repaymentShowVo.setUrl(weChatRepayVo.getUrl());
		return repaymentShowVo;
	}

	@Override
	public RepaymentShowVo quickRepayment(String appid, String orderId) {
		OrderActualLoanBo orderActualLoanBo = orderActualLoanMapper.getOrderActualLoanBo(appid, orderId);
		AlipayRequestDto alipayRequestDto = new AlipayRequestDto();
		alipayRequestDto.setFinanceId(orderActualLoanBo.getFinanceOrderId());
		alipayRequestDto.setPayType("3");
		alipayRequestDto.setCustomerId(orderActualLoanBo.getApplyId());

		AlipayQuickRepayVo alipayQuickRepayVo;
		try {
			alipayQuickRepayVo = centerClientFactory.getCenterClient(appid).aliQuickRepay(alipayRequestDto);
		} catch (RemoteServerException e) {
			logger.error("快捷还款调用失败，appid:{};orderId:{};message:{}", appid, orderId, e.getMessage());

			throw new CalfException(e.getMessage(), CoreResultEnum.REMOTE_ERROR);
		}

		RepaymentShowVo repaymentShowVo = new RepaymentShowVo();
		repaymentShowVo.setCode(alipayQuickRepayVo.getCode());
		repaymentShowVo.setMsg(alipayQuickRepayVo.getMsg());
		repaymentShowVo.setUrl(alipayQuickRepayVo.getUrl());
		return repaymentShowVo;
	}

}
