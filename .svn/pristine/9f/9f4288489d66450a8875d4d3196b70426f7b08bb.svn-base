package com.shangyong.thorder.service.process.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.center.client.CenterClientFactory;
import com.shangyong.center.dto.CompositeBehindDto;
import com.shangyong.center.dto.CompositeDto;
import com.shangyong.center.dto.ContractLoanFMSubjectDto;
import com.shangyong.center.dto.ContractUserInfoDto;
import com.shangyong.center.dto.ContractUserSealDto;
import com.shangyong.center.dto.LoanFMCompositeContractDto;
import com.shangyong.center.vo.CompositeVo;
import com.shangyong.center.vo.ContractApplyVo;
import com.shangyong.common.utils.LocalDateUtil;
import com.shangyong.thcore.bo.OrderBankBo;
import com.shangyong.thcore.bo.OrderCreditBo;
import com.shangyong.thcore.bo.OrderPreCreditBo;
import com.shangyong.thcore.bo.OrderUserBo;
import com.shangyong.thcore.vo.ProductConfigVo;
import com.shangyong.thorder.dao.OrderBankBindMapper;
import com.shangyong.thorder.dao.OrderCreditMapper;
import com.shangyong.thorder.dao.OrderPreCreditMapper;
import com.shangyong.thorder.dao.OrderUserMapper;
import com.shangyong.thorder.service.process.CreditProcess;
import com.shangyong.thorder.service.process.CreditProcessFactory;

@Service(CreditProcessFactory.FM_NAME_TYPE)
public class FmCreditProcess implements CreditProcess {

	@Autowired
	private CenterClientFactory centerClientFactory;

	@Autowired
	private OrderBankBindMapper orderBankBindMapper;

	@Autowired
	private OrderUserMapper orderUserMapper;

	@Autowired
	private OrderPreCreditMapper orderPreCreditMapper;

	@Autowired
	private OrderCreditMapper oderCreditMapper;

	@Override
	public CompositeVo applyBefore(String appid, String orderId, CompositeDto compositeDto) {
		return centerClientFactory.getCenterClient(appid).compositeFMApplyBefore(compositeDto);
	}

	@Override
	public CompositeVo applyBehind(String appid, String orderId, CompositeBehindDto compositeBehindDto) {
		return centerClientFactory.getCenterClient(appid).compositeFMApplyBehind(compositeBehindDto);
	}

	@Override
	public ContractApplyVo safety(String appid, String orderId, int index, int sceneId, ProductConfigVo productConfigVo) {

		OrderBankBo orderBankBo = orderBankBindMapper.getOrderBankBo(appid, orderId);
		OrderUserBo orderUserBo = orderUserMapper.getOrderUserBo(appid, orderId);

		LoanFMCompositeContractDto loanFMCompositeContractDto = new LoanFMCompositeContractDto();

		Date loanTime;
		// 前置
		if (index == 0) {
			OrderPreCreditBo orderPreCreditBo = orderPreCreditMapper.getOrderPreCreditBo(appid, orderId);
			loanTime = orderPreCreditBo.getCreateTime();
			loanFMCompositeContractDto.setCreditUuid(orderPreCreditBo.getCreditUuid());
		}
		// 后置
		else {
			OrderCreditBo orderCreditBo = oderCreditMapper.getOrderCreditBo(appid, orderId);
			loanTime = orderCreditBo.getCreateTime();
			loanFMCompositeContractDto.setCreditUseUuid(orderCreditBo.getCreditUseUuid());

		}

		// 合同主体信息
		ContractLoanFMSubjectDto contractLoanFMSubjectDto = new ContractLoanFMSubjectDto();
		contractLoanFMSubjectDto.setApplicationName(productConfigVo.getApplicationName());
		contractLoanFMSubjectDto.setBorroweraccountNumber(orderBankBo.getBankCardNo());
		contractLoanFMSubjectDto.setBorrowerCertNo(orderBankBo.getIdentityNo());
		contractLoanFMSubjectDto.setBorrowerName(orderBankBo.getUserName());
		contractLoanFMSubjectDto.setBorroweropeningInstitution(orderBankBo.getBankName());
		contractLoanFMSubjectDto.setBorrowerTell(orderUserBo.getMobile());

		LocalDateTime localLoanTime = LocalDateUtil.parse(loanTime);
		contractLoanFMSubjectDto.setLoanTimeYear(localLoanTime.getYear());
		contractLoanFMSubjectDto.setLoanTimeMonth(localLoanTime.getMonthValue());
		contractLoanFMSubjectDto.setLoanTimeDate(localLoanTime.getDayOfMonth());

		contractLoanFMSubjectDto.setLoanTimeHour(localLoanTime.getHour());
		contractLoanFMSubjectDto.setLoanTimeMinute(localLoanTime.getMinute());
		contractLoanFMSubjectDto.setLoanTimeSecond(localLoanTime.getSecond());

		contractLoanFMSubjectDto.setResidentialAddress(orderUserBo.getAddress());

		loanFMCompositeContractDto.setContractLoanFMSubjectDto(contractLoanFMSubjectDto);

		// 用户主体信息
		List<ContractUserInfoDto> contractUserInfoDtoList = new ArrayList<>();
		ContractUserInfoDto contractUserInfoDto = new ContractUserInfoDto();
		contractUserInfoDto.setBankNo(orderBankBo.getBankCardNo());
		contractUserInfoDto.setEmail("");
		contractUserInfoDto.setIdCard(orderUserBo.getIdentityNo());
		contractUserInfoDto.setMobile(orderUserBo.getMobile());
		contractUserInfoDto.setUserName(orderUserBo.getUserName());
		contractUserInfoDtoList.add(contractUserInfoDto);
		loanFMCompositeContractDto.setContractUserInfoDtoList(contractUserInfoDtoList);
		
		// 用户签章信息
		List<ContractUserSealDto> contractUserSealDtoList = new ArrayList<>();
		ContractUserSealDto contractUserSealDto = new ContractUserSealDto();
		contractUserSealDto.setCertType(7);
		contractUserSealDto.setIdentNo(orderUserBo.getIdentityNo());
		contractUserSealDto.setType(1);
		contractUserSealDto.setUserName(orderUserBo.getUserName());
		contractUserSealDto.setUserType(1);
		contractUserSealDtoList.add(contractUserSealDto);
		loanFMCompositeContractDto.setContractUserSealDtoList(contractUserSealDtoList);
		loanFMCompositeContractDto.setSubType(sceneId);
		return centerClientFactory.getCenterClient(appid).contractFMSafety(loanFMCompositeContractDto);
	}
}
