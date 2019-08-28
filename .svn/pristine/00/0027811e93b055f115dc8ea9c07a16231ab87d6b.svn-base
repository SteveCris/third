package com.shangyong.thzlqb.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.thcore.event.AuditEvent;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventUserInfo;
import com.shangyong.thzlqb.bo.OrderSimpleBo;
import com.shangyong.thzlqb.bo.CoreAuditUserInfoBo;
import com.shangyong.thzlqb.send.Sender;
import com.shangyong.thzlqb.service.CoreAuditService;
import com.shangyong.thzlqb.service.CoreOrderService;
import com.shangyong.thzlqb.service.ZlqbUserService;
import com.shangyong.thzlqb.utils.EventUtil;
import com.shangyong.thzlqb.zlqb.utils.ZlqbUtil;

import java.util.Objects;

@Service
public class CoreAuditServiceImpl implements CoreAuditService {

	private Logger logger = LoggerFactory.getLogger(CoreAuditServiceImpl.class);

	@Autowired
	private Sender sender;

	@Autowired
	private CoreOrderService coreOrderService;

	@Autowired
	private ZlqbUserService zlqbUserService;

	//风控系统回调:订单审核是否成功
	@Override
	public boolean processAudit(String orderNo, boolean ifSuccess) {
		OrderSimpleBo orderSimpleBo = coreOrderService.getOrderSimpleBo(orderNo);
		if(Objects.isNull(orderSimpleBo)){
			logger.error("该订单查无订单记录信息 orderNo --》{}",orderNo);
			return false;
		}
		CoreAuditUserInfoBo coreAuditUserInfoBo = zlqbUserService.getCoreAuditUserInfoBo(orderNo);
		if(Objects.isNull(coreAuditUserInfoBo)){
			logger.error("该订单查无用户信息 orderNo --》{}",orderNo);
			return false;
		}
		AuditEvent auditEvent = new AuditEvent();
		auditEvent.setEventHeader(EventUtil.buildEventHeader(EventUtil.getMessageId(), ZlqbUtil.getAppid(),
				orderSimpleBo.getOrderId(), String.valueOf(ZlqbUtil.getAppName()), ifSuccess));
		EventUserInfo eventUserInfo = new EventUserInfo();
		eventUserInfo.setAddress(coreAuditUserInfoBo.getAddress());
		eventUserInfo.setIdentityNo(coreAuditUserInfoBo.getIdCard());
		eventUserInfo.setMobile(coreAuditUserInfoBo.getUserMobile());
		eventUserInfo.setOtherOrderId(orderNo);
		eventUserInfo.setUserName(coreAuditUserInfoBo.getUserName());
		eventUserInfo.setPhoneIdNumberMd5(
				DigestUtils.md5Hex(coreAuditUserInfoBo.getUserMobile() + coreAuditUserInfoBo.getIdCard().toLowerCase())
						.toLowerCase());
		auditEvent.setEventUserInfo(eventUserInfo);
		if (!ifSuccess) {
			EventFailureResult eventFailureResult = new EventFailureResult();
			eventFailureResult.setCode("500");
			eventFailureResult.setReason("审核失败，单号：" + orderNo);
			auditEvent.setEventFailureResult(eventFailureResult);
		}
		return sender.sendMq("/third", "ex.event.audit", "audit." + ZlqbUtil.getAppid() + ".rKey", auditEvent, true);
	}

}
