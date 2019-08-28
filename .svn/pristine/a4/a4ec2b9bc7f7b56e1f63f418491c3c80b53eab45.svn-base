package com.shangyong.thjdq.service.impl;

import com.shangyong.backend.dto.AddressBookDto;
import com.shangyong.backend.dto.*;
import com.shangyong.backend.fegin.ApplicationCloudHystrixService;
import com.shangyong.backend.fegin.ReportCloudHystrixService;
import com.shangyong.backend.fegin.UploadCloudHystrixService;
import com.shangyong.common.entity.RestResult;
import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.thjdq.constants.JdqConstant;
import com.shangyong.thjdq.dto.*;
import com.shangyong.thjdq.service.RiskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ybds on 2019-03-21.
 */
@Service
public class RiskServiceImpl implements RiskService {

    private static final Logger log = LoggerFactory.getLogger(RiskServiceImpl.class);

    @Autowired
    private ApplicationCloudHystrixService applicationCloudHystrixService;
    @Autowired
    private UploadCloudHystrixService uploadCloudHystrixService;
    @Autowired
    private ReportCloudHystrixService reportCloudHystrixService;

    @Override
    @SleuthLoggerExclude(excludeOut = false, excludeInput = true)
    public boolean pushUserInfoToRisk(RiskApplicationDto riskApplicationDto, RiskUserInfoDto riskUserInfoDto, RiskCompanyInfoDto riskCompanyInfoDto, RiskContactListDto riskContactListDto, RiskFaceInfoDto riskFaceInfoDto, RiskAddressBookDto riskAddressBookDto, String operatorData, String userInfoId, String jdqOrderId) {

        try {

            // 推送魔蝎报文数据给风控系统
            boolean ifMoxieReportSuccess = pushOperatorInfoToRisk(operatorData, riskApplicationDto.getAppSerialNumber(), JdqConstant.MOXIE_REPORT_TASK_TYPE);

            if (!ifMoxieReportSuccess) {
                log.error("魔蝎报文数据推送给风控系统处理失败 {}", riskApplicationDto.getCustomerId());
                return false;
            }

            // 强行增加一个默认ip
            if (StringUtils.isEmpty(riskApplicationDto.getLoanIp())) {
                riskApplicationDto.setLoanIp("114.114.114.114");
            }

            ApplicationAllDto allParam = new ApplicationAllDto();

            // 上传通讯录
            AddressBookDto addressBookDto = new AddressBookDto();
            if (addressBookDto != null) {
                BeanUtils.copyProperties(riskAddressBookDto, addressBookDto);
                log.debug("applicationBean:{}", addressBookDto);
                RestResult<String> restResult = uploadCloudHystrixService.uploadAddressBook(addressBookDto);
                if (restResult == null || !restResult.isSuccess()) {
                    log.error("上传通讯录失败 {}", restResult);
                    return false;
                }
                log.info("通讯录id{}", restResult.getData().getBody());

                // [上传通讯录] 时 返回 bu_thridpart_report 表中的id
                allParam.setThirdPartAddressBookId(restResult.getData().getBody());
            }

            // 申请单对象
            allParam.setApplicationBean(composeApplicationBean(riskApplicationDto));
            // 平台客户所属公司信息bean
            allParam.setCuCustomerCompany(composeCompany(riskCompanyInfoDto));
            // 平台客户其它账号信息bean
            allParam.setCuCustomerOtherList(new ArrayList<>());
            // 平台客户紧急联系人bean
            allParam.setCuIcePersonList(composeIcePersonList(riskContactListDto));
            // 平台客户信息表bean
            allParam.setCuPlatformCustomer(composePlatformCustomer(riskUserInfoDto));
            // 平台客户客户人脸识别评分记录bean
            allParam.setFaceRecognitionScore(composeFaceRecognitionScore(riskFaceInfoDto));
            // 芝麻 行业信息
            allParam.setIndustryDetailsList(new ArrayList<>());

            log.debug("applicationBean:{}", allParam);
            log.debug("applicationBean:{}", allParam.getApplicationBean());
            log.debug("cuPlatformCustomer:{}", allParam.getCuPlatformCustomer());
            log.debug("cuIcePersonList:{}", allParam.getCuIcePersonList());
            log.debug("cuPlatformCustomer:{}", allParam.getCuPlatformCustomer());
            log.debug("faceRecognitionScore:{}", allParam.getFaceRecognitionScore());

            RestResult<Void> saveResult = applicationCloudHystrixService.saveApplication(allParam);
            log.info("审核单推送风控result:{} {}", saveResult, riskApplicationDto.getCustomerId());
            if (saveResult == null || !saveResult.isSuccess()) {
                log.error("审核单推送风控失败 {}", saveResult);
                // 修改订单为初始状态
                return false;
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            log.error("推送用户信息给风控系统 异常 {}", e);
        }

        return false;
    }

    @Override
    @SleuthLoggerExclude(excludeOut = false, excludeInput = true)
    public boolean pushOperatorInfoToRisk(String resultJson, String appSerialNumber, String taskType) {

        DataReportDto dataReportDto = new DataReportDto();
        dataReportDto.setJsonInfo(resultJson);
        dataReportDto.setAppSerialNumber(appSerialNumber);
        dataReportDto.setTaskType(taskType);
        RestResult<Boolean> restResult = reportCloudHystrixService.operatorInfoUpload(dataReportDto);
        log.info("运营商数据推送风控result{}", restResult);
        if (restResult == null || !restResult.isSuccess() || restResult.getData().getBody() == Boolean.FALSE) {
            log.error("运营商数据推送风控失败 {}", restResult);
            return false;
        }
        return true;
    }

    private FaceRecognitionScoreDto composeFaceRecognitionScore(RiskFaceInfoDto riskFaceInfoDto) {
        FaceRecognitionScoreDto faceRecognitionScoreDto = new FaceRecognitionScoreDto();
        if (riskFaceInfoDto != null) {
            BeanUtils.copyProperties(riskFaceInfoDto, faceRecognitionScoreDto);
        }
        return faceRecognitionScoreDto;
    }

    private CuPlatformCustomerDto composePlatformCustomer(RiskUserInfoDto riskUserInfoDto) {
        CuPlatformCustomerDto cuPlatformCustomerDto = new CuPlatformCustomerDto();
        if (riskUserInfoDto != null) {
            BeanUtils.copyProperties(riskUserInfoDto, cuPlatformCustomerDto);
        }
        return cuPlatformCustomerDto;
    }

    private List<CuIcePersonDto> composeIcePersonList(RiskContactListDto riskContactListDto) {
        List<CuIcePersonDto> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(riskContactListDto.getRiskContactDtoList())) {
            return list;
        }
        List<RiskContactDto> riskContactListDtoList = riskContactListDto.getRiskContactDtoList();
        for (int i = 0, size = riskContactListDtoList.size(); i < size; i++) {
            RiskContactDto riskContactDto = riskContactListDtoList.get(i);
            CuIcePersonDto cuIcePersonDto = new CuIcePersonDto();
            // father	父亲、mother	母亲、spouse	配偶、child	子女、other_relative	其他亲属、friend	朋友、coworker	同事、others	其他**/
            cuIcePersonDto.setType(riskContactDto.getType());
            // 真实姓名
            cuIcePersonDto.setTrueName(riskContactDto.getTrueName());
            // 手机号码
            cuIcePersonDto.setPhoneNum(riskContactDto.getPhoneNum());
            // 备注
            cuIcePersonDto.setRemark(riskContactDto.getRemark());
            list.add(cuIcePersonDto);
        }
        return list;
    }

    private CuCustomerCompanyDto composeCompany(RiskCompanyInfoDto riskCompanyInfoDto) {
        CuCustomerCompanyDto cuCustomerCompanyDto = new CuCustomerCompanyDto();
        if (riskCompanyInfoDto != null) {
            BeanUtils.copyProperties(riskCompanyInfoDto, cuCustomerCompanyDto);
        }
        return cuCustomerCompanyDto;

    }

    private ApplicationDto composeApplicationBean(RiskApplicationDto riskApplicationDto) {
        ApplicationDto applicationDto = new ApplicationDto();
        if (riskApplicationDto != null) {
            BeanUtils.copyProperties(riskApplicationDto, applicationDto);
        }
        return applicationDto;
    }
}
