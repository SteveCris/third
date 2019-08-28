package com.shangyong.thzlqb.listener.service.impl;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.shangyong.loan.autoconfigure.redis.BatchRedisTemplate;
import com.shangyong.loan.ext.exception.CalfException;
import com.shangyong.loan.ext.util.IdUtil;
import com.shangyong.thcore.event.dto.EventActualLoan;
import com.shangyong.thcore.event.dto.EventBankBind;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.event.dto.EventPreSign;
import com.shangyong.thcore.event.dto.EventRepayment;
import com.shangyong.thcore.event.dto.EventSign;
import com.shangyong.thcore.event.dto.EventUserInfo;
import com.shangyong.thcore.vo.OrderBankVo;
import com.shangyong.thcore.vo.OrderLoanVo;
import com.shangyong.thzlqb.bo.OrderSimpleBo;
import com.shangyong.thzlqb.contants.CoreContants;
import com.shangyong.thzlqb.contants.RedisPrefix;
import com.shangyong.thzlqb.contants.UuidPrefix;
import com.shangyong.thzlqb.entity.ZlqbBandCardInfo;
import com.shangyong.thzlqb.entity.ZlqbUserInfo;
import com.shangyong.thzlqb.enums.ZlqbOrderReviewStatusEnum;
import com.shangyong.thzlqb.enums.ZlqbOrderStatusEnum;
import com.shangyong.thzlqb.enums.ZlqbResultEnum;
import com.shangyong.thzlqb.listener.service.CoreConsumerService;
import com.shangyong.thzlqb.service.CoreOrderService;
import com.shangyong.thzlqb.service.ZlqbOrderReviewService;
import com.shangyong.thzlqb.service.ZlqbOrderStatusService;
import com.shangyong.thzlqb.service.ZlqbUserService;

@Service
public class CoreConsumerServiceImpl implements CoreConsumerService {

	private Logger logger = LoggerFactory.getLogger(SelfConsumerServiceImpl.class);

	@Autowired
	private CoreOrderService coreOrderService;

	@Autowired
	private BatchRedisTemplate batchRedisTemplate;

	@Autowired
	private ZlqbOrderStatusService zlqbOrderStatusService;

	@Autowired
	private ZlqbOrderReviewService zlqbOrderReviewService;

