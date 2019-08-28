package com.shangyong.thorder.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.thorder.contants.UuidPrefix;
import com.shangyong.thorder.dao.OrderEquityMapper;
import com.shangyong.thorder.entity.OrderEquity;
import com.shangyong.thorder.service.OrderEquityService;
import com.shangyong.thorder.utils.IdUtil;

@Service
public class OrderEquityServiceImpl implements OrderEquityService {

	@Autowired
	private OrderEquityMapper orderEquityMapper;

	@Override
	public OrderEquity getOrderEquity(String appid, String orderId) {
		return orderEquityMapper.getOrderEquity(appid, orderId);
	}

	@Override
	public OrderEquity insertOrIgnore(String appid, String orderId) {
		OrderEquity orderEquity = new OrderEquity();
		orderEquity.setEqId(IdUtil.getNumberUuid(UuidPrefix.EQUITY));
		orderEquity.setAppid(appid);
		orderEquity.setCreateTime(new Date());
		orderEquity.setOrderId(orderId);
		orderEquity.setState(0);
		if (orderEquityMapper.insertOrIgnore(orderEquity) > 0) {
			return orderEquity;
		} else {
			return null;
		}

	}

	@Override
	public boolean updateOrderEquity(OrderEquity orderEquity) {
		return orderEquityMapper.updateByPrimaryKeySelective(orderEquity) > 0;
	}

}
