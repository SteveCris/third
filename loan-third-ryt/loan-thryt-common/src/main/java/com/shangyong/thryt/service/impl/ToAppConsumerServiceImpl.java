package com.shangyong.thryt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.common.entity.RestResult;
import com.shangyong.rest.feign.OrderBankCloudHystrixService;
import com.shangyong.rest.feign.ThirdInfoPushHystrixService;
import com.shangyong.rest.vo.ThirdPushUserReqVo;
import com.shangyong.rest.vo.ThirdPushUserReqVo.BankcardRecord;
import com.shangyong.rest.vo.ThirdPushUserReqVo.IceRecord;
import com.shangyong.rest.vo.ThirdPushUserReqVo.UserInfo;
import com.shangyong.thcore.vo.OrderBankVo;
import com.shangyong.thryt.bo.RytOrderBo;
import com.shangyong.thryt.bo.RytOrderLinkBo;
import com.shangyong.thryt.bo.RytUserInfoBo;
import com.shangyong.thryt.enums.RytResultEnum;
import com.shangyong.thryt.exception.CalfException;
import com.shangyong.thryt.service.RytOrderService;
import com.shangyong.thryt.service.RytUserService;
import com.shangyong.thryt.service.ToAppConsumerService;
import com.shangyong.thryt.utils.RytUtil;

@Service
public class ToAppConsumerServiceImpl implements ToAppConsumerService {

	private Logger logger = LoggerFactory.getLogger(ToAppConsumerServiceImpl.class);

	@Autowired
	private RytOrderService rytOrderService;

	@Autowired
	private RytUserService rytUserService;

	@Autowired
	private ThirdInfoPushHystrixService thirdInfoPushHystrixService;

	@Autowired
	private OrderBankCloudHystrixService orderBankCloudHystrixService;

	@Override
	public boolean push(String orderNo) {
		// 获取订单信息
		RytOrderBo rytOrderBo = rytOrderService.getRytOrderBo(orderNo);
		RytOrderLinkBo rytOrderLinkBo= rytOrderService.getRytOrderLinkBo(orderNo, 40, 30);

		String orderId = rytOrderBo.getOrderId();
		String appid = RytUtil.getAppid();

		RestResult<OrderBankVo> bankResult = orderBankCloudHystrixService.orderBankSearch(appid, orderId);
		if (bankResult == null || !bankResult.isSuccess()) {
			logger.error("调用查询银行卡信息失败，orderNo号：{}，result：{}", orderNo, bankResult);
			throw new CalfException(RytResultEnum.REMOTE_ERROR);
		}
		OrderBankVo orderBankVo = bankResult.getData().getBody();

		String toAppid = RytUtil.getToAppid();
		int toAppName = RytUtil.getToAppName();
		ThirdPushUserReqVo thirdPushUserReqVo = new ThirdPushUserReqVo();
		thirdPushUserReqVo.setAppid(toAppid);
		thirdPushUserReqVo.setAppName(toAppName);
		thirdPushUserReqVo.setAuthPassedTime(rytOrderLinkBo.getCreateTime().getTime());

		RytUserInfoBo rytUserInfoBo = rytUserService.getUserInfo(orderNo);

		// 绑卡信息
		BankcardRecord bankcardRecord = new BankcardRecord();
		bankcardRecord.setBankCode(orderBankVo.getBankCode());
		bankcardRecord.setBankName(orderBankVo.getBankName());
		bankcardRecord.setCardNum(orderBankVo.getBankCardNo());
		bankcardRecord.setReservedPhone(orderBankVo.getReservedMobile());
		bankcardRecord.setSignNo(orderBankVo.getSignNo());
		thirdPushUserReqVo.setBankcardRecord(bankcardRecord);
		// 渠道信息
		thirdPushUserReqVo.setChannel(RytUtil.getChannel());
		// 紧急联系人信息
		List<IceRecord> list = new ArrayList<>();
		thirdPushUserReqVo.setListIceRecord(list);
		// os版本信息
		int os;
		if ("iphone".equalsIgnoreCase(rytUserInfoBo.getOsVersion())) {
			os = 2;
		} else if ("Android".equalsIgnoreCase(rytUserInfoBo.getOsVersion())) {
			os = 1;
		} else {
			os = 0;
		}

		thirdPushUserReqVo.setOsVersion(os);
		// 用户customerId
		thirdPushUserReqVo.setUid(rytUserInfoBo.getUserId());
		// 用户名称信息
		thirdPushUserReqVo.setUname(rytUserInfoBo.getUserName());
		// 用户手机号信息
		thirdPushUserReqVo.setUphone(rytUserInfoBo.getUserMobile());
		// 用户基本信息
		UserInfo userInfo = new UserInfo();
		userInfo.setFacePic(rytUserInfoBo.getFacePic());
		userInfo.setIdCardNo(rytUserInfoBo.getIdCard());
		userInfo.setIdCardPic(rytUserInfoBo.getIdCardPic());
		userInfo.setIdCardThePic(rytUserInfoBo.getIdCardPicThe());
		userInfo.setIssuedBy(rytUserInfoBo.getIssuedBy());
		userInfo.setRace(rytUserInfoBo.getRace());
		userInfo.setRegisteredAddress(rytUserInfoBo.getIdCardAddress());
		userInfo.setValidDate(rytUserInfoBo.getValidDate());
		thirdPushUserReqVo.setUserInfo(userInfo);

		RestResult<Void> restResult = thirdInfoPushHystrixService.push(toAppid, thirdPushUserReqVo);
		if (restResult == null || !restResult.isSuccess()) {
			logger.error("调用推送用户信息失败，orderNo号：{}，result：{}", orderNo, restResult);
			throw new CalfException(RytResultEnum.REMOTE_ERROR);
		}
		return true;
	}
}
