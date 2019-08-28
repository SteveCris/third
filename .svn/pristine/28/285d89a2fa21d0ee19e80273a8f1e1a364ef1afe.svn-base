package com.shangyong.thorder.service.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.center.client.CenterClientFactory;
import com.shangyong.center.client.exception.RemoteServerException;
import com.shangyong.center.dto.CardBindConfirmDto;
import com.shangyong.center.dto.CardBindDto;
import com.shangyong.center.dto.CardBindQryDto;
import com.shangyong.center.dto.YijifuBindCardAuthDto;
import com.shangyong.center.dto.YijifuBindCardConfirmDto;
import com.shangyong.center.vo.CardBindInfoVo;
import com.shangyong.center.vo.CardBindVo;
import com.shangyong.center.vo.RuleConfigVo;
import com.shangyong.center.vo.YijifuBindCardVo;
import com.shangyong.common.entity.RestResult;
import com.shangyong.rest.feign.BaseCloudHystrixService;
import com.shangyong.thcore.bo.OrderUserBo;
import com.shangyong.thcore.dto.BankVerifyCodeDto;
import com.shangyong.thcore.dto.OrderBankDto;
import com.shangyong.thcore.event.BankBindEvent;
import com.shangyong.thcore.event.dto.EventBankBind;
import com.shangyong.thcore.vo.BankVerifyCodeVo;
import com.shangyong.thcore.vo.OrderBankVo;
import com.shangyong.thorder.contants.UuidPrefix;
import com.shangyong.thorder.dao.OrderBankBindMapper;
import com.shangyong.thorder.dao.OrderBindRuleMapper;
import com.shangyong.thorder.dao.OrderUserMapper;
import com.shangyong.thorder.entity.OrderBindRule;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.exception.CalfException;
import com.shangyong.thorder.sender.mq.Sender;
import com.shangyong.thorder.service.OrderBankService;
import com.shangyong.thorder.utils.EventUtil;
import com.shangyong.thorder.utils.IdUtil;
import com.shangyong.thorder.vo.OrderBindRuleVo;

@Service
public class OrderBankServiceImpl implements OrderBankService {

	private Logger logger = LoggerFactory.getLogger(OrderBankServiceImpl.class);

	@Autowired
	private Sender sender;

	@Autowired
	private BaseCloudHystrixService baseCloudHystrixService;

	@Autowired
	private CenterClientFactory centerClientFactory;

	@Autowired
	private OrderBankBindMapper orderBankBindMapper;

	@Autowired
	private OrderUserMapper orderUserMapper;

	@Autowired
	private OrderBindRuleMapper orderBindRuleMapper;

	@Override
	public OrderBindRuleVo getOrderBindRuleVo(String appid, String orderId) {
		OrderBindRuleVo orderBindRuleVo = orderBindRuleMapper.getOrderBindRuleVo(appid, orderId);
		if (orderBindRuleVo == null) {
			try {
				RuleConfigVo ruleConfigVo = centerClientFactory.getCenterClient(appid).getRuleConfigVo("BIND", null);
				OrderBindRule orderBindRule = new OrderBindRule();
				orderBindRule.setBindRId(IdUtil.getNumberUuid(UuidPrefix.BIND_RULE));
				orderBindRule.setAppid(appid);
				orderBindRule.setOrderId(orderId);
				orderBindRule.setCreateTime(new Date());
				orderBindRule.setRule(ruleConfigVo.getRule());

				if (orderBindRuleMapper.insertOrIgnore(orderBindRule) > 0) {
					orderBindRuleVo = new OrderBindRuleVo();
					orderBindRuleVo.setRule(orderBindRule.getRule());
					return orderBindRuleVo;
				} else {
					throw new CalfException(CoreResultEnum.CLICK_REPEAT);
				}

			} catch (RemoteServerException e) {
				logger.error("获取绑卡规则失败，appid:{};orderId:{};message:{}", appid, orderId, e.getMessage());
				throw new CalfException(e.getMessage(), CoreResultEnum.REMOTE_ERROR);
			}

		} else {
			return orderBindRuleVo;
		}
	}

