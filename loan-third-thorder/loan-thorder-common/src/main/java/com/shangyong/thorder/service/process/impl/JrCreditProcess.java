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
import com.shangyong.center.dto.ContractLoanJRSubjectDto;
import com.shangyong.center.dto.ContractUserInfoDto;
import com.shangyong.center.dto.ContractUserSealDto;
import com.shangyong.center.dto.LoanJRCompositeContractDto;
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

@Service(CreditProcessFactory.JR_NAME_TYPE)
public class JrCreditProcess implements CreditProcess {


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
		return centerClientFactory.getCenterClient(appid).compositeJRApplyBefore(compositeDto);
	}

	@Override
	public CompositeVo applyBehind(String appid, String orderId, CompositeBehindDto compositeBehindDto) {
		return centerClientFactory.getCenterClient(appid).compositeJRApplyBehind(compositeBehindDto);
	}

	@Override
	public ContractApplyVo safety(String appid, String orderId, int index, int sceneId, ProductConfigVo productConfigVo) {

		OrderBankBo orderBankBo = orderBankBindMapper.getOrderBankBo(appid, orderId);
		OrderUserBo orderUserBo = orderUserMapper.getOrderUserBo(appid, orderId);

		LoanJRCompositeContractDto loanJRCompositeContractDto = new LoanJRCompositeContractDto();

		Date loanTime;
		// 前置
		if (index == 0) {
			OrderPreCreditBo orderPreCreditBo = orderPreCreditMapper.getOrderPreCreditBo(appid, orderId);
			loanTime = orderPreCreditBo.getCreateTime();
			loanJRCompositeContractDto.setCreditUuid(orderPreCreditBo.getCreditUuid());
		}
		// 后置
		else {
			OrderCreditBo orderCreditBo = oderCreditMapper.getOrderCreditBo(appid, orderId);
			loanTime = orderCreditBo.getCreateTime();
			loanJRCompositeContractDto.setCreditUseUuid(orderCreditBo.getCreditUseUuid());

		}

		// 合同主体信息
		ContractLoanJRSubjectDto contractLoanJRSubjectDto = new ContractLoanJRSubjectDto();
		contractLoanJRSubjectDto.setApplicationName(productConfigVo.getApplicationName());
		contractLoanJRSubjectDto.setBorrowerCertNo(orderBankBo.getIdentityNo());
		contractLoanJRSubjectDto.setBorrowerEmail("");
		contractLoanJRSubjectDto.setBorrowerName(orderBankBo.getUserName());
		contractLoanJRSubjectDto.setBorrowerTell(orderUserBo.getMobile());

		// 签订时间-开始时间
		LocalDateTime localLoanTime = LocalDateUtil.parse(loanTime);
		LocalDateTime localLoanEndTime = localLoanTime.plus(productConfigVo.getCycle(), ChronoUnit.DAYS);

		// 签订时间
		contractLoanJRSubjectDto.setLoanTimeYear(localLoanTime.getYear());
		contractLoanJRSubjectDto.setLoanTimeMonth(localLoanTime.getMonthValue());
		contractLoanJRSubjectDto.setLoanTimeDate(localLoanTime.getDayOfMonth());
		
		// 开始时间
		contractLoanJRSubjectDto.setLoanStartTimeYear(localLoanTime.getYear());
		contractLoanJRSubjectDto.setLoanStartTimeMonth(localLoanTime.getMonthValue());
		contractLoanJRSubjectDto.setLoanStartTimeDate(localLoanTime.getDayOfMonth());

		// 截止时间
		contractLoanJRSubjectDto.setLoanEndTimeYear(localLoanEndTime.getYear());
		contractLoanJRSubjectDto.setLoanEndTimeMonth(localLoanEndTime.getMonthValue());
		contractLoanJRSubjectDto.setLoanEndTimeDate(localLoanEndTime.getDayOfMonth());

		contractLoanJRSubjectDto.setResidentialAddress(orderUserBo.getAddress());

		loanJRCompositeContractDto.setContractLoanJRSubjectDto(contractLoanJRSubjectDto);
		// 用户主体信息
		List<ContractUserInfoDto> contractUserInfoDtoList = new ArrayList<>();
		ContractUserInfoDto contractUserInfoDto = new ContractUserInfoDto();
		contractUserInfoDto.setBankNo(orderBankBo.getBankCardNo());
		contractUserInfoDto.setEmail("");
		contractUserInfoDto.setIdCard(orderUserBo.getIdentityNo());
		contractUserInfoDto.setMobile(orderUserBo.getMobile());
		contractUserInfoDto.setUserName(orderUserBo.getUserName());
		contractUserInfoDtoList.add(contractUserInfoDto);
		loanJRCompositeContractDto.setContractUserInfoDtoList(contractUserInfoDtoList);

		// 用户签章信息
		List<ContractUserSealDto> contractUserSealDtoList = new ArrayList<>();
		ContractUserSealDto contractUserSealDto = new ContractUserSealDto();
		contractUserSealDto.setCertType(7);
		contractUserSealDto.setIdentNo(orderUserBo.getIdentityNo());
		contractUserSealDto.setType(1);
		contractUserSealDto.setUserName(orderUserBo.getUserName());
		contractUserSealDto.setUserType(1);
		contractUserSealDtoList.add(contractUserSealDto);
		loanJRCompositeContractDto.setContractUserSealDtoList(contractUserSealDtoList);
		loanJRCompositeContractDto.setSubType(sceneId);
		return centerClientFactory.getCenterClient(appid).contractJRSafety(loanJRCompositeContractDto);
	}

}
