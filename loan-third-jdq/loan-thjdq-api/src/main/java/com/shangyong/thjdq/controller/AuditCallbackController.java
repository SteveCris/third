package com.shangyong.thjdq.controller;

import com.shangyong.common.entity.RestResult;
import com.shangyong.thjdq.constants.JdqConstant;
import com.shangyong.thjdq.dto.AuditResultNotifyDto;
import com.shangyong.thjdq.enums.CoreResultEnum;
import com.shangyong.thjdq.service.AuditCallbackService;
import com.shangyong.thjdq.util.SignUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 风控，运营商审核回调
 * Created by ybds on 2019-03-21.
 */
@Api(tags = "zbb-郑斌斌-风控，运营商审核回调处理")
@RestController
public class AuditCallbackController {

    private static final Logger log = LoggerFactory.getLogger(AuditCallbackController.class);

    @Autowired
    private AuditCallbackService auditCallbackService;

    @ApiOperation(value = "风控回调审核结果")
    @PostMapping(value = "/risk/riskAuditResultCallBack")
    public RestResult<Void> riskAuditResultCallBack(AuditResultNotifyDto auditResultNotifyDto) {
        log.info("风控回调审核结果 {}", auditResultNotifyDto);
        if (!checkSign(auditResultNotifyDto, auditResultNotifyDto.getSign())) {
            return CoreResultEnum.ERROR.<Void>with().withMessage("签名错误");
        }
        auditCallbackService.riskAuditResultCallback(auditResultNotifyDto);
        return CoreResultEnum.SUCCESS.with();
    }

    private boolean checkSign(Object bean, String sign) {
        Map<String, String> describe = null;
        try {
            describe = BeanUtils.describe(bean);
            describe.remove("class");
        } catch (Exception e) {
            log.error("checkSign bean 转map异常", e);
            return false;
        }
        return SignUtil.checkSign(describe, sign, JdqConstant.MERCHANT_KEY);
    }

}
