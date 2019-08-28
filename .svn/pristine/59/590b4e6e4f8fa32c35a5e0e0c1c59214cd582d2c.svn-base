package com.shangyong.thorder.service.impl;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shangyong.common.entity.RestResult;
import com.shangyong.common.utils.LocalDateUtil;
import com.shangyong.rest.feign.BaseCloudHystrixService;
import com.shangyong.thcore.bo.OrderBorrowBo;
import com.shangyong.thcore.bo.OrderLoanBo;
import com.shangyong.thcore.event.CancelEvent;
import com.shangyong.thcore.event.OverdueEvent;
import com.shangyong.thcore.vo.ProductConfigVo;
import com.shangyong.thorder.bo.OrderAuditExpireBo;
import com.shangyong.thorder.dao.TaskMapper;
import com.shangyong.thorder.sender.mq.Sender;
import com.shangyong.thorder.service.TaskService;
import com.shangyong.thorder.utils.EventUtil;

@Service
public class TaskServiceImpl implements TaskService {

	private Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Autowired
	private TaskMapper taskMapper;

	@Autowired
	private Sender sender;

	@Autowired
	private BaseCloudHystrixService baseCloudHystrixService;

	private ConcurrentHashMap<String, ProductConfigVo> map = new ConcurrentHashMap<>();

	@Override
	public boolean processOverdue(String beforeDateStr, String afterDateStr) {

		Date beforeTime;
		Date afterTime;
		if (beforeDateStr == null) {
			beforeTime = null;
			afterTime = null;
		} else {
			beforeTime = LocalDateUtil.parseToDate(beforeDateStr + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			afterTime = LocalDateUtil.parseToDate(afterDateStr + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
		}

		int start = 0;
		int size = 100;

		cycleProcess(beforeTime, afterTime, start, size);

		return true;
	}

	@Override
	public boolean processAuditExpire(String beforeDateStr, String afterDateStr) {

		Date beforeTime;
		Date afterTime;
		if (beforeDateStr == null) {
			beforeTime = null;
			afterTime = null;
		} else {
			beforeTime = LocalDateUtil.parseToDate(beforeDateStr + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			afterTime = LocalDateUtil.parseToDate(afterDateStr + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
		}

		int start = 0;
		int size = 100;

		cycleProcessAuditExpire(beforeTime, afterTime, start, size);

		return true;
	}

	private void cycleProcessAuditExpire(Date beforeTime, Date afterTime, int start, int size) {
		int compareSize = 0;
		do {
			PageHelper.offsetPage(start, size);
			List<OrderAuditExpireBo> orderAuditExpireBoList = taskMapper.listOrderLoanBoNoBorrow(beforeTime, afterTime);

			for (OrderAuditExpireBo orderAuditExpireBo : orderAuditExpireBoList) {
				start++;
				ProductConfigVo productConfigVo = getProduct(orderAuditExpireBo.getAppid());
				if (productConfigVo == null) {
					break;
				}
				
				if(orderAuditExpireBo.getCreateTime()==null) {
					break;
				}

				// 当前日期
				LocalDate localDateNow = LocalDateUtil.parse(new Date()).toLocalDate();
				// 审核日期
				LocalDate localDateAudit = LocalDateUtil.parse(LocalDateUtil.plus(orderAuditExpireBo.getCreateTime(),
						Integer.valueOf(productConfigVo.getExt2()), ChronoUnit.DAYS)).toLocalDate();

				// 如果日期大于审核期限往后15天
				if (localDateNow.isAfter(localDateAudit)) {
					CancelEvent cancelEvent = new CancelEvent();
					cancelEvent.setEventHeader(EventUtil.buildEventHeader(EventUtil.getMessageId(),
							orderAuditExpireBo.getAppid(), orderAuditExpireBo.getOrderId(), "", true, "定时任务执行"));

					sender.sendMq("/third", "ex.event.cancel",
							MessageFormat.format("cancel.{0}.rKey", orderAuditExpireBo.getAppid()), cancelEvent, true);
					logger.info("第{}个单子处理，单号是{}，审核信息过期", start, orderAuditExpireBo.getOrderId());
				} else {
					logger.info("第{}个单子处理，单号是{}，审核信息正常", start, orderAuditExpireBo.getOrderId());
				}
			}
			compareSize = orderAuditExpireBoList.size();
		} while (size == compareSize);
	}

	private ProductConfigVo getProduct(String appid) {
		ProductConfigVo productConfigVo = map.get(appid);
		if (productConfigVo == null) {
			RestResult<ProductConfigVo> result = baseCloudHystrixService.getProductConfigVo(appid);
			if (result != null && result.isSuccess()) {
				productConfigVo = result.getData().getBody();
				map.put(appid, productConfigVo);
			}
		}
		return productConfigVo;
	}

	private void cycleProcess(Date beforeTime, Date afterTime, int start, int size) {

		int compareSize = 0;
		do {
			PageHelper.offsetPage(start, size);
			List<OrderLoanBo> orderLoanBoList = taskMapper.listOrderLoanBo(beforeTime, afterTime);

			for (OrderLoanBo orderLoanBo : orderLoanBoList) {
				start++;

				OrderBorrowBo orderBorrowBo = taskMapper.getOrderBorrowBo(orderLoanBo.getAppid(),
						orderLoanBo.getOrderId());
				// 当前日期
				LocalDate localDateNow = LocalDateUtil.parse(new Date()).toLocalDate();
				// 借款日期
				LocalDate localDateBorrow = LocalDateUtil.parse(orderBorrowBo.getRepaymentPlanTime()).toLocalDate();

				// 如果日期大于最后还款日期 视为逾期
				if (localDateNow.isAfter(localDateBorrow)) {
					OverdueEvent overdueEvent = new OverdueEvent();
					overdueEvent.setEventHeader(EventUtil.buildEventHeader(EventUtil.getMessageId(),
							orderLoanBo.getAppid(), orderLoanBo.getOrderId(), "", true));
					sender.sendMq("/third", "ex.event.overdue",
							MessageFormat.format("overdue.{0}.rKey", orderLoanBo.getAppid()), overdueEvent, true);
					logger.info("第{}个单子处理，单号是{}，已逾期", start, orderLoanBo.getOrderId());
				} else {
					logger.info("第{}个单子处理，单号是{}，正常", start, orderLoanBo.getOrderId());
				}
			}
			compareSize = orderLoanBoList.size();
		} while (size == compareSize);

	}

}
