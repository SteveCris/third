package com.shangyong.thjdq.service;

import com.shangyong.thjdq.entity.UserInfo;
import com.shangyong.thjdq.event.PushRiskEvent;

/**
 * 借点钱风控数据封装service
 * Created by ybds on 2019-03-21.
 */
public interface RiskJdqService {

    /**
     * 借点钱推送用户信息给风控系统
     * @param userInfo
     * @return
     */
    boolean jdqPushUserInfoToRisk(UserInfo userInfo);

    /**
     * 借点钱推送用户信息给风控系统事件
     */
    boolean jdqPushUserInfoToRiskEvent(PushRiskEvent pushRiskEvent);

}
