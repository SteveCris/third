package com.shangyong.thorder.service.impl;

import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.center.client.CenterClientFactory;
import com.shangyong.center.client.exception.RemoteServerException;
import com.shangyong.center.dto.InsuranceQueryDto;
import com.shangyong.center.vo.InsuranceQueryResponseVo;
import com.shangyong.common.utils.LocalDateUtil;
import com.shangyong.thorder.bo.OrderBorrowInsuranceBo;
import com.shangyong.thorder.dao.OrderBorrowApplyMapper;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.exception.CalfException;
import com.shangyong.thorder.service.OrderInsuranceService;
import com.shangyong.thorder.vo.OrderInsuranceVo;

@Service
public class OrderInsuranceServiceImpl implements OrderInsuranceService {

	private Logger logger = LoggerFactory.getLogger(OrderInsuranceServiceImpl.class);

	@Autowired
	private CenterClientFactory centerClientFactory;

	@Autowired
	private OrderBorrowApplyMapper orderBorrowApplyMapper;

	
	@Override
	public OrderInsuranceVo getOrderInsuranceVo(String appid, String orderId) {

		OrderBorrowInsuranceBo orderBorrowInsuranceBo = orderBorrowApplyMapper.getOrderBorrowInsuranceBo(appid,
				orderId);
		InsuranceQueryDto insuranceQueryDto = new InsuranceQueryDto();
		insuranceQueryDto.setApplicationId(orderBorrowInsuranceBo.getApplyId());
		insuranceQueryDto.setStartDate(LocalDateUtil.plus(orderBorrowInsuranceBo.getCreateTime(), 3, ChronoUnit.DAYS));
		insuranceQueryDto.setStatement(orderBorrowInsuranceBo.getFinanceOrderId());
		try {
			InsuranceQueryResponseVo insuranceQueryResponseVo = centerClientFactory.getCenterClient(appid)
					.policyQuery(insuranceQueryDto);
			OrderInsuranceVo orderInsuranceVo = new OrderInsuranceVo();
			BeanUtils.copyProperties(insuranceQueryResponseVo, orderInsuranceVo);
			return orderInsuranceVo;
		} catch (RemoteServerException e) {
			logger.info("查询保单信息失败，appid:{};orderId:{}", appid, orderId, e);
			throw new CalfException(CoreResultEnum.REMOTE_ERROR);
		}

	}

}
