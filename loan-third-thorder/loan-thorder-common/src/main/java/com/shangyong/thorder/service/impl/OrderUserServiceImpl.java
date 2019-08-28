package com.shangyong.thorder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.thcore.vo.OrderUserVo;
import com.shangyong.thorder.dao.OrderUserMapper;
import com.shangyong.thorder.service.OrderUserService;

@Service
public class OrderUserServiceImpl implements OrderUserService {

	@Autowired
	private OrderUserMapper orderUserMapper;
	
	@Override
	public OrderUserVo getOrderUserVo(String appid, String orderId) {
		return orderUserMapper.getOrderUserVo(appid, orderId);
	}

	@Override
	public boolean checkOlder(String appid, String identityNo) {
		return orderUserMapper.countHasRepayment(appid,identityNo)>0;
	}

}
