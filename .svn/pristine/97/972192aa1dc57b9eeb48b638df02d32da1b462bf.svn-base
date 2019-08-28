package com.shangyong.thorder.service.impl;

import java.text.MessageFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.shangyong.center.client.CenterClientFactory;
import com.shangyong.thcore.bo.OrderCreditBo;
import com.shangyong.thcore.bo.OrderLoanBo;
import com.shangyong.thcore.bussiness.event.BorrowEvent;
import com.shangyong.thcore.bussiness.event.SafeEvent;
import com.shangyong.thcore.event.PreCreditExpireEvent;
import com.shangyong.thcore.event.dto.EventActualLoan;
import com.shangyong.thcore.event.dto.EventBankBind;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.event.dto.EventPreSign;
import com.shangyong.thcore.event.dto.EventRepayment;
import com.shangyong.thcore.event.dto.EventSign;
import com.shangyong.thcore.event.dto.EventUserInfo;
import com.shangyong.thcore.vo.OrderRuleVo;
import com.shangyong.thorder.dao.OrderActualLoanMapper;
import com.shangyong.thorder.dao.OrderActualLoanRMapper;
import com.shangyong.thorder.dao.OrderAuditRMapper;
import com.shangyong.thorder.dao.OrderBankBindMapper;
import com.shangyong.thorder.dao.OrderBankBindRMapper;
import com.shangyong.thorder.dao.OrderCreditMapper;
import com.shangyong.thorder.dao.OrderCreditRMapper;
import com.shangyong.thorder.dao.OrderCreditRuleMapper;
import com.shangyong.thorder.dao.OrderLoanMapper;
import com.shangyong.thorder.dao.OrderPreCreditMapper;
import com.shangyong.thorder.dao.OrderPreCreditRMapper;
import com.shangyong.thorder.dao.OrderRepaymentMapper;
import com.shangyong.thorder.dao.OrderRepaymentRMapper;
import com.shangyong.thorder.dao.OrderUserMapper;
import com.shangyong.thorder.entity.OrderActualLoan;
import com.shangyong.thorder.entity.OrderActualLoanR;
import com.shangyong.thorder.entity.OrderAuditR;
import com.shangyong.thorder.entity.OrderBankBind;
import com.shangyong.thorder.entity.OrderBankBindR;
import com.shangyong.thorder.entity.OrderCredit;
import com.shangyong.thorder.entity.OrderCreditR;
import com.shangyong.thorder.entity.OrderPreCredit;
import com.shangyong.thorder.entity.OrderPreCreditR;
import com.shangyong.thorder.entity.OrderRepayment;
import com.shangyong.thorder.entity.OrderRepaymentR;
import com.shangyong.thorder.entity.OrderUser;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.exception.CalfException;
import com.shangyong.thorder.sender.mq.Sender;
import com.shangyong.thorder.service.OrderCreditService;
import com.shangyong.thorder.service.OrderEventService;
import com.shangyong.thorder.utils.EventUtil;

@Service
public class OrderEventServiceImpl implements OrderEventService {

	@Autowired
	private Sender sender;

	

	@Autowired
	private OrderCreditService orderCreditService;
	
	// 事件mapper
	@Autowired
	private OrderAuditRMapper orderAuditRMapper;

	@Autowired
	private OrderBankBindRMapper orderBankBindRMapper;

	@Autowired
	private OrderPreCreditRMapper orderPreCreditRMapper;

	@Autowired
	private OrderCreditRMapper orderCreditRMapper;

	@Autowired
	private OrderActualLoanRMapper orderActualLoanRMapper;

	@Autowired
	private OrderRepaymentRMapper orderRepaymentRMapper;

	// 业务mapper
	@Autowired
	private OrderLoanMapper orderLoanMapper;

	@Autowired
	private OrderBankBindMapper orderBankBindMapper;

	@Autowired
	private OrderUserMapper orderUserMapper;

	@Autowired
	private OrderPreCreditMapper orderPreCreditMapper;

	@Autowired
	private OrderCreditRuleMapper orderCreditRuleMapper;

	@Autowired
	private OrderCreditMapper orderCreditMapper;

	@Autowired
	private OrderActualLoanMapper orderActualLoanMapper;

