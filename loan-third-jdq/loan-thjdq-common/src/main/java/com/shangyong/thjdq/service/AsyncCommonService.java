package com.shangyong.thjdq.service;

import com.shangyong.thjdq.event.PushPhaseOneEvent;
import com.shangyong.thjdq.event.PushPhaseTwoEvent;

/**
 * 异步通用service
 * Created by zhengbb on 2019-05-24.
 */
public interface AsyncCommonService {

    /**
     * 保存一推基本信息
     * @param pushPhaseOneEvent
     */
    void pushPhaseOneDataAsync(PushPhaseOneEvent pushPhaseOneEvent);

    /**
     * 保存二推基本信息
     * @return
     */
    void pushPhaseTwoDataAsync(PushPhaseTwoEvent pushPhaseTwoEvent);
}
