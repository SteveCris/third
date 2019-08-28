package com.shangyong.thjdq.service;

import com.shangyong.thjdq.dto.AuditResultNotifyDto;
import com.shangyong.thjdq.dto.AuditResultTDCarrierDto;
import com.shangyong.thjdq.entity.UserInfo;

/**
 * 风控，运营商审核回调service
 * Created by ybds on 2019-03-21.
 */
public interface AuditCallbackService {

    /**
     * 风控审核回调
     * @param auditResultNotifyDto
     */
    void riskAuditResultCallback(AuditResultNotifyDto auditResultNotifyDto);

    /**
     * 运营商采集回调
     * @param dto
     */
    void operatorCollectCallback(AuditResultTDCarrierDto dto);

}
