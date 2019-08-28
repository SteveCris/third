package com.shangyong.thzlqb.service;

import com.shangyong.thzlqb.entity.ZlqbOrderReview;

/**
 * 用户相关服务
 * 
 * @author tongjingji
 * @date 2019年7月19日
 */
public interface ZlqbOrderReviewService {

	/**
	 * 插入订单审核表记录
	 * @param orderReview
	 * @return
	 */
	boolean saveZlqbReviewDto(ZlqbOrderReview orderReview);

	/**
	 * 更新订单表审核记录
	 * @param status
	 * @param orderNo
	 * @return
	 */
	boolean updateOrderReviewDto(int status , String orderNo);


	ZlqbOrderReview getOrderReviewDto(String orderNo);
}
