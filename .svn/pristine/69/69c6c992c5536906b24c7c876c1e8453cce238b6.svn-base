package com.shangyong.thorder.service.process.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.center.client.CenterClientFactory;
import com.shangyong.center.dto.CompositeBehindDto;
import com.shangyong.center.dto.CompositeDto;
import com.shangyong.center.dto.ContractLoanXHSubjectDto;
import com.shangyong.center.dto.ContractUserInfoDto;
import com.shangyong.center.dto.ContractUserSealDto;
import com.shangyong.center.dto.LoanXHCompositeContractDto;
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

@Service(CreditProcessFactory.XH_NAME_TYPE)
public class XhCreditProcess implements CreditProcess {

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
		return centerClientFactory.getCenterClient(appid).compositeXHApplyBefore(compositeDto);
	}

	@Override
	public CompositeVo applyBehind(String appid, String orderId, CompositeBehindDto compositeBehindDto) {
		return centerClientFactory.getCenterClient(appid).compositeXHApplyBehind(compositeBehindDto);
	}

	@Override
	public ContractApplyVo safety(String appid, String orderId, int index, int sceneId, ProductConfigVo productConfigVo) {
		OrderBankBo orderBankBo = orderBankBindMapper.getOrderBankBo(appid, orderId);
		OrderUserBo orderUserBo = orderUserMapper.getOrderUserBo(appid, orderId);

		LoanXHCompositeContractDto loanxhCompositeContractDto = new LoanXHCompositeContractDto();

		Date loanTime;
		// 前置
		if (index == 0) {
			OrderPreCreditBo orderPreCreditBo = orderPreCreditMapper.getOrderPreCreditBo(appid, orderId);
			loanTime = orderPreCreditBo.getCreateTime();
			loanxhCompositeContractDto.setCreditUuid(orderPreCreditBo.getCreditUuid());
		}
		// 后置
		else {
			OrderCreditBo orderCreditBo = oderCreditMapper.getOrderCreditBo(appid, orderId);
			loanTime = orderCreditBo.getCreateTime();
			loanxhCompositeContractDto.setCreditUseUuid(orderCreditBo.getCreditUseUuid());

		}

		// 合同主体信息
		ContractLoanXHSubjectDto contractLoanXHSubjectDto = new ContractLoanXHSubjectDto();
		contractLoanXHSubjectDto.setApplicationName(productConfigVo.getApplicationName());
		contractLoanXHSubjectDto.setBorroweraccountNumber(orderBankBo.getBankCardNo());
		contractLoanXHSubjectDto.setBorrowerCertNo(orderBankBo.getIdentityNo());
		contractLoanXHSubjectDto.setBorrowerName(orderBankBo.getUserName());
		contractLoanXHSubjectDto.setBorroweropeningInstitution(orderBankBo.getBankName());
		contractLoanXHSubjectDto.setBorrowerTell(orderUserBo.getMobile());

		// 签订时间-开始时间
		LocalDateTime localLoanTime = LocalDateUtil.parse(loanTime);
		LocalDateTime localLoanEndTime = localLoanTime.plus(productConfigVo.getCycle(), ChronoUnit.DAYS);
	
		// 签订时间时间
		contractLoanXHSubjectDto.setLoanTimeYear(localLoanTime.getYear());
		contractLoanXHSubjectDto.setLoanTimeMonth(localLoanTime.getMonthValue());
		contractLoanXHSubjectDto.setLoanTimeDate(localLoanTime.getDayOfMonth());

		// 开始时间
		contractLoanXHSubjectDto.setLoanStartTimeYear(localLoanTime.getYear());
		contractLoanXHSubjectDto.setLoanStartTimeMonth(localLoanTime.getMonthValue());
		contractLoanXHSubjectDto.setLoanStartTimeDate(localLoanTime.getDayOfMonth());
		
		// 截止时间
		contractLoanXHSubjectDto.setLoanEndTimeYear(localLoanEndTime.getYear());
		contractLoanXHSubjectDto.setLoanEndTimeMonth(localLoanEndTime.getMonthValue());
		contractLoanXHSubjectDto.setLoanEndTimeDate(localLoanEndTime.getDayOfMonth());

		contractLoanXHSubjectDto.setResidentialAddress(orderUserBo.getAddress());

		loanxhCompositeContractDto.setContractLoanXHSubjectDto(contractLoanXHSubjectDto);
		// 用户主体信息
		List<ContractUserInfoDto> contractUserInfoDtoList = new ArrayList<>();
		ContractUserInfoDto contractUserInfoDto = new ContractUserInfoDto();
		contractUserInfoDto.setBankNo(orderBankBo.getBankCardNo());
		contractUserInfoDto.setEmail("");
		contractUserInfoDto.setIdCard(orderUserBo.getIdentityNo());
		contractUserInfoDto.setMobile(orderUserBo.getMobile());
		contractUserInfoDto.setUserName(orderUserBo.getUserName());
		contractUserInfoDtoList.add(contractUserInfoDto);
		loanxhCompositeContractDto.setContractUserInfoDtoList(contractUserInfoDtoList);

		// 用户签章信息
		List<ContractUserSealDto> contractUserSealDtoList = new ArrayList<>();
		ContractUserSealDto contractUserSealDto = new ContractUserSealDto();
		contractUserSealDto.setCertType(7);
		contractUserSealDto.setIdentNo(orderUserBo.getIdentityNo());
		contractUserSealDto.setType(1);
		contractUserSealDto.setUserName(orderUserBo.getUserName());
		contractUserSealDto.setUserType(1);
		contractUserSealDtoList.add(contractUserSealDto);
		loanxhCompositeContractDto.setContractUserSealDtoList(contractUserSealDtoList);
		loanxhCompositeContractDto.setSubType(sceneId);
		return centerClientFactory.getCenterClient(appid).contractXHSafety(loanxhCompositeContractDto);

		
		
	
	}

}
