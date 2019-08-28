package com.shangyong.thorder.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.center.client.CenterClientFactory;
import com.shangyong.center.dto.ApplicationRequestDto;
import com.shangyong.center.vo.ApplicationResponseVo;
import com.shangyong.common.entity.RestResult;
import com.shangyong.common.utils.LocalDateUtil;
import com.shangyong.rest.feign.BaseCloudHystrixService;
import com.shangyong.thcore.bo.OrderBankBo;
import com.shangyong.thcore.bo.OrderCreditBo;
import com.shangyong.thcore.bo.OrderRuleBo;
import com.shangyong.thcore.bo.OrderUserBo;
import com.shangyong.thcore.bussiness.event.SafeEvent;
import com.shangyong.thcore.bussiness.event.dto.EventBorrow;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.vo.ProductConfigVo;
import com.shangyong.thorder.contants.UuidPrefix;
import com.shangyong.thorder.dao.OrderBankBindMapper;
import com.shangyong.thorder.dao.OrderBindRuleMapper;
import com.shangyong.thorder.dao.OrderBorrowApplyMapper;
import com.shangyong.thorder.dao.OrderCreditMapper;
import com.shangyong.thorder.dao.OrderCreditRuleMapper;
import com.shangyong.thorder.dao.OrderUserMapper;
import com.shangyong.thorder.entity.OrderBorrowApply;
import com.shangyong.thorder.service.OrderBuessinessEventService;
import com.shangyong.thorder.service.process.CompositeCreditProcess;
import com.shangyong.thorder.service.process.CreditProcess;
import com.shangyong.thorder.service.process.CreditProcessFactory;
import com.shangyong.thorder.utils.IdUtil;
import com.shangyong.thorder.vo.OrderBindRuleVo;

@Service
public class OrderBuessinessEventServiceImpl implements OrderBuessinessEventService {

	private Logger logger = LoggerFactory.getLogger(OrderBuessinessEventServiceImpl.class);

	private String formatPattern = "yyyy-MM-dd HH:mm:ss";

	@Autowired
	private BaseCloudHystrixService baseCloudHystrixService;

	@Autowired
	private CreditProcessFactory creditProcessFactory;
	
	@Autowired
	private CompositeCreditProcess compositeCreditProcess;

	@Autowired
	private CenterClientFactory centerClientFactory;

	@Autowired
	private OrderUserMapper orderUserMapper;

	@Autowired
	private OrderBankBindMapper orderBankBindMapper;

	@Autowired
	private OrderCreditMapper orderCreditMapper;

	@Autowired
	private OrderBorrowApplyMapper orderBorrowApplyMapper;

	@Autowired
	private OrderCreditRuleMapper orderCreditRuleMapper;

	@Autowired
	private OrderBindRuleMapper orderBindRuleMapper;

	@Override
	public boolean processBorrowEvent(EventHeader eventHeader, EventBorrow eventBorrow) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();
		RestResult<ProductConfigVo> restResult = baseCloudHystrixService.getProductConfigVo(appid);
		if (restResult == null || !restResult.isSuccess()) {
			return false;
		}

		ProductConfigVo productConfigVo = restResult.getData().getBody();

		Date applyDate = new Date();
		int days = productConfigVo.getCycle() - 1;
		LocalDate localDate = LocalDateUtil.parse(applyDate).toLocalDate().plus(days, ChronoUnit.DAYS);
		Date repaymentPlanTime = LocalDateUtil.parse(localDate.atStartOfDay());
		String applyDateStr = LocalDateUtil.dateToString(applyDate, formatPattern);
		String repaymentPlanDateStr = LocalDateUtil.dateToString(repaymentPlanTime, formatPattern);
		// 借款申请id
		String applyId = IdUtil.getNumberUuid(UuidPrefix.BORROW_APPLY);

		OrderRuleBo orderRuleBo = orderCreditRuleMapper.getOrderRuleBo(appid, orderId, productConfigVo.getPrice());
		// 获取用户信息
		OrderUserBo orderUserBo = orderUserMapper.getOrderUserBo(appid, orderId);
		// 获取银行卡信息
		OrderBankBo orderBankBo = orderBankBindMapper.getOrderBankBo(appid, orderId);
		// 获取授信信息
		OrderCreditBo orderCreditBo = orderCreditMapper.getOrderCreditBo(appid, orderId);
		// 获取绑卡规则信息
		OrderBindRuleVo orderBindRuleVo = orderBindRuleMapper.getOrderBindRuleVo(appid, orderId);

