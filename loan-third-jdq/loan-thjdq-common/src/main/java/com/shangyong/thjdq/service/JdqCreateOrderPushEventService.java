package com.shangyong.thjdq.service;

/**
 * Created by ybds on 2019-03-24.
 */
public interface JdqCreateOrderPushEventService {

    /**
     * 根据借点钱订单状态发起订单状态推送事件
     *
     * @param jdqOrderId
     * @param userInfoId
     * @param localOrderId
     * @return
     */
    boolean createOrderPushEvent(String jdqOrderId, String userInfoId, String localOrderId);

    /**
     * 根据借点钱订单状态发起订单状态推送事件
     *
     * @param jdqOrderId
     * @param userInfoId
     * @param localOrderId
     * @param ifOrderFinish
     * @return
     */
    boolean createOrderPushEvent(String jdqOrderId, String userInfoId, String localOrderId, boolean ifOrderFinish);

    /**
     * 根据订单号删除完结订单相关redisKey
     *
     * @param idNumber
     * @return
     */
    boolean deleteOrderFinishRedisKeyByIdNumber(String idNumber);
}
