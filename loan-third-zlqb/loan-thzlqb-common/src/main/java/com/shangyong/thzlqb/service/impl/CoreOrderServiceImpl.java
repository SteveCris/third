package com.shangyong.thzlqb.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.loan.ext.util.IdUtil;
import com.shangyong.loan.ext.util.ResultUtil;
import com.shangyong.rest.feign.OrderCloudHystrixService;
import com.shangyong.thcore.dto.OrderLoanDto;
import com.shangyong.thcore.vo.OrderLoanVo;
import com.shangyong.thzlqb.bo.OrderSimpleBo;
import com.shangyong.thzlqb.contants.UuidPrefix;
import com.shangyong.thzlqb.dao.ZlqbOrderLinkMapper;
import com.shangyong.thzlqb.dao.ZlqbOrderMapper;
import com.shangyong.thzlqb.entity.ZlqbOrder;
import com.shangyong.thzlqb.entity.ZlqbOrderLink;
import com.shangyong.thzlqb.service.CoreOrderService;
import com.shangyong.thzlqb.zlqb.utils.ZlqbUtil;

@Service
public class CoreOrderServiceImpl implements CoreOrderService {

	private Logger logger = LoggerFactory.getLogger(CoreOrderServiceImpl.class);

	@Autowired
	private ZlqbOrderMapper zlqbOrderMapper;

	@Autowired
	private ZlqbOrderLinkMapper zlqbOrderLinkMapper;

	@Autowired
	private OrderCloudHystrixService orderCloudHystrixService;

	@Override
	public boolean checkStatus(String orderNo, int expectStatus) {
		OrderSimpleBo orderSimpleBo = getOrderSimpleBo(orderNo);
		return orderSimpleBo.getStatus() == expectStatus;
	}

	@Override
	public boolean updateOrderStatus(String orderNo, int newStatus, int oldStatus, boolean ifFinish, String remark) {
		boolean isSuccess = zlqbOrderMapper.updateOrderStatus(orderNo, newStatus, oldStatus, ifFinish, remark) > 0;
		if (isSuccess) {
			ZlqbOrderLink zlqbOrderLink = new ZlqbOrderLink();
			zlqbOrderLink.setCreateTime(new Date());
			zlqbOrderLink.setLinkId(IdUtil.getNumberUuid(UuidPrefix.LINK));
			zlqbOrderLink.setNewStatus(newStatus);
			zlqbOrderLink.setOldStatus(oldStatus);
			zlqbOrderLink.setOrderNo(orderNo);
			zlqbOrderLink.setRemark(remark);
			zlqbOrderLinkMapper.insert(zlqbOrderLink);
		}
		return isSuccess;
	}

	@Override
	public boolean isExist(String orderNo) {
		return zlqbOrderMapper.count(orderNo) > 0;
	}

	@Override
	public boolean create(ZlqbOrder zlqbOrder) {
		return zlqbOrderMapper.insertSelective(zlqbOrder) > 0;
	}

	@Override
	public OrderSimpleBo getOrderSimpleBo(String orderNo) {
		return zlqbOrderMapper.getOrderSimpleBoByOrderNo(orderNo);
	}

	@Override
	public OrderSimpleBo getOrderSimpleBoByOrderId(String orderId) {
		if (StringUtils.isEmpty(orderId)) {
			logger.error("通过订单号查询记录时出现空值");
			return null;
		}
		return zlqbOrderMapper.getOrderSimpleBoByOrderId(orderId);
	}

	// **************************远程交互**************************

	@Override
	public OrderLoanVo getRemoteOrder(String orderNo) {
		return ResultUtil.checkAndGet(orderCloudHystrixService.orderSearch(ZlqbUtil.getAppid(), orderNo));
	}

	@Override
	public OrderLoanVo createRemoteOrder(String orderNo) {
		OrderLoanDto orderLoanDto = new OrderLoanDto();
		orderLoanDto.setOtherOrderId(orderNo);
		return ResultUtil.checkAndGet(orderCloudHystrixService.orderCreate(ZlqbUtil.getAppid(), orderLoanDto));
	}

	@Override
	public boolean toWaitAuditRemoteOrder(String orderId) {
		return ResultUtil.check(orderCloudHystrixService.orderToWaitAudit(ZlqbUtil.getAppid(), orderId));
	}

	@Override
	public boolean checkHasOnWayRemoteOrder(String identityNo) {
		return ResultUtil.check(orderCloudHystrixService.orderOnWayCheck(ZlqbUtil.getAppid(), identityNo));
	}



	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateOrderisSign(String orderNo) {
		return zlqbOrderMapper.updateOrderIsSign(orderNo)>0;
	}

}