		// 组装请求数据
		ApplicationRequestDto applicationRequestDto = new ApplicationRequestDto();
		// appid
		applicationRequestDto.setAppID(appid);
		// 借款单号
		applicationRequestDto.setApplicationId(applyId);
		// 申请时间
		applicationRequestDto.setApplyForDate(applyDateStr);
		// 授信号
		applicationRequestDto.setApplyNo(orderCreditBo.getCreditUseUuid());
		// appName
		applicationRequestDto.setApplyType(Integer.parseInt(productConfigVo.getAppName()));
		// 合同号
		applicationRequestDto.setAutherContractId(orderCreditBo.getCreditUseUuid());
		// 银行卡号
		applicationRequestDto.setBankCard(orderBankBo.getBankCardNo());
		// 银行编号
		applicationRequestDto.setBankCode(orderBankBo.getBankCode());
		// 银行名称
		applicationRequestDto.setBankName(orderBankBo.getBankName());
		// 用户id
		applicationRequestDto.setCustomerId(
				productConfigVo.getExt() == null ? "JDQ" : productConfigVo.getExt() + orderUserBo.getIdentityNo());
		// 身份证号
		applicationRequestDto.setIdCard(orderBankBo.getIdentityNo());
		// 前置申请时间
		applicationRequestDto.setInsuranceApplyForDate(applyDateStr);
		// 前置金额
		applicationRequestDto.setInsuranceMoney(productConfigVo.getPrePrice().toString());
		// 前置支付号
		applicationRequestDto.setInsurancePaymentId(null);
		// 保险预还款时间
		applicationRequestDto.setInsuranceRepayDate(repaymentPlanDateStr);
		// 用户名称
		applicationRequestDto.setName(orderBankBo.getUserName());
		// 支付规则
		applicationRequestDto.setPaymentRule(orderRuleBo.getRule());
		// 支付标记
		applicationRequestDto.setPaySign(orderBankBo.getSignNo());
		// 手机号码
		applicationRequestDto.setPhone(orderUserBo.getMobile());
		// 产品编号
		applicationRequestDto.setProductCode(orderCreditBo.getProductCode());
		// 借款金额
		applicationRequestDto
				.setProductQuota(orderCreditBo.getCreditAmount().add(productConfigVo.getPrePrice()).toString());
		// 实际放款金额
		applicationRequestDto.setRealMoney(orderCreditBo.getCreditAmount().toString());
		// 预计还款时间
		applicationRequestDto.setRepayDate(repaymentPlanDateStr);
		// 来源
		applicationRequestDto.setSource(1);
		// 保险渠道
		applicationRequestDto.setInsuranceChannel(orderRuleBo.getSceneRule());
		// 放款绑卡渠道
		if (orderBindRuleVo == null) {
			applicationRequestDto.setPaymentChannel(2);
		} else {
			applicationRequestDto.setPaymentChannel(Integer.valueOf(orderBindRuleVo.getRule()));
		}

		ApplicationResponseVo applicationResponseVo = centerClientFactory.getCenterClient(appid)
				.loanApply(applicationRequestDto);
		if ("-1".equals(applicationResponseVo.getState())) {
			logger.error("请求失败：{}", applicationResponseVo);
			return false;
		} else {
			OrderBorrowApply orderBorrowApply = new OrderBorrowApply();
			orderBorrowApply.setAppid(appid);
			orderBorrowApply.setApplyId(applyId);
			orderBorrowApply.setBorrowAmount(orderCreditBo.getCreditAmount());
			orderBorrowApply.setCreateTime(new Date());
			orderBorrowApply.setRepaymentPlanTime(repaymentPlanTime);
			orderBorrowApply.setCreditUseUuid(orderCreditBo.getCreditUseUuid());
			orderBorrowApply.setCycle(productConfigVo.getCycle());
			orderBorrowApply.setFinanceOrderId(applicationResponseVo.getFinanceApplicationId());
			orderBorrowApply.setIfValid(true);
			orderBorrowApply.setOrderId(orderId);
			orderBorrowApply.setPeriods(productConfigVo.getPeriods());
			orderBorrowApply.setPreBorrowAmount(productConfigVo.getPrePrice());
			orderBorrowApply.setProductCode(orderCreditBo.getProductCode());
			orderBorrowApplyMapper.insert(orderBorrowApply);

			return true;
		}

	}

	@Override
	public boolean processPreSafeEvent(EventHeader eventHeader, SafeEvent safeEvent) {
		return commonProcessSafeEvent(eventHeader, 0);
	}

	@Override
	public boolean processSafeEvent(EventHeader eventHeader, SafeEvent safeEvent) {
		return commonProcessSafeEvent(eventHeader, 1);
	}
	
	@Override
	public boolean processCompositeSafeEvent(EventHeader eventHeader, SafeEvent safeEvent) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();

		RestResult<ProductConfigVo> restResult = baseCloudHystrixService.getProductConfigVo(appid);
		if (restResult == null || !restResult.isSuccess()) {
			logger.error("获取产品配置信息失败 appid:{};orderId:{}", appid, orderId);
			return false;
		}
		// 产品配置信息
		ProductConfigVo productConfigVo = restResult.getData().getBody();

		OrderRuleBo orderRuleBo = orderCreditRuleMapper.getOrderRuleBo(appid, orderId, productConfigVo.getPrice());

		//获取模板信息
		int type = Integer.parseInt(orderRuleBo.getRule().split(",")[1]);
		// 获得场景值
		int sceneId = Integer.parseInt(orderRuleBo.getSceneRule().split(",")[1]);

		compositeCreditProcess.safety(appid, orderId, type, sceneId, productConfigVo);
		return true;
	}

	// *******************************************************************************************
	private boolean commonProcessSafeEvent(EventHeader eventHeader, int index) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();

		RestResult<ProductConfigVo> restResult = baseCloudHystrixService.getProductConfigVo(appid);
		if (restResult == null || !restResult.isSuccess()) {
			logger.error("获取产品配置信息失败 appid:{};orderId:{}", appid, orderId);
			return false;
		}
		// 产品配置信息
		ProductConfigVo productConfigVo = restResult.getData().getBody();

		OrderRuleBo orderRuleBo = orderCreditRuleMapper.getOrderRuleBo(appid, orderId, productConfigVo.getPrice());

		int ruleType = Integer.parseInt(orderRuleBo.getRule().split(",")[index]);
		// 获得场景值
		int sceneId = Integer.parseInt(orderRuleBo.getSceneRule().split(",")[index]);

		CreditProcess creditProcess = creditProcessFactory.getCreditProcess(ruleType);

		creditProcess.safety(appid, orderId, index, sceneId, productConfigVo);
		return true;
	}

}
