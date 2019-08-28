package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * Created by ybds on 2019-03-19.
 */
public class RiskApplicationDto implements Serializable {

    private static final long serialVersionUID = -852342610925928140L;

    /**APP应用请求流水号**/
    private String appSerialNumber;

    /**
     * APP名称：
     * @see  com.shangyong.backend.common.enums.AppNameEnum
     */
    private Integer appName;

    /**
     * app 产品标识
     */
    private String appId;

    /**APP应用客户编号**/
    private String customerId;

    /**客户姓名**/
    private String name;

    /**证件类型（1.身份证 2.护照 3.其他）**/
    private String certType;

    /**证件号码**/
    private String certCode;

    /**手机号**/
    private String phoneNum;

    /**产品额度**/
    private String productQuota;

    /**周期**/
    private Integer days;

    /**借款用户公网IP**/
    private String loanIp;

    /**申请来源（0——Android；1——IOS）**/
    private String source;

    /**同盾black_box**/
    private String tdBlackBox;

    /**聚信立用户token**/
    private String jxlUseToken;

    /**芝麻用户标识：芝麻会员在商户端的身份标识**/
    private String zhiMaOpenId;

    /**客户标识(0:新客户 1：老客户)**/
    private String appLevel;

    /**app渠道标识**/
    private String appChannel;

    /**APP版本号**/
    private String appVersion;

    /** 芝麻分数 **/
    private String zhiMaScore;

    private String mobileWebsite;

    /**
     * 申请单审核结果 回调地址
     */
    private String callBackUrlAuditStatus;

    /**
     * 信息重新采集 回调地址
     */
    private String callBackUrlInfoRecollect;

    public String getAppSerialNumber() {
        return appSerialNumber;
    }

    public void setAppSerialNumber(String appSerialNumber) {
        this.appSerialNumber = appSerialNumber;
    }

    public Integer getAppName() {
        return appName;
    }

    public void setAppName(Integer appName) {
        this.appName = appName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getProductQuota() {
        return productQuota;
    }

    public void setProductQuota(String productQuota) {
        this.productQuota = productQuota;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getLoanIp() {
        return loanIp;
    }

    public void setLoanIp(String loanIp) {
        this.loanIp = loanIp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTdBlackBox() {
        return tdBlackBox;
    }

    public void setTdBlackBox(String tdBlackBox) {
        this.tdBlackBox = tdBlackBox;
    }

    public String getJxlUseToken() {
        return jxlUseToken;
    }

    public void setJxlUseToken(String jxlUseToken) {
        this.jxlUseToken = jxlUseToken;
    }

    public String getZhiMaOpenId() {
        return zhiMaOpenId;
    }

    public void setZhiMaOpenId(String zhiMaOpenId) {
        this.zhiMaOpenId = zhiMaOpenId;
    }

    public String getAppLevel() {
        return appLevel;
    }

    public void setAppLevel(String appLevel) {
        this.appLevel = appLevel;
    }

    public String getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getZhiMaScore() {
        return zhiMaScore;
    }

    public void setZhiMaScore(String zhiMaScore) {
        this.zhiMaScore = zhiMaScore;
    }

    public String getMobileWebsite() {
        return mobileWebsite;
    }

    public void setMobileWebsite(String mobileWebsite) {
        this.mobileWebsite = mobileWebsite;
    }

    public String getCallBackUrlAuditStatus() {
        return callBackUrlAuditStatus;
    }

    public void setCallBackUrlAuditStatus(String callBackUrlAuditStatus) {
        this.callBackUrlAuditStatus = callBackUrlAuditStatus;
    }

    public String getCallBackUrlInfoRecollect() {
        return callBackUrlInfoRecollect;
    }

    public void setCallBackUrlInfoRecollect(String callBackUrlInfoRecollect) {
        this.callBackUrlInfoRecollect = callBackUrlInfoRecollect;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RiskApplicationDto{");
        sb.append("appSerialNumber='").append(appSerialNumber).append('\'');
        sb.append(", appName=").append(appName);
        sb.append(", appId='").append(appId).append('\'');
        sb.append(", customerId='").append(customerId).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", certType='").append(certType).append('\'');
        sb.append(", certCode='").append(certCode).append('\'');
        sb.append(", phoneNum='").append(phoneNum).append('\'');
        sb.append(", productQuota='").append(productQuota).append('\'');
        sb.append(", days=").append(days);
        sb.append(", loanIp='").append(loanIp).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", tdBlackBox='").append(tdBlackBox).append('\'');
        sb.append(", jxlUseToken='").append(jxlUseToken).append('\'');
        sb.append(", zhiMaOpenId='").append(zhiMaOpenId).append('\'');
        sb.append(", appLevel='").append(appLevel).append('\'');
        sb.append(", appChannel='").append(appChannel).append('\'');
        sb.append(", appVersion='").append(appVersion).append('\'');
        sb.append(", zhiMaScore='").append(zhiMaScore).append('\'');
        sb.append(", mobileWebsite='").append(mobileWebsite).append('\'');
        sb.append(", callBackUrlAuditStatus='").append(callBackUrlAuditStatus).append('\'');
        sb.append(", callBackUrlInfoRecollect='").append(callBackUrlInfoRecollect).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
