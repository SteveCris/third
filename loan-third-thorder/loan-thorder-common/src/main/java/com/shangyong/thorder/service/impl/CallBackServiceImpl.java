package com.shangyong.thorder.service.impl;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.thcore.bo.OrderBorrowBo;
import com.shangyong.thcore.event.ActualLoanEvent;
import com.shangyong.thcore.event.RepaymentEvent;
import com.shangyong.thcore.event.dto.EventActualLoan;
import com.shangyong.thcore.event.dto.EventFailureResult;
import com.shangyong.thcore.event.dto.EventHeader;
import com.shangyong.thcore.event.dto.EventRepayment;
import com.shangyong.thorder.contants.UuidPrefix;
import com.shangyong.thorder.dao.OrderBorrowApplyMapper;
import com.shangyong.thorder.dto.LoanCallBackDto;
import com.shangyong.thorder.dto.RepaymentCallBackDto;
import com.shangyong.thorder.enums.CoreResultEnum;
import com.shangyong.thorder.exception.CalfException;
import com.shangyong.thorder.sender.mq.Sender;
import com.shangyong.thorder.service.CallBackService;
import com.shangyong.thorder.utils.CheckUtil;
import com.shangyong.thorder.utils.EventUtil;
import com.shangyong.thorder.utils.IdUtil;

@Service
public class CallBackServiceImpl implements CallBackService {

	private Logger logger = LoggerFactory.getLogger(CallBackServiceImpl.class);

	@Autowired
	private Sender sender;

	@Autowired
	private OrderBorrowApplyMapper orderBorrowApplyMapper;

	@Override
	public boolean processLoanCallBack(String appid, LoanCallBackDto loanCallBackDto) {

		// 获取借款申请单
		OrderBorrowBo orderBorrowBo = orderBorrowApplyMapper.getOrderBorrowBo(loanCallBackDto.getApplicationId());

		if (orderBorrowBo == null) {
			throw new CalfException(CoreResultEnum.NULL_ERROR);
		}

		if (!orderBorrowBo.getAppid().equals(appid)) {
			logger.error("appid不匹配 {}", loanCallBackDto);
			return false;
		}

		if (!loanCallBackDto.getStatement().equals(orderBorrowBo.getFinanceOrderId())) {
			logger.error("financeOrderId不匹配 {}", loanCallBackDto);
			return false;
		}

		boolean isSuccess = "1".equals(loanCallBackDto.getState());
		ActualLoanEvent actualLoanEvent = new ActualLoanEvent();
		EventHeader eventHeader = EventUtil.buildEventHeader(IdUtil.getNumberUuid(UuidPrefix.MESSAGE), appid,
				orderBorrowBo.getOrderId(), "", isSuccess);
		actualLoanEvent.setEventHeader(eventHeader);
		if (isSuccess) {

			EventActualLoan eventActualLoan = new EventActualLoan();
			eventActualLoan.setApplyId(loanCallBackDto.getApplicationId());
			eventActualLoan.setCreditUseUuid(orderBorrowBo.getCreditUseUuid());
			eventActualLoan.setFinanceOrderId(orderBorrowBo.getFinanceOrderId());
			eventActualLoan.setLoanAmount(orderBorrowBo.getBorrowAmount());
			actualLoanEvent.setEventActualLoan(eventActualLoan);

			return sender.sendMq("/third", "ex.event.actualLoan", MessageFormat.format("actualLoan.{0}.rKey", appid),
					actualLoanEvent, true);
		} else {

			EventFailureResult eventFailureResult = new EventFailureResult();
			eventFailureResult.setCode(loanCallBackDto.getState());
			eventFailureResult.setReason(loanCallBackDto.getRemark());
			actualLoanEvent.setEventFailureResult(eventFailureResult);
			return sender.sendMq("/third", "ex.event.actualLoan", MessageFormat.format("actualLoan.{0}.rKey", appid),
					actualLoanEvent, true);
		}

	}

	@Override
	public boolean processRepaymentCallback(String appid, RepaymentCallBackDto repaymentCallBackDto) {
		// 获取借款申请单
		OrderBorrowBo orderBorrowBo = orderBorrowApplyMapper.getOrderBorrowBo(repaymentCallBackDto.getApplicationId());

		if (!orderBorrowBo.getAppid().equals(appid)) {
			logger.error("appid不匹配 {}", repaymentCallBackDto);
			return false;
		}

		if (!repaymentCallBackDto.getStatement().equals(orderBorrowBo.getFinanceOrderId())) {
			logger.error("financeOrderId不匹配 {}", repaymentCallBackDto);
			return false;
		}

		boolean isOverdue = CheckUtil.checkIsOverdue(orderBorrowBo.getRepaymentPlanTime());

		boolean isSuccess = "1".equals(repaymentCallBackDto.getState());

		RepaymentEvent repaymentEvent = new RepaymentEvent();
		EventHeader eventHeader = EventUtil.buildEventHeader(IdUtil.getNumberUuid(UuidPrefix.MESSAGE), appid,
				orderBorrowBo.getOrderId(), "", isSuccess);
		repaymentEvent.setEventHeader(eventHeader);
		if (isSuccess) {
			EventRepayment eventRepayment = new EventRepayment();
			eventRepayment.setApplyId(repaymentCallBackDto.getApplicationId());
			eventRepayment.setCreditUseUuid(orderBorrowBo.getCreditUseUuid());
			eventRepayment.setFinanceOrderId(orderBorrowBo.getFinanceOrderId());
			eventRepayment.setLoanAmount(orderBorrowBo.getBorrowAmount());
			eventRepayment.setPreRepaymentAmount(repaymentCallBackDto.getRepayInsuranceMoney());
			eventRepayment.setRepaymentAmount(repaymentCallBackDto.getRepayLoanMoney());
			eventRepayment.setTotalRepaymentAmount(repaymentCallBackDto.getRepayMoney());
			eventRepayment.setIfOverdue(isOverdue);
			repaymentEvent.setEventRepayment(eventRepayment);
			return sender.sendMq("/third", "ex.event.repayment", MessageFormat.format("repayment.{0}.rKey", appid),
					repaymentEvent, true);
		} else {
			EventFailureResult eventFailureResult = new EventFailureResult();
			eventFailureResult.setCode(repaymentCallBackDto.getState());
			eventFailureResult.setReason(repaymentCallBackDto.getRemark());
			repaymentEvent.setEventFailureResult(eventFailureResult);
			return sender.sendMq("/third", "ex.event.repayment", MessageFormat.format("repayment.{0}.rKey", appid),
					repaymentEvent, true);

		}

	}

}