	@Autowired
	private OrderRepaymentMapper orderRepaymentMapper;

	@Autowired
	private CenterClientFactory centerClientFactory;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processAuditSuccess(EventHeader eventHeader, EventUserInfo eventUserInfo) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();
		if (orderLoanMapper.updateOrderStatus(appid, orderId, 40, 10, false, null, null) > 0) {

			OrderAuditR orderAuditR = new OrderAuditR();
			orderAuditR.setAuditId(eventHeader.getMessageId());
			orderAuditR.setAppid(appid);
			orderAuditR.setOrderId(orderId);
			orderAuditR.setCreateTime(new Date(eventHeader.getHappenTime()));
			orderAuditR.setCode("200");
			orderAuditR.setIfSuccess(true);
			orderAuditRMapper.insert(orderAuditR);

			// 用户数据落库
			OrderUser orderUser = new OrderUser();
			BeanUtils.copyProperties(eventUserInfo, orderUser);
			orderUser.setAuditId(orderAuditR.getAuditId());
			orderUser.setAppid(appid);
			orderUser.setOrderId(orderId);
			orderUser.setCreateTime(new Date());
			orderUser.setIfValid(true);
			orderUserMapper.insert(orderUser);
			return true;
		} else {
			return checkStatusAndReturn(appid, orderId, 40);
		}

	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processAuditFailure(EventHeader eventHeader, EventFailureResult eventFailureResult) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();
		if (orderLoanMapper.updateOrderStatus(appid, orderId, 30, 10, true, null, null) > 0) {
			OrderAuditR orderAuditR = new OrderAuditR();
			orderAuditR.setAuditId(eventHeader.getMessageId());
			orderAuditR.setAppid(appid);
			orderAuditR.setOrderId(orderId);
			orderAuditR.setCreateTime(new Date(eventHeader.getHappenTime()));
			orderAuditR.setIfSuccess(false);
			BeanUtils.copyProperties(eventFailureResult, orderAuditR);
			orderAuditRMapper.insert(orderAuditR);
			return true;
		} else {
			return checkStatusAndReturn(appid, orderId, 30);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processBankBindSuccess(EventHeader eventHeader, EventBankBind eventBankBind) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();
		if (orderLoanMapper.updateOrderStatus(appid, orderId, 60, 40, false, null, null) > 0) {

			OrderBankBindR orderBankBindR = new OrderBankBindR();
			orderBankBindR.setBindId(eventHeader.getMessageId());
			orderBankBindR.setAppid(appid);
			orderBankBindR.setOrderId(orderId);
			orderBankBindR.setCreateTime(new Date(eventHeader.getHappenTime()));
			orderBankBindR.setCode("200");
			orderBankBindR.setIfSuccess(true);
			orderBankBindRMapper.insert(orderBankBindR);

			OrderBankBind orderBankBind = new OrderBankBind();
			BeanUtils.copyProperties(eventBankBind, orderBankBind);
			orderBankBind.setAppid(appid);
			orderBankBind.setBindId(orderBankBindR.getBindId());
			orderBankBind.setCreateTime(new Date());
			orderBankBind.setIfValid(true);
			orderBankBind.setOrderId(orderId);
			orderBankBindMapper.insert(orderBankBind);
			return true;
		} else {
			return checkStatusAndReturn(appid, orderId, 60);
		}
	}

	@Override
	public boolean processBankBindFailure(EventHeader eventHeader, EventFailureResult eventFailureResult) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();
		OrderBankBindR orderBankBindR = new OrderBankBindR();
		orderBankBindR.setBindId(eventHeader.getMessageId());
		orderBankBindR.setAppid(appid);
		orderBankBindR.setOrderId(orderId);
		orderBankBindR.setCreateTime(new Date(eventHeader.getHappenTime()));
		orderBankBindR.setIfSuccess(false);
		BeanUtils.copyProperties(eventFailureResult, orderBankBindR);

