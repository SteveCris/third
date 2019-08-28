package com.shangyong.thorder.service.process.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.center.client.CenterClientFactory;
import com.shangyong.center.dto.CompositeDto;
import com.shangyong.center.dto.ContractLoanSubjectDto;
import com.shangyong.center.dto.ContractUserInfoDto;
import com.shangyong.center.dto.ContractUserSealDto;
import com.shangyong.center.dto.LoanCompositeContractDto;
import com.shangyong.center.vo.CompositeVo;
import com.shangyong.center.vo.ContractApplyVo;
import com.shangyong.common.utils.LocalDateUtil;
import com.shangyong.thcore.bo.OrderBankBo;
import com.shangyong.thcore.bo.OrderCreditBo;
import com.shangyong.thcore.bo.OrderUserBo;
import com.shangyong.thcore.vo.ProductConfigVo;
import com.shangyong.thorder.dao.OrderBankBindMapper;
import com.shangyong.thorder.dao.OrderCreditMapper;
import com.shangyong.thorder.dao.OrderUserMapper;
import com.shangyong.thorder.service.process.CompositeCreditProcess;

@Service
public class CompositeCreditProcessImpl implements CompositeCreditProcess {

	@Autowired
	private CenterClientFactory centerClientFactory;

	@Autowired
	private OrderBankBindMapper orderBankBindMapper;

	@Autowired
	private OrderUserMapper orderUserMapper;

	@Autowired
	private OrderCreditMapper oderCreditMapper;

	@Override
	public CompositeVo apply(String appid, String orderId, CompositeDto compositeDto) {
		return centerClientFactory.getCenterClient(appid).compositeApply(compositeDto);

	}

	@Override
	public ContractApplyVo safety(String appid, String orderId, int type, int sceneId,
			ProductConfigVo productConfigVo) {
		OrderBankBo orderBankBo = orderBankBindMapper.getOrderBankBo(appid, orderId);
		OrderUserBo orderUserBo = orderUserMapper.getOrderUserBo(appid, orderId);

		LoanCompositeContractDto loanCompositeContractDto = new LoanCompositeContractDto();

		OrderCreditBo orderCreditBo = oderCreditMapper.getOrderCreditBo(appid, orderId);
		Date loanTime = orderCreditBo.getCreateTime();
		loanCompositeContractDto.setCreditUseUuid(orderCreditBo.getCreditUseUuid());

		// 合同主体信息
		ContractLoanSubjectDto contractLoanSubjectDto = new ContractLoanSubjectDto();
		contractLoanSubjectDto.setLendAmount(orderCreditBo.getCreditAmount().toString());
		contractLoanSubjectDto.setApplicationName(productConfigVo.getApplicationName());
		contractLoanSubjectDto.setBorroweraccountNumber(orderBankBo.getBankCardNo());
		contractLoanSubjectDto.setBorrowerCertNo(orderBankBo.getIdentityNo());
		contractLoanSubjectDto.setBorrowerName(orderBankBo.getUserName());
		contractLoanSubjectDto.setBorroweropeningInstitution(orderBankBo.getBankName());
		contractLoanSubjectDto.setBorrowerTell(orderUserBo.getMobile());

		// 签订时间-开始时间
		LocalDateTime localLoanTime = LocalDateUtil.parse(loanTime);
		LocalDateTime localLoanEndTime = localLoanTime.plus(productConfigVo.getCycle(), ChronoUnit.DAYS);

		// 开始时间
		contractLoanSubjectDto.setLoanStartTimeYear(localLoanTime.getYear());
		contractLoanSubjectDto.setLoanStartTimeMonth(localLoanTime.getMonthValue());
		contractLoanSubjectDto.setLoanStartTimeDate(localLoanTime.getDayOfMonth());

		// 截止时间
		contractLoanSubjectDto.setLoanEndTimeYear(localLoanEndTime.getYear());
		contractLoanSubjectDto.setLoanEndTimeMonth(localLoanEndTime.getMonthValue());
		contractLoanSubjectDto.setLoanEndTimeDate(localLoanEndTime.getDayOfMonth());

		contractLoanSubjectDto.setResidentialAddress(orderUserBo.getAddress());

		loanCompositeContractDto.setContractLoanSubjectDto(contractLoanSubjectDto);

		// 用户主体信息
		List<ContractUserInfoDto> contractUserInfoDtoList = new ArrayList<>();
		ContractUserInfoDto contractUserInfoDto = new ContractUserInfoDto();
		contractUserInfoDto.setBankNo(orderBankBo.getBankCardNo());
		contractUserInfoDto.setEmail("");
		contractUserInfoDto.setIdCard(orderUserBo.getIdentityNo());
		contractUserInfoDto.setMobile(orderUserBo.getMobile());
		contractUserInfoDto.setUserName(orderUserBo.getUserName());
		contractUserInfoDtoList.add(contractUserInfoDto);
		loanCompositeContractDto.setContractUserInfoDtoList(contractUserInfoDtoList);

		// 用户签章信息
		List<ContractUserSealDto> contractUserSealDtoList = new ArrayList<>();
		ContractUserSealDto contractUserSealDto = new ContractUserSealDto();
		contractUserSealDto.setCertType(7);
		contractUserSealDto.setIdentNo(orderUserBo.getIdentityNo());
		contractUserSealDto.setType(1);
		contractUserSealDto.setUserName(orderUserBo.getUserName());
		contractUserSealDto.setUserType(1);
		contractUserSealDtoList.add(contractUserSealDto);
		loanCompositeContractDto.setContractUserSealDtoList(contractUserSealDtoList);
		loanCompositeContractDto.setSubType(sceneId);
		loanCompositeContractDto.setType(type);
		return centerClientFactory.getCenterClient(appid).contractSafety(loanCompositeContractDto);
	}

}