	@Override
	public boolean canBind(String appid, String orderId, String rule, String appName, String bankCardNo) {

		// 事件
		BankBindEvent bankBindEvent = new BankBindEvent();
		// 事件id
		String messageId = EventUtil.getMessageId();

		OrderUserBo orderUserBo = orderUserMapper.getOrderUserBo(appid, orderId);

		CardBindQryDto cardBindQryDto = new CardBindQryDto();
		cardBindQryDto.setAppid(appid);
		cardBindQryDto.setAppName(appName);
		cardBindQryDto.setChannelType(rule);
		cardBindQryDto.setIdCard(orderUserBo.getIdentityNo());
		cardBindQryDto.setBankNo(bankCardNo);
		try {

			CardBindInfoVo cardBindInfoVo = null;
			List<CardBindInfoVo> list = centerClientFactory.getCenterClient(appid).cardBindInfoQry(cardBindQryDto);

			for (CardBindInfoVo cbiv : list) {
				if (bankCardNo.equals(cbiv.getBankNo())) {
					cardBindInfoVo = cbiv;
				}
			}

			if (cardBindInfoVo == null) {
				return false;
			}

			// 事件头
			bankBindEvent.setEventHeader(EventUtil.buildEventHeader(messageId, appid, orderId, "", true));
			// 事件主体
			EventBankBind eventBankBind = new EventBankBind();
			eventBankBind.setBankCardNo(cardBindInfoVo.getBankNo());
			eventBankBind.setBankCode(cardBindInfoVo.getBankCode());
			eventBankBind.setBankName(cardBindInfoVo.getBankName());
			eventBankBind.setCardType(cardBindInfoVo.getCardType());
			eventBankBind.setIdentityNo(cardBindInfoVo.getIdCard());
			eventBankBind.setReservedMobile(cardBindInfoVo.getReservedPhone());
			eventBankBind.setUserName(cardBindInfoVo.getUserName());
			eventBankBind.setSignNo(cardBindInfoVo.getSerialNo());
			bankBindEvent.setEventBankBind(eventBankBind);
			return sender.sendMq("/third", "ex.event.bankBind", MessageFormat.format("bankBind.{0}.rKey", appid),
					bankBindEvent, true);
		} catch (RemoteServerException e) {
			logger.error("绑卡失败，appid:{};orderId:{};cardBindQryDto:{};message:{}", appid, orderId, cardBindQryDto,
					e.getMessage());
			// 事件头
			bankBindEvent.setEventHeader(EventUtil.buildEventHeader(messageId, appid, orderId, "", false));
			// 事件失败原因
			bankBindEvent.setEventFailureResult(EventUtil.buildEventFailureResult(e));
			sender.sendMq("/third", "ex.event.bankBind", MessageFormat.format("bankBind.{0}.rKey", appid),
					bankBindEvent, true);
			throw new CalfException(e.getMessage(), CoreResultEnum.REMOTE_ERROR);
		}
	}

	@Override
	public OrderBankVo getOrderBankVo(String appid, String orderId) {
		return orderBankBindMapper.getOrderBankVo(appid, orderId);
	}

	@Override
	public boolean checkOrderBankBind(String appid, String orderId) {
		return orderBankBindMapper.getOrderBankBo(appid, orderId) != null;
	}

	@Override
	public BankVerifyCodeVo getNewBankVerifyVo(String appid, String appName, String orderId,
			BankVerifyCodeDto bankVerifyCodeDto) {
		// 获取订单用户
		OrderUserBo orderUserBo = orderUserMapper.getOrderUserBo(appid, orderId);
		// 获取绑卡规则
		OrderBindRuleVo orderBindRuleVo = getOrderBindRuleVo(appid, orderId);

		CardBindDto cardBindDto = new CardBindDto();
		cardBindDto.setAppid(appid);
		cardBindDto.setAppName(appName);
		cardBindDto.setBankNo(bankVerifyCodeDto.getBankCardNo());
		cardBindDto.setChannelType(orderBindRuleVo.getRule());
		cardBindDto.setIdCard(orderUserBo.getIdentityNo());
		cardBindDto.setOrderNo(orderId);
		cardBindDto.setResvPhone(bankVerifyCodeDto.getReservedMobile());
		cardBindDto.setUserName(orderUserBo.getUserName());
		cardBindDto.setBankCode(bankVerifyCodeDto.getBankCode());

		CardBindVo cardBindVo;

		try {
			cardBindVo = centerClientFactory.getCenterClient(appid).cardBindSendSms(cardBindDto);

		} catch (RemoteServerException e) {
			logger.error("获取验证码失败，appid:{};orderId:{};bankVerifyCodeDto:{};message:{}", appid, orderId,
					bankVerifyCodeDto, e.getMessage());
			throw new CalfException(e.getMessage(), CoreResultEnum.REMOTE_ERROR);
		}

		BankVerifyCodeVo bankVerifyCodeVo = new BankVerifyCodeVo();
		bankVerifyCodeVo.setSignNo(cardBindVo.getSerialNo());
		return bankVerifyCodeVo;
	}

