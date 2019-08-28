package com.shangyong.thjdq.service;

import com.shangyong.thjdq.vo.FeedbackOrderStatusVo;

/**
 * 借点钱订单推送service
 * Created by ybds on 2019-03-24.
 */
public interface JdqOrderPushService {

    /**
     * 根据订单状态推送借点钱订单状态
     * @param jdqOrderId
     * @return
     */
    boolean pushOrderStateByStatus(String jdqOrderId);


    /**
     * 根据订单状态获取借点钱订单状态
     * @param status
     * @param repaymentStatus
     * @param jdqOrderId
     * @param localOrderId
     * @return
     */
    FeedbackOrderStatusVo findFeedbackOrderStatusVoByStatus(int status, int repaymentStatus, String jdqOrderId, String localOrderId);

    /**
     * 获取审批贷款信息
     * @param appId
     * @param feedbackOrderStatusVo
     * @return
     */
    FeedbackOrderStatusVo findApprovalLoanInfo(String appId, FeedbackOrderStatusVo feedbackOrderStatusVo);

    /**
     *  获取确认借款贷款信息
     * @param appId
     * @param localOrderId
     * @param feedbackOrderStatusVo
     * @return
     */
    FeedbackOrderStatusVo findLoanInfo(String appId,String localOrderId, FeedbackOrderStatusVo feedbackOrderStatusVo);

    /**
     * 获取用户放款/还款银行卡
     * @param appId
     * @param localOrderId
     * @param feedbackOrderStatusVo
     * @return
     */
    FeedbackOrderStatusVo findBankList(String appId,String localOrderId, FeedbackOrderStatusVo feedbackOrderStatusVo);

    /**
     * 获取还款计划
     * @param status
     * @param repaymentStatus
     * @param appId
     * @param localOrderId
     * @param feedbackOrderStatusVo
     * @return
     */
    FeedbackOrderStatusVo findRepaymentPlan(int status,int repaymentStatus, String appId,String localOrderId, FeedbackOrderStatusVo feedbackOrderStatusVo);

    /**
     * 本地订单状态转换借点钱订单状态
     * @param localOrderStatus
     * @param repaymentStatus
     * @param jdqOrderId
     * @return
     */
    int convertJdqOrderStatus(int localOrderStatus, int repaymentStatus, String jdqOrderId);

}