	@Autowired
	private ZlqbUserService userService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean processAuditSuccess(EventHeader eventHeader, EventUserInfo eventUserInfo) {

		long startTime = System.currentTimeMillis();
		String orderId = eventHeader.getOrderId();
		logger.info("请求进入订单审核成功单元事件 当前时间 --》{} orderId--》{}", startTime, orderId);

		OrderSimpleBo orderSimpleBo = coreOrderService.getOrderSimpleBoByOrderId(orderId);

		if (Objects.isNull(orderSimpleBo)) {
			logger.error("查询订单审核成功记录时;出现空值 订单号 {}", orderId);
			throw new CalfException(ZlqbResultEnum.NULL_ERROR.getMessage());
		}

		String orderNo = orderSimpleBo.getOrderNo();

		if (!coreOrderService.updateOrderStatus(orderNo, ZlqbOrderStatusEnum.CHECK_PASS.getValue(),
				ZlqbOrderStatusEnum.PUSH_CHECK.getValue(), false, null)) {
			return coreOrderService.checkStatus(orderNo, ZlqbOrderStatusEnum.CHECK_PASS.getValue());
		}

		zlqbOrderReviewService.updateOrderReviewDto(ZlqbOrderReviewStatusEnum.REVIEW_SUCCESS.getValue(), orderNo);
		// 事务提交后，发送前置授信失效
		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
			@Override
			public void afterCommit() {
				zlqbOrderStatusService.pushOrder(orderNo);
			}
		});

		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean processAuditFailure(EventHeader eventHeader, EventFailureResult eventFailureResult) {
		long startTime = System.currentTimeMillis();
		String orderId = eventHeader.getOrderId();
		logger.info("请求进入订单审核失败单元事件 当前时间 --》{} orderId--》{}", startTime, orderId);

		OrderSimpleBo orderSimpleBo = coreOrderService.getOrderSimpleBoByOrderId(orderId);

		if (Objects.isNull(orderSimpleBo)) {
			logger.error("查询订单失败记录时;出现空值 订单号 {}", orderId);
			throw new CalfException(ZlqbResultEnum.NULL_ERROR.getMessage());
		}

		String orderNo = orderSimpleBo.getOrderNo();

		if (!coreOrderService.updateOrderStatus(orderNo, ZlqbOrderStatusEnum.CHECK_FAIL.getValue(),
				ZlqbOrderStatusEnum.PUSH_CHECK.getValue(), true, null)) {
			return coreOrderService.checkStatus(orderNo, ZlqbOrderStatusEnum.CHECK_FAIL.getValue());
		}
		zlqbOrderReviewService.updateOrderReviewDto(ZlqbOrderReviewStatusEnum.REVIEW_FAILURE.getValue(), orderNo);
		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
			@Override
			public void afterCommit() {
				zlqbOrderStatusService.pushOrder(orderNo);
			}
		});

		return true;
	}

	@Override
	public boolean processBankBindSuccess(EventHeader eventHeader, EventBankBind eventBankBind) {
		String orderId = eventHeader.getOrderId();
		logger.info("请求绑卡成功单元事件 orderId--》{}", orderId);
		OrderSimpleBo orderSimpleBo = coreOrderService.getOrderSimpleBoByOrderId(orderId);
		if (Objects.isNull(orderSimpleBo)) {
			logger.error("当前订单号{};无相关订单记录信息", orderId);
			return false;
		}
		// 存库
		ZlqbUserInfo userInfo = userService.selectOne(orderSimpleBo.getOrderNo());
		if (Objects.isNull(userInfo)) {
			logger.error("当前的订单号{};未查询到用户记录信息", orderId);
			return false;
		}
		ZlqbBandCardInfo info = sealZlqbBandCardInfo(userInfo, orderSimpleBo);
		userService.saveUserBandCardDto(info);
		return zlqbOrderStatusService.pushOrder(orderSimpleBo.getOrderNo());
	}

	private ZlqbBandCardInfo sealZlqbBandCardInfo(ZlqbUserInfo userInfo, OrderSimpleBo orderSimpleBo) {
		String idCard = userInfo.getIdCard();
		final String orderNo = userInfo.getOrderNo();
		OrderLoanVo loanVo = new OrderLoanVo();
		loanVo.setOrderId(orderSimpleBo.getOrderId());
		loanVo.setOtherOrderId(orderSimpleBo.getOrderNo());
		OrderBankVo bankVo = zlqbOrderStatusService.bankInfoDtoGet(loanVo);
		logger.info("当前订单号 orderNo {} 查询的结果 result {}", orderNo, bankVo.toString());
		ZlqbBandCardInfo info = new ZlqbBandCardInfo();
		info.setBindId(IdUtil.getNumberUuid(UuidPrefix.BAND_CARD));
		info.setBankCardNo(bankVo.getBankCardNo());
		info.setIdCard(idCard);
		info.setOrderNo(orderNo);
		info.setCreateTime(System.currentTimeMillis());
		logger.info("插入绑卡记录表中的值 orderNo {} idCard {} bankCardNo {}", orderNo, idCard, bankVo.getBankCardNo());
		return info;
	}

	@Override
	public boolean processBankBindFailure(EventHeader eventHeader, EventFailureResult eventFailureResult) {
		return true;
	}

	@Override
	public boolean processPreSignSuccess(EventHeader eventHeader, EventPreSign eventPreSign) {
		return true;
	}

	@Override
	public boolean processPreSignFailure(EventHeader eventHeader, EventFailureResult eventFailureResult) {
		return true;
	}

	@Override
	public boolean processSignSuccess(EventHeader eventHeader, EventSign eventSign) {
		String orderId = eventHeader.getOrderId();
		logger.info("请求提现单元事件 orderId--》{}", orderId);
		OrderSimpleBo orderSimpleBo = coreOrderService.getOrderSimpleBoByOrderId(orderId);
		return zlqbOrderStatusService.pushOrder(orderSimpleBo.getOrderNo());
	}

	@Override
	public boolean processSignFailure(EventHeader eventHeader, EventFailureResult eventFailureResult) {
		return true;
	}

	@Override
	public boolean processActualLoanSuccess(EventHeader eventHeader, EventActualLoan eventActualLoan) {
		String orderId = eventHeader.getOrderId();
		logger.info("请求放款成功单元事件 orderId--》{}", orderId);
		OrderSimpleBo orderSimpleBo = coreOrderService.getOrderSimpleBoByOrderId(orderId);
		return zlqbOrderStatusService.pushOrder(orderSimpleBo.getOrderNo());
	}

	@Override
	public boolean processActualLoanFailure(EventHeader eventHeader, EventFailureResult eventFailureResult) {
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean processRepaymentSuccess(EventHeader eventHeader, EventRepayment eventRepayment) {
		String orderId = eventHeader.getOrderId();
		logger.info("请求还款成功单元事件 orderId--》{}", orderId);
		OrderSimpleBo orderSimpleBo = coreOrderService.getOrderSimpleBoByOrderId(orderId);
		if (!coreOrderService.updateOrderStatus(orderSimpleBo.getOrderNo(), ZlqbOrderStatusEnum.REPAY_SUC.getValue(),
				orderSimpleBo.getStatus(), true, null)) {
			return coreOrderService.checkStatus(orderSimpleBo.getOrderNo(), ZlqbOrderStatusEnum.REPAY_SUC.getValue());
		}
		removerHitDBRedisKey(orderSimpleBo);

		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
			@Override
			public void afterCommit() {
				zlqbOrderStatusService.pushOrder(orderSimpleBo.getOrderNo());
			}
		});
		return true;
	}

	@Override
	public boolean processRepaymentFailure(EventHeader eventHeader, EventFailureResult eventFailureResult) {
		return true;
	}

	@Override
	public boolean processPreCreditExpireEvent(EventHeader eventHeader) {
		return true;
	}

	@Override
	public boolean processOverdue(String appid, String orderId) {
		logger.info("请求逾期单元事件 orderId--》{}", orderId);
		OrderSimpleBo orderSimpleBo = coreOrderService.getOrderSimpleBoByOrderId(orderId);
		return zlqbOrderStatusService.pushOrder(orderSimpleBo.getOrderNo());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean processCancel(String appid, String orderId, String remark) {
		OrderSimpleBo orderSimpleBo = coreOrderService.getOrderSimpleBoByOrderId(orderId);
		final String orderNo = orderSimpleBo.getOrderNo();
		if (orderSimpleBo.getStatus() == ZlqbOrderStatusEnum.ORDER_CANCEL.getValue()) {
			return true;
		}

		if (!coreOrderService.updateOrderStatus(orderNo, ZlqbOrderStatusEnum.ORDER_CANCEL.getValue(),
				orderSimpleBo.getStatus(), true, "订单取消：" + orderSimpleBo.getStatus() + "->1000")) {
			return coreOrderService.checkStatus(orderNo, ZlqbOrderStatusEnum.ORDER_CANCEL.getValue());
		}
		removerHitDBRedisKey(orderSimpleBo);
		logger.info("请求订单取消单元事件 orderId--》{}", orderId);
		return zlqbOrderStatusService.pushOrder(orderSimpleBo.getOrderNo());
	}

	private void removerHitDBRedisKey(OrderSimpleBo orderSimpleBo) {
		ZlqbUserInfo userInfo = userService.selectOne(orderSimpleBo.getOrderNo());
		if (Objects.isNull(userInfo)) {
			logger.error("该订单未找到；请手动删除reidisKey");
			return;
		}
		try {
			boolean delete = batchRedisTemplate
					.delete(RedisPrefix.LOCK_REPEAT + CoreContants.REDIS_BUSINESS_KEY + userInfo.getIdCard());
			logger.info("执行redis删除key是否成功{}", delete);
		} catch (Exception ex) {
			logger.error("执行redis删除key时;出现异常");
		}

	}
}
