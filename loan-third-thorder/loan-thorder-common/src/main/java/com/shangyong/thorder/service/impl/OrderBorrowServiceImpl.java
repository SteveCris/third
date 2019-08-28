package com.shangyong.thorder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.thcore.vo.OrderBorrowVo;
import com.shangyong.thorder.dao.OrderBorrowApplyMapper;
import com.shangyong.thorder.service.OrderBorrowService;

@Service
public class OrderBorrowServiceImpl implements OrderBorrowService {

	@Autowired
	private OrderBorrowApplyMapper orderBorrowApplyMapper;
	
	@Override
	public OrderBorrowVo getOrderBorrowVo(String appid, String orderId) {
	
		return orderBorrowApplyMapper.getOrderBorrowVo(appid, orderId);
	}

}
