package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * 审核中，风控告知需要用户运营商需要重新采集，步骤号为201
 *
 * @author huangyufa <br>
 * 2019年01月11日 <br>
 */
public class AuditResultTDCarrierDto implements Serializable {

    private static final long serialVersionUID = -3775562766404957532L;

    /**
     * 平台标识
     */
    private String appid;

    /**
     * 签名
     */
    private String sign;

    /**
     * 用户id
     */
    private String customerId;

    /**
     * 申请单号ID
     */
    private String applicationId;

    /**
     * 审核单申请流水号
     */
    private String appSerialNumber;

    /**
     * 平台标识
     */
    private String platform;

    /**
     * 风控步骤编号
     */
    private String riskStepNo;

    /**
     * 备注
     */
    private String remark;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getAppSerialNumber() {
        return appSerialNumber;
    }

    public void setAppSerialNumber(String appSerialNumber) {
        this.appSerialNumber = appSerialNumber;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRiskStepNo() {
        return riskStepNo;
    }

    public void setRiskStepNo(String riskStepNo) {
        this.riskStepNo = riskStepNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuditResultTDCarrierDto [appid=");
        builder.append(appid);
        builder.append(", sign=");
        builder.append(sign);
        builder.append(", customerId=");
        builder.append(customerId);
        builder.append(", applicationId=");
        builder.append(applicationId);
        builder.append(", appSerialNumber=");
        builder.append(appSerialNumber);
        builder.append(", platform=");
        builder.append(platform);
        builder.append(", riskStepNo=");
        builder.append(riskStepNo);
        builder.append(", remark=");
        builder.append(remark);
        builder.append("]");
        return builder.toString();
    }

}
