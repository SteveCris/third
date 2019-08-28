package com.shangyong.thjdq.service;

import com.shangyong.thjdq.dto.*;

/**
 * 风控推送service
 * Created by ybds on 2019-03-21.
 */
public interface RiskService {

    /**
     * 推送用户信息给风控系统
     * @param riskApplicationDto
     * @param riskUserInfoDto
     * @param riskCompanyInfoDto
     * @param riskContactListDto
     * @param riskFaceInfoDto
     * @param riskAddressBookDto
     * @param operatorData
     * @param userInfoId
     * @param jdqOrderId
     * @return
     */
    boolean pushUserInfoToRisk(RiskApplicationDto riskApplicationDto, RiskUserInfoDto riskUserInfoDto, RiskCompanyInfoDto riskCompanyInfoDto, RiskContactListDto riskContactListDto, RiskFaceInfoDto riskFaceInfoDto, RiskAddressBookDto riskAddressBookDto, String operatorData, String userInfoId, String jdqOrderId);

    /**
     * 推送魔蝎分析报文给风控系统
     * @param resultJson
     * @param appSerialNumber
     * @param taskType
     * @return
     */
    boolean pushOperatorInfoToRisk(String resultJson,  String appSerialNumber, String taskType);
}
