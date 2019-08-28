package com.shangyong.thbase.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class ThUserQuarantine implements Serializable {
    //隔离用户id
    @ApiModelProperty(value = "隔离用户id")
    private String userQuarantineId;

    //手机号码
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    //身份证号码
    @ApiModelProperty(value = "身份证号码")
    private String identityNo;

    //用户名称
    @ApiModelProperty(value = "用户名称")
    private String userName;

    //手机身份证md5
    @ApiModelProperty(value = "手机身份证md5")
    private String phoneIdNumberMd5;

    //来源应用id
    @ApiModelProperty(value = "来源应用id")
    private String appid;

    //来源应用标识
    @ApiModelProperty(value = "来源应用标识")
    private String appName;

    //来源订单id
    @ApiModelProperty(value = "来源订单id")
    private String orderId;

    //来源第三方订单id
    @ApiModelProperty(value = "来源第三方订单id")
    private String otherOrderId;

    //原因
    @ApiModelProperty(value = "原因")
    private String reason;

    //隔离结束日期
    @ApiModelProperty(value = "隔离结束日期")
    private Date quarantineEndTime;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取隔离用户id
     *
     */
    public String getUserQuarantineId() {
        return userQuarantineId;
    }

    /**
     * 设置隔离用户id
     *
     * @param userQuarantineId 隔离用户id
     */
    public void setUserQuarantineId(String userQuarantineId) {
        this.userQuarantineId = userQuarantineId == null ? null : userQuarantineId.trim();
    }

    /**
     * 获取手机号码
     *
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取身份证号码
     *
     */
    public String getIdentityNo() {
        return identityNo;
    }

    /**
     * 设置身份证号码
     *
     * @param identityNo 身份证号码
     */
    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo == null ? null : identityNo.trim();
    }

    /**
     * 获取用户名称
     *
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取手机身份证md5
     *
     */
    public String getPhoneIdNumberMd5() {
        return phoneIdNumberMd5;
    }

    /**
     * 设置手机身份证md5
     *
     * @param phoneIdNumberMd5 手机身份证md5
     */
    public void setPhoneIdNumberMd5(String phoneIdNumberMd5) {
        this.phoneIdNumberMd5 = phoneIdNumberMd5 == null ? null : phoneIdNumberMd5.trim();
    }

    /**
     * 获取来源应用id
     *
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 设置来源应用id
     *
     * @param appid 来源应用id
     */
    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    /**
     * 获取来源应用标识
     *
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 设置来源应用标识
     *
     * @param appName 来源应用标识
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * 获取来源订单id
     *
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置来源订单id
     *
     * @param orderId 来源订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 获取来源第三方订单id
     *
     */
    public String getOtherOrderId() {
        return otherOrderId;
    }

    /**
     * 设置来源第三方订单id
     *
     * @param otherOrderId 来源第三方订单id
     */
    public void setOtherOrderId(String otherOrderId) {
        this.otherOrderId = otherOrderId == null ? null : otherOrderId.trim();
    }

    /**
     * 获取原因
     *
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置原因
     *
     * @param reason 原因
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * 获取隔离结束日期
     *
     */
    public Date getQuarantineEndTime() {
        return quarantineEndTime;
    }

    /**
     * 设置隔离结束日期
     *
     * @param quarantineEndTime 隔离结束日期
     */
    public void setQuarantineEndTime(Date quarantineEndTime) {
        this.quarantineEndTime = quarantineEndTime;
    }

    /**
     * 获取创建时间
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userQuarantineId=").append(userQuarantineId);
        sb.append(", mobile=").append(mobile);
        sb.append(", identityNo=").append(identityNo);
        sb.append(", userName=").append(userName);
        sb.append(", phoneIdNumberMd5=").append(phoneIdNumberMd5);
        sb.append(", appid=").append(appid);
        sb.append(", appName=").append(appName);
        sb.append(", orderId=").append(orderId);
        sb.append(", otherOrderId=").append(otherOrderId);
        sb.append(", reason=").append(reason);
        sb.append(", quarantineEndTime=").append(quarantineEndTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}