		orderBankBindRMapper.insert(orderBankBindR);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processPreSignSuccess(EventHeader eventHeader, EventPreSign eventPreSign) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();
		if (orderLoanMapper.updateOrderStatus(appid, orderId, 70, 60, false, null, null) > 0) {

			OrderPreCreditR orderPreCreditR = new OrderPreCreditR();
			orderPreCreditR.setPreCreditId(eventHeader.getMessageId());
			orderPreCreditR.setAppid(appid);
			orderPreCreditR.setOrderId(orderId);
			orderPreCreditR.setCreateTime(new Date(eventHeader.getHappenTime()));
			orderPreCreditR.setCode("200");
			orderPreCreditR.setIfSuccess(true);
			orderPreCreditRMapper.insert(orderPreCreditR);

			OrderPreCredit orderPreCredit = new OrderPreCredit();
			BeanUtils.copyProperties(eventPreSign, orderPreCredit);
			orderPreCredit.setAppid(appid);
			orderPreCredit.setPreCreditId(orderPreCreditR.getPreCreditId());
			orderPreCredit.setCreateTime(new Date());
			orderPreCredit.setIfValid(true);
			orderPreCredit.setOrderId(orderId);
			orderPreCreditMapper.insert(orderPreCredit);

			// 事务提交后，发送前置授信失效
			TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
				@Override
				public void afterCommit() {
					PreCreditExpireEvent preCreditExpireEvent = new PreCreditExpireEvent();
					preCreditExpireEvent.setEventHeader(
							EventUtil.buildEventHeader(eventHeader.getMessageId(), appid, orderId, "", true));
					sender.sendMq("/third", "ex.event.preSign.expire",
							MessageFormat.format("preSign.expire.{0}.rKey", appid), preCreditExpireEvent, true);

				}
			});

			return true;
		} else {
			return checkStatusAndReturn(appid, orderId, 70);
		}

	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processPreCreditExpireEvent(EventHeader eventHeader) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();
		// 回滚状态
		if (orderLoanMapper.updateOrderStatus(appid, orderId, 60, 70, false, null, null) > 0) {
			orderCreditRuleMapper.removeCreditRule(appid, orderId);
			orderPreCreditMapper.invalidPreCredit(appid, orderId, eventHeader.getMessageId() + "，正常前置授信失效");
		}
		return true;
	}

	@Override
	public boolean processPreSignFailure(EventHeader eventHeader, EventFailureResult eventFailureResult) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();

		OrderPreCreditR orderPreCreditR = new OrderPreCreditR();
		orderPreCreditR.setPreCreditId(eventHeader.getMessageId());
		orderPreCreditR.setAppid(appid);
		orderPreCreditR.setOrderId(orderId);
		orderPreCreditR.setCreateTime(new Date(eventHeader.getHappenTime()));
		orderPreCreditR.setIfSuccess(false);
		BeanUtils.copyProperties(eventFailureResult, orderPreCreditR);
		orderPreCreditRMapper.insert(orderPreCreditR);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processSignSuccess(EventHeader eventHeader, EventSign eventSign) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();

		// 判断是不是复合模式
		int oldStatus = eventSign.isIfComposite() ? 60 : 70;

		// 更新状态成功
		if (orderLoanMapper.updateOrderStatus(appid, orderId, 80, oldStatus, false, null, null) > 0) {

			// 后置授信记录
			OrderCreditR orderCreditR = new OrderCreditR();
			orderCreditR.setCreditId(eventHeader.getMessageId());
			orderCreditR.setAppid(appid);
			orderCreditR.setOrderId(orderId);
			orderCreditR.setCreateTime(new Date(eventHeader.getHappenTime()));
			orderCreditR.setCode("200");
			orderCreditR.setIfSuccess(true);
			orderCreditRMapper.insert(orderCreditR);

			// 授信信息入库
			OrderCredit orderCredit = new OrderCredit();
			BeanUtils.copyProperties(eventSign, orderCredit);
			orderCredit.setAppid(appid);
			orderCredit.setCreditId(orderCreditR.getCreditId());
			orderCredit.setCreateTime(new Date());
			orderCredit.setIfValid(true);
			orderCredit.setOrderId(orderId);
			orderCreditMapper.insert(orderCredit);

			// 事务提交后，发送借款处理事件
			TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
				@Override
				public void afterCommit() {
					BorrowEvent borrowEvent = new BorrowEvent();
					borrowEvent.setEventHeader(
							EventUtil.buildEventHeader(eventHeader.getMessageId(), appid, orderId, "", true));
					sender.sendMq("/third", "ex.bussiness.borrow", "borrow.rKey", borrowEvent, true);

				}
			});

			return true;
		} else {
			return checkStatusAndReturn(appid, orderId, 80);

		}

	}

	@Override
	public boolean processSignFailure(EventHeader eventHeader, EventFailureResult eventFailureResult) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();
		OrderCreditR orderCreditR = new OrderCreditR();
		orderCreditR.setCreditId(eventHeader.getMessageId());
		orderCreditR.setAppid(appid);
		orderCreditR.setOrderId(orderId);
		orderCreditR.setCreateTime(new Date(eventHeader.getHappenTime()));
		orderCreditR.setIfSuccess(false);
		BeanUtils.copyProperties(eventFailureResult, orderCreditR);

		orderCreditRMapper.insert(orderCreditR);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processActualLoanSuccess(EventHeader eventHeader, EventActualLoan eventActualLoan) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();
		if (orderLoanMapper.updateOrderStatus(appid, orderId, 110, 80, false, 10, null) > 0) {
			OrderActualLoanR orderActualLoanR = new OrderActualLoanR();
			orderActualLoanR.setAppid(appid);
			orderActualLoanR.setOrderId(orderId);
			orderActualLoanR.setCreateTime(new Date(eventHeader.getHappenTime()));
			orderActualLoanR.setCode("200");
			orderActualLoanR.setIfSuccess(true);
			orderActualLoanR.setLoanId(eventHeader.getMessageId());

			orderActualLoanRMapper.insert(orderActualLoanR);

			OrderActualLoan orderActualLoan = new OrderActualLoan();
			BeanUtils.copyProperties(eventActualLoan, orderActualLoan);
			orderActualLoan.setAppid(appid);
			orderActualLoan.setOrderId(orderId);
			orderActualLoan.setCreateTime(new Date());
			orderActualLoan.setLoanId(orderActualLoanR.getLoanId());

			orderActualLoanMapper.insert(orderActualLoan);

			OrderRuleVo orderRuleVo= orderCreditService.getOrderRuleVoHasCredit(appid, orderId);
			
			int ssType=orderRuleVo.getSsType();
			// 事务提交后，发送保全处理事件
			TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
				@Override
				public void afterCommit() {
					//无场景模式
					if(ssType==8) {
						SafeEvent safeEvent = new SafeEvent();
						safeEvent.setEventHeader(
								EventUtil.buildEventHeader(eventHeader.getMessageId(), appid, orderId, "", true));
						sender.sendMq("/third", "ex.bussiness.compositeSafe", "safe.rKey", safeEvent, true);
						
					}else {
						SafeEvent safeEvent = new SafeEvent();
						safeEvent.setEventHeader(
								EventUtil.buildEventHeader(eventHeader.getMessageId(), appid, orderId, "", true));
						sender.sendMq("/third", "ex.bussiness.safe", "safe.rKey", safeEvent, true);
					}
					
					

				}
			});

			return true;
		} else {
			return checkStatusAndReturn(appid, orderId, 110);
		}
	}

	@Override
	public boolean processActualLoanFailure(EventHeader eventHeader, EventFailureResult eventFailureResult) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();
		// if (orderLoanMapper.updateOrderStatus(appid, orderId, 100, 80, true, null,
		// null) > 0) {
		OrderActualLoanR orderActualLoanR = new OrderActualLoanR();
		BeanUtils.copyProperties(eventFailureResult, orderActualLoanR);
		orderActualLoanR.setAppid(appid);
		orderActualLoanR.setOrderId(orderId);
		orderActualLoanR.setCreateTime(new Date(eventHeader.getHappenTime()));
		orderActualLoanR.setIfSuccess(false);
		orderActualLoanR.setLoanId(eventHeader.getMessageId());
		orderActualLoanRMapper.insert(orderActualLoanR);

		if (orderActualLoanR.getReason() != null && (orderActualLoanR.getReason().contains("触发黑名单风险")
				|| orderActualLoanR.getReason().contains("风控检测已拒绝"))) {
			orderLoanMapper.updateOrderStatus(appid, orderId, 100, 80, true, null, "触发黑名单风险、风控检测已拒绝");
		}

		return true;
		// } else {
		// return checkStatusAndReturn(appid, orderId, 100);
		// }

	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processRepaymentSuccess(EventHeader eventHeader, EventRepayment eventRepayment) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();
		boolean isSuccess;

		if (eventRepayment.isIfOverdue()) {
			// 逾期还款
			isSuccess = orderLoanMapper.updateOrderStatus(appid, orderId, 130, 110, true, 40, null) > 0;
		} else {
			// 正常还款
			isSuccess = orderLoanMapper.updateOrderStatus(appid, orderId, 130, 110, true, 20, null) > 0;
		}

		if (isSuccess) {
			OrderRepaymentR orderRepaymentR = new OrderRepaymentR();
			orderRepaymentR.setAppid(appid);
			orderRepaymentR.setCreateTime(new Date(eventHeader.getHappenTime()));
			orderRepaymentR.setIfSuccess(true);
			orderRepaymentR.setOrderId(orderId);
			orderRepaymentR.setRepaymentId(eventHeader.getMessageId());
			orderRepaymentR.setCode("200");
			orderRepaymentRMapper.insert(orderRepaymentR);

			OrderRepayment orderRepayment = new OrderRepayment();
			BeanUtils.copyProperties(eventRepayment, orderRepayment);
			orderRepayment.setAppid(appid);
			orderRepayment.setOrderId(orderId);
			orderRepayment.setCreateTime(new Date());
			orderRepayment.setRepaymentId(orderRepaymentR.getRepaymentId());
			orderRepaymentMapper.insert(orderRepayment);
			return true;
		} else {
			return checkStatusAndReturn(appid, orderId, 130);
		}

	}

	@Override
	public boolean processRepaymentFailure(EventHeader eventHeader, EventFailureResult eventFailureResult) {
		String appid = eventHeader.getAppid();
		String orderId = eventHeader.getOrderId();

		OrderRepaymentR orderRepaymentR = new OrderRepaymentR();
		orderRepaymentR.setAppid(appid);
		orderRepaymentR.setCreateTime(new Date(eventHeader.getHappenTime()));
		orderRepaymentR.setIfSuccess(false);
		orderRepaymentR.setOrderId(orderId);
		orderRepaymentR.setRepaymentId(eventHeader.getMessageId());
		BeanUtils.copyProperties(eventFailureResult, orderRepaymentR);
		orderRepaymentRMapper.insert(orderRepaymentR);
		return true;
	}

	/**
	 * 处理逾期事件
	 */
	@Override
	public boolean processOverdue(String appid, String orderId) {
		orderLoanMapper.overdue(appid, orderId);
		return true;
	}

	/**
	 * 处理订单取消事件
	 */
	@Override
	public boolean processCancel(String appid, String orderId, String remark) {

		OrderLoanBo orderLoanBo = orderLoanMapper.getOrderLoanBo(appid, orderId);
		int status = orderLoanBo.getStatus();
		if (orderLoanBo.isIfFinish()) {
			throw new CalfException(CoreResultEnum.CANCEL_ERROR);
		}

		int repaymentStatus = orderLoanBo.getRepaymentStatus();
		if (repaymentStatus != 0) {
			throw new CalfException(CoreResultEnum.REPAYMENT_ERROR);
		}

		String newRemark = MessageFormat.format("备注：{0}，订单取消，状态流转：{1}->1000", remark, status);
		if (orderLoanMapper.updateOrderStatus(appid, orderId, 1000, status, true, repaymentStatus, newRemark) > 0) {
			// 说明都签约过了，需要主动去失效，并且账务系统也需要进行拦截操作
			if (status == 80) {
				// 获取授信信息申请信息
				OrderCreditBo orderCreditBo = orderCreditMapper.getOrderCreditBo(appid, orderId);
				// 失效相关授信
				centerClientFactory.getCenterClient(appid).useExpire(orderCreditBo.getCreditUseUuid());
			}
			return true;
		} else {
			return checkStatusAndReturn(appid, orderId, 1000);
		}
	}

	// *****************************************************************************************
	private boolean checkStatusAndReturn(String appid, String orderId, int checkStatus) {
		OrderLoanBo orderLoanBo = orderLoanMapper.getOrderLoanBo(appid, orderId);
		return orderLoanBo.getStatus() == checkStatus;
	}

}
