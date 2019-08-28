package com.shangyong.thryt.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.thryt.bo.RytOrderBo;
import com.shangyong.thryt.bo.RytOrderLinkBo;
import com.shangyong.thryt.bo.RytOrderSimpleBo;
import com.shangyong.thryt.contants.UuidPrefix;
import com.shangyong.thryt.dao.RytOrderLinkMapper;
import com.shangyong.thryt.dao.RytOrderMapper;
import com.shangyong.thryt.entity.RytOrder;
import com.shangyong.thryt.entity.RytOrderLink;
import com.shangyong.thryt.service.RytOrderService;
import com.shangyong.thryt.utils.IdUtil;
import com.shangyong.thryt.utils.RytUtil;
import com.shangyong.thryt.vo.RytOrderSimpleVo;

@Service
public class RytOrderServiceImpl implements RytOrderService {

	@Autowired
	private RytOrderMapper rytOrderMapper;

	@Autowired
	private RytOrderLinkMapper rytOrderLinkMapper;

	
	@Override
	public RytOrderLinkBo getRytOrderLinkBo(String orderNo, int newStatus, int oldStatus) {
		return rytOrderLinkMapper.getRytOrderLinkBo(orderNo,newStatus,oldStatus);
	}

	@Override
	public boolean checkStatus(String orderNo, int expectStatus) {
		RytOrderBo rytOrderBo = getRytOrderBo(orderNo);
		return rytOrderBo.getStatus() == expectStatus;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateOrderStatus(String orderNo, int newStatus, int oldStatus, boolean ifFinish, String remark) {
		boolean isSuccess = rytOrderMapper.updateOrderStatus(orderNo, newStatus, oldStatus, ifFinish, remark) > 0;
		if (isSuccess) {
			RytOrderLink rytOrderLink = new RytOrderLink();
			rytOrderLink.setCreateTime(new Date());
			rytOrderLink.setLinkId(IdUtil.getNumberUuid(UuidPrefix.RYT_ORDER_LINK));
			rytOrderLink.setNewStatus(newStatus);
			rytOrderLink.setOldStatus(oldStatus);
			rytOrderLink.setOrderNo(orderNo);
			rytOrderLink.setRemark(remark);
			rytOrderLinkMapper.insert(rytOrderLink);
		}
		return isSuccess;
	}

	@Override
	public boolean isExist(String orderNo) {
		return rytOrderMapper.count(orderNo) > 0;
	}

	@Override
	public boolean create(RytOrder rytOrder) {
		return rytOrderMapper.insert(rytOrder) > 0;
	}

	@Override
	public RytOrderBo getRytOrderBo(String orderNo) {
		return rytOrderMapper.getRytOrderBo(orderNo);
	}

	@Override
	public RytOrderSimpleBo getRytOrderSimpleBoByOrderId(String orderId) {
		return rytOrderMapper.getRytOrderSimpleBoByOrderId(orderId);
	}

	@Override
	public RytOrderSimpleVo getRytOrderSimpleVo(String orderNo) {
		RytOrderBo rytOrderBo = getRytOrderBo(orderNo);
		RytOrderSimpleVo rytOrderSimpleVo = new RytOrderSimpleVo();
		rytOrderSimpleVo.setAppid(RytUtil.getAppid());
		rytOrderSimpleVo.setAppName(RytUtil.getAppName());
		rytOrderSimpleVo.setfUrl(RytUtil.getFailureUrl());
		rytOrderSimpleVo.setsUrl(RytUtil.getSuccessUrl());
		rytOrderSimpleVo.setOrderId(rytOrderBo.getOrderId());
		return rytOrderSimpleVo;
	}

}
