package com.shangyong.thzlqb.utils;

import com.shangyong.loan.ext.exception.CalfException;
import com.shangyong.thcore.vo.OrderLoanVo;

public class OrderUtil {

	private OrderUtil() {

	}

	/**
	 * 是否待前置授信
	 * 
	 * @param orderLoanVo
	 * @return
	 */
	public static boolean isWaitPreCredit(OrderLoanVo orderLoanVo) {
		return 60 == orderLoanVo.getStatus();
	}

	/**
	 * 是否待后置授信
	 * 
	 * @param orderLoanVo
	 * @return
	 */
	public static boolean isWaitCredit(OrderLoanVo orderLoanVo) {
		return 70 == orderLoanVo.getStatus();
	}

	/**
	 * 是否处于待还款状态
	 * 
	 * @param orderLoanVo
	 * @return
	 */
	public static boolean isWaitRepayment(OrderLoanVo orderLoanVo) {
		return 110 == orderLoanVo.getStatus();
	}

	/**
	 * 是否绑卡成功
	 * 
	 * @param orderLoanVo
	 * @return
	 */
	public static boolean isBindSuccess(OrderLoanVo orderLoanVo) {
		boolean flag;
		switch (orderLoanVo.getStatus()) {
		case 60:
		case 70:
		case 80:
		case 90:
		case 100:
		case 110:
		case 120:
		case 130:
		case 140:
			flag = true;
			break;
		default:
			flag = false;
			break;
		}
		return flag;
	}

	/**
	 * 是否是 审核通过状态
	 * 
	 * @param orderLoanVo
	 */
	public static boolean isAuditSuccess(OrderLoanVo orderLoanVo) {
		boolean flag;
		switch (orderLoanVo.getStatus()) {
		case 40:
		case 50:
		case 60:
		case 70:
		case 80:
		case 90:
		case 100:
		case 110:
		case 120:
		case 130:
		case 140:
			flag = true;
			break;
		default:
			flag = false;
			break;
		}
		return flag;
	}

	/**
	 * 是否是 放款通过状态
	 * 
	 * @param orderLoanVo
	 * @return
	 */
	public static boolean isLoanSuccess(OrderLoanVo orderLoanVo) {
		boolean flag;
		switch (orderLoanVo.getStatus()) {
		case 110:
		case 120:
		case 130:
		case 140:
			flag = true;
			break;
		default:
			flag = false;
			break;
		}
		return flag;
	}

	/**
	 * 判断是不是还款成功
	 * 
	 * @param orderLoanVo
	 * @return
	 */
	public static boolean isRepaymentSuccess(OrderLoanVo orderLoanVo) {
		return orderLoanVo.getStatus() == 130;
	}

	/**
	 * 获得助力钱包状态码
	 * 
	 * @param orderLoanVo
	 * @param isContract
	 *            是否进行过签约操作
	 * @return
	 */
	public static int getZlqbOrderStatus(OrderLoanVo orderLoanVo, boolean isContract) {
		int status = orderLoanVo.getStatus();
		int zlqbStatus;
		switch (status) {
		case 0:
		case 10:
			zlqbStatus = 401;
			break;
		case 20:
			zlqbStatus = 402;
			break;
		case 30:
			zlqbStatus = 999;
			break;
		case 40:
		case 50:
			zlqbStatus = 403;
			break;
		case 60:
			// 可能有绑卡成功、待开户两种情况
			// 是否进行过签约操作，如果有，那么就是待开户状态反之绑卡成功
			if (isContract) {
				zlqbStatus = 704;
			} else {
				zlqbStatus = 404;
			}
			break;
		case 70:
			zlqbStatus = 704;
			break;
		case 80:
		case 90:
			zlqbStatus = 701;
			break;
		case 100:
			zlqbStatus = 408;
			break;
		case 110:
		case 120:
			if (orderLoanVo.getRepaymentStatus() == 30) {
				zlqbStatus = 213;
			} else {
				zlqbStatus = 509;
			}
			break;
		case 130:
			zlqbStatus = 201;
			break;
		case 140:
			zlqbStatus = 213;
			break;
		case 1000:
			zlqbStatus = 702;
			break;
		default:
			throw new CalfException("不存在的订单状态");
		}
		return zlqbStatus;
	}

}