	@Override
	public boolean bindNewBankCard(String appid, String orderId, String appName, OrderBankDto orderBankDto) {

		OrderBindRuleVo orderBindRuleVo = getOrderBindRuleVo(appid, orderId);
		// 事件
		BankBindEvent bankBindEvent = new BankBindEvent();
		// 事件id
		String messageId = EventUtil.getMessageId();
		try {
			CardBindConfirmDto cardBindConfirmDto = new CardBindConfirmDto();
			cardBindConfirmDto.setAppid(appid);
			cardBindConfirmDto.setAppName(appName);
			cardBindConfirmDto.setChannelType(orderBindRuleVo.getRule());
			cardBindConfirmDto.setSerialNo(orderBankDto.getSignNo());
			cardBindConfirmDto.setSmsCode(orderBankDto.getVerifyCode());

			CardBindVo cardBindVo = centerClientFactory.getCenterClient(appid).cardBindConfirm(cardBindConfirmDto);

			// 不支持的银行卡类别
			if (!cardBindVo.getCardType().equals(1)) {
				throw new CalfException(CoreResultEnum.NOT_SUPPORT_CREDIT_TYPE_ERROR);
			}

			// 是否支持该银行卡
			RestResult<Void> restResult = baseCloudHystrixService.checkBank(appid,
					Integer.valueOf(orderBindRuleVo.getRule()), cardBindVo.getBankCode());
			if (restResult == null || !restResult.isSuccess()) {
				throw new CalfException(CoreResultEnum.NON_SUPPORT_BANK);
			}

			// 事件头
			bankBindEvent.setEventHeader(EventUtil.buildEventHeader(messageId, appid, orderId, "", true));
			// 事件主体
			EventBankBind eventBankBind = new EventBankBind();
			eventBankBind.setBankCardNo(cardBindVo.getBankNo());
			eventBankBind.setBankCode(cardBindVo.getBankCode());
			eventBankBind.setBankName(cardBindVo.getBankName());
			eventBankBind.setCardType(cardBindVo.getCardType());
			eventBankBind.setIdentityNo(cardBindVo.getIdCard());
			eventBankBind.setReservedMobile(cardBindVo.getReservedPhone());
			eventBankBind.setUserName(cardBindVo.getUserName());
			eventBankBind.setSignNo(orderBankDto.getSignNo());
			bankBindEvent.setEventBankBind(eventBankBind);
			return sender.sendMq("/third", "ex.event.bankBind", MessageFormat.format("bankBind.{0}.rKey", appid),
					bankBindEvent, true);
		} catch (RemoteServerException e) {
			logger.error("绑卡失败，appid:{};orderId:{};orderBankDto:{};message:{}", appid, orderId, orderBankDto,
					e.getMessage());
			// 事件头
			bankBindEvent.setEventHeader(EventUtil.buildEventHeader(messageId, appid, orderId, "", false));
			// 事件失败原因
			bankBindEvent.setEventFailureResult(EventUtil.buildEventFailureResult(e));
			sender.sendMq("/third", "ex.event.bankBind", MessageFormat.format("bankBind.{0}.rKey", appid),
					bankBindEvent, true);
			throw new CalfException(e.getMessage(), CoreResultEnum.REMOTE_ERROR);
		}

	}

