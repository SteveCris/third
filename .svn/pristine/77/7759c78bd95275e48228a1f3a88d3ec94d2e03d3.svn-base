package com.shangyong.thjdq.service;

import com.shangyong.thjdq.dto.InvokeJdqRequestDto;
import com.shangyong.thjdq.dto.JdqInvokeChannelDto;
import com.shangyong.thjdq.enums.JdqPushEnum;
import com.shangyong.thjdq.vo.FeedbackOrderStatusVo;
import com.shangyong.thjdq.vo.Response;

/**
 * 借点钱 签名service
 * Created by ybds on 2019-03-18.
 */
public interface JdqSignService {

    /**
     * 借点钱调用机构方法
     * @return
     */
    boolean channelParseJdqInvokeRequest(JdqInvokeChannelDto request);

    /**
     * 构建请求借点钱机构参数
     * @param jdqPushEnum
     * @param data
     * @return
     */
    InvokeJdqRequestDto channelBuildInvokeJdqRequest(JdqPushEnum jdqPushEnum, Object data);

    /**
     * 请求借点钱接口
     */
    Response sendJdqRequest(InvokeJdqRequestDto invokeJdqRequestDto);

}
