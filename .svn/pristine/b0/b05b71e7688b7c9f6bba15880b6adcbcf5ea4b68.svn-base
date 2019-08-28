package com.shangyong.thjdq.service.impl;

import com.shangyong.thjdq.event.PushPhaseOneEvent;
import com.shangyong.thjdq.event.PushPhaseTwoEvent;
import com.shangyong.thjdq.service.AsyncCommonService;
import com.shangyong.thjdq.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by zhengbb on 2019-05-24.
 */
@Service
public class AsyncCommonServiceImpl implements AsyncCommonService {

    private static final Logger log = LoggerFactory.getLogger(AsyncCommonServiceImpl.class);
    @Autowired
    private UserInfoService userInfoService;

    @Override
    @Async
    public void pushPhaseOneDataAsync(PushPhaseOneEvent pushPhaseOneEvent) {
        try {
            userInfoService.pushPhaseOneDataAsync(pushPhaseOneEvent);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("异步保存一推基本信息异常", e);
        }

    }

    @Override
    @Async
    public void pushPhaseTwoDataAsync(PushPhaseTwoEvent pushPhaseTwoEvent) {
        try {
            userInfoService.pushPhaseTwoDataAsync(pushPhaseTwoEvent);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("异步保存二推补充信息异常", e);
        }
    }
}
