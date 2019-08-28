package com.shangyong.thryt.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.thcore.event.AuditEvent;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventUserInfo;
import com.shangyong.thryt.bo.RytAuditUserInfoBo;
import com.shangyong.thryt.bo.RytOrderBo;
import com.shangyong.thryt.send.Sender;
import com.shangyong.thryt.service.RytAuditService;
import com.shangyong.thryt.service.RytOrderService;
import com.shangyong.thryt.service.RytUserService;
import com.shangyong.thryt.utils.EventUtil;
import com.shangyong.thryt.utils.RytUtil;

@Service
public class RytAuditServiceImpl implements RytAuditService {

	@Autowired
	private Sender sender;

	@Autowired
	private RytOrderService rytOrderService;

	@Autowired
	private RytUserService rytUserService;

	@Override
	public boolean processAudit(String orderNo, boolean ifSuccess) {
		RytOrderBo rytOrderBo = rytOrderService.getRytOrderBo(orderNo);
		RytAuditUserInfoBo rytAuditUserInfoBo = rytUserService.getRytAuditUserInfoBo(orderNo);

		AuditEvent auditEvent = new AuditEvent();
		auditEvent.setEventHeader(EventUtil.buildEventHeader(EventUtil.getMessageId(), RytUtil.getAppid(),
				rytOrderBo.getOrderId(), String.valueOf(RytUtil.getAppName()), ifSuccess));
		EventUserInfo eventUserInfo = new EventUserInfo();
		eventUserInfo.setAddress(rytAuditUserInfoBo.getAddress());
		eventUserInfo.setIdentityNo(rytAuditUserInfoBo.getIdCard());
		eventUserInfo.setMobile(rytAuditUserInfoBo.getUserMobile());
		eventUserInfo.setOtherOrderId(orderNo);
		eventUserInfo.setUserName(rytAuditUserInfoBo.getUserName());
		eventUserInfo.setPhoneIdNumberMd5(
				DigestUtils.md5Hex(rytAuditUserInfoBo.getUserMobile() + rytAuditUserInfoBo.getIdCard().toLowerCase()).toLowerCase());
		auditEvent.setEventUserInfo(eventUserInfo);
		if (!ifSuccess) {
			EventFailureResult eventFailureResult = new EventFailureResult();
			eventFailureResult.setCode("500");
			eventFailureResult.setReason("审核失败，单号：" + orderNo);
			auditEvent.setEventFailureResult(eventFailureResult);
		}
		return sender.sendMq("/third", "ex.event.audit", "audit." + RytUtil.getAppid() + ".rKey", auditEvent, true);
	}

}