	@Deprecated
	@Override
	public BankVerifyCodeVo getBankVerifyVo(String appid, String orderId, BankVerifyCodeDto bankVerifyCodeDto) {

		// 获取订单用户
		OrderUserBo orderUserBo = orderUserMapper.getOrderUserBo(appid, orderId);

		YijifuBindCardAuthDto yijifuBindCardAuthDto = new YijifuBindCardAuthDto();
		yijifuBindCardAuthDto.setAppName(bankVerifyCodeDto.getAppName());
		yijifuBindCardAuthDto.setBankNo(bankVerifyCodeDto.getBankCardNo());
		yijifuBindCardAuthDto.setIdCard(orderUserBo.getIdentityNo());
		yijifuBindCardAuthDto.setOrderNo(orderId);
		yijifuBindCardAuthDto.setReservedPhone(bankVerifyCodeDto.getReservedMobile());
		yijifuBindCardAuthDto.setUserName(orderUserBo.getUserName());

		YijifuBindCardVo yijifuBindCardVo;
		try {
			yijifuBindCardVo = centerClientFactory.getCenterClient(appid).yjfProtocolBindNew(yijifuBindCardAuthDto);
		} catch (RemoteServerException e) {
			logger.error("获取验证码失败，appid:{};orderId:{};bankVerifyCodeDto:{};message:{}", appid, orderId,
					bankVerifyCodeDto, e.getMessage());
			throw new CalfException(e.getMessage(), CoreResultEnum.REMOTE_ERROR);
		}
		BankVerifyCodeVo bankVerifyCodeVo = new BankVerifyCodeVo();
		bankVerifyCodeVo.setSignNo(yijifuBindCardVo.getSignNo());
		return bankVerifyCodeVo;
	}

	@Deprecated
	@Override
	public boolean bindBankCard(String appid, String orderId, OrderBankDto orderBankDto) {

		YijifuBindCardConfirmDto yijifuBindCardConfirmDto = new YijifuBindCardConfirmDto();
		yijifuBindCardConfirmDto.setAuthMsg(orderBankDto.getVerifyCode());
		yijifuBindCardConfirmDto.setSignNo(orderBankDto.getSignNo());

		// 事件
		BankBindEvent bankBindEvent = new BankBindEvent();
		// 事件id
		String messageId = EventUtil.getMessageId();
		try {

			YijifuBindCardVo yijifuBindCardVo = centerClientFactory.getCenterClient(appid)
					.yjfProtocolBindConfirmNew(yijifuBindCardConfirmDto);

			// 不支持的银行卡类别
			if (!yijifuBindCardVo.getCardType().equals(1)) {
				throw new CalfException(CoreResultEnum.NOT_SUPPORT_CREDIT_TYPE_ERROR);
			}

			// 是否支持改银行卡
			RestResult<Void> restResult = baseCloudHystrixService.checkBank(appid, 2, yijifuBindCardVo.getBankCode());
			if (restResult == null || !restResult.isSuccess()) {
				throw new CalfException(CoreResultEnum.NON_SUPPORT_BANK);
			}

			// 事件头
			bankBindEvent.setEventHeader(EventUtil.buildEventHeader(messageId, appid, orderId, "", true));
			// 事件主体
			EventBankBind eventBankBind = new EventBankBind();
			eventBankBind.setBankCardNo(yijifuBindCardVo.getBankNo());
			eventBankBind.setBankCode(yijifuBindCardVo.getBankCode());
			eventBankBind.setBankName(yijifuBindCardVo.getBankName());
			eventBankBind.setCardType(yijifuBindCardVo.getCardType());
			eventBankBind.setIdentityNo(yijifuBindCardVo.getIdCard());
			eventBankBind.setReservedMobile(yijifuBindCardVo.getReservedPhone());
			eventBankBind.setUserName(yijifuBindCardVo.getUserName());
			eventBankBind.setSignNo(yijifuBindCardVo.getSignNo());
			bankBindEvent.setEventBankBind(eventBankBind);
			return sender.sendMq("/third", "ex.event.bankBind", MessageFormat.format("bankBind.{0}.rKey", appid),
					bankBindEvent, true);
		} catch (RemoteServerException e) {
			logger.error("绑卡失败，appid:{};orderId:{};orderBankDto:{};message:{}", appid, orderId, orderBankDto,
					e.getMessage());
			// 事件头
			bankBindEvent.setEventHeader(EventUtil.buildEventHeader(messageId, appid, orderId, "", false));
			// 事件失败原因
			bankBindEvent.setEventFailureResult(EventUtil.buildEventFailureResult(e));
			sender.sendMq("/third", "ex.event.bankBind", MessageFormat.format("bankBind.{0}.rKey", appid),
					bankBindEvent, true);
			throw new CalfException(e.getMessage(), CoreResultEnum.REMOTE_ERROR);
		}

	}

}
