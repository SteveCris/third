package com.shangyong.thryt.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class RytCheckR implements Serializable {
    private String checkId;

    //手机号掩码
    @ApiModelProperty(value = "手机号掩码")
    private String mobileMask;

    //身份证号掩码
    @ApiModelProperty(value = "身份证号掩码")
    private String idCardMask;

    //用户名称
    @ApiModelProperty(value = "用户名称")
    private String userName;

    //md5(手机号+身份证) 
    @ApiModelProperty(value = "md5(手机号+身份证) ")
    private String mCMd5;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //校验结果（0无效1有效）
    @ApiModelProperty(value = "校验结果（0无效1有效）")
    private Integer valid;

    //0-融易推老用户1-新用户2-合作方老用户
    @ApiModelProperty(value = "0-融易推老用户1-新用户2-合作方老用户")
    private Integer userType;

    //valid为0表示用户不能申请该合作机构的产品的原因
    @ApiModelProperty(value = "valid为0表示用户不能申请该合作机构的产品的原因")
    private String canLoanTime;

    //null，1=黑名单， 2=在途订单 ，3=隔离期， 4=非融易推渠道老用户
    @ApiModelProperty(value = "null，1=黑名单， 2=在途订单 ，3=隔离期， 4=非融易推渠道老用户")
    private String reason;

    private static final long serialVersionUID = 1L;

    /**
     */
    public String getCheckId() {
        return checkId;
    }

    /**
     * @param checkId
     */
    public void setCheckId(String checkId) {
        this.checkId = checkId == null ? null : checkId.trim();
    }

    /**
     * 获取手机号掩码
     *
     */
    public String getMobileMask() {
        return mobileMask;
    }

    /**
     * 设置手机号掩码
     *
     * @param mobileMask 手机号掩码
     */
    public void setMobileMask(String mobileMask) {
        this.mobileMask = mobileMask == null ? null : mobileMask.trim();
    }

    /**
     * 获取身份证号掩码
     *
     */
    public String getIdCardMask() {
        return idCardMask;
    }

    /**
     * 设置身份证号掩码
     *
     * @param idCardMask 身份证号掩码
     */
    public void setIdCardMask(String idCardMask) {
        this.idCardMask = idCardMask == null ? null : idCardMask.trim();
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
     * 获取md5(手机号+身份证) 
     *
     */
    public String getmCMd5() {
        return mCMd5;
    }

    /**
     * 设置md5(手机号+身份证) 
     *
     * @param mCMd5 md5(手机号+身份证) 
     */
    public void setmCMd5(String mCMd5) {
        this.mCMd5 = mCMd5 == null ? null : mCMd5.trim();
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

    /**
     * 获取校验结果（0无效1有效）
     *
     */
    public Integer getValid() {
        return valid;
    }

    /**
     * 设置校验结果（0无效1有效）
     *
     * @param valid 校验结果（0无效1有效）
     */
    public void setValid(Integer valid) {
        this.valid = valid;
    }

    /**
     * 获取0-融易推老用户1-新用户2-合作方老用户
     *
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置0-融易推老用户1-新用户2-合作方老用户
     *
     * @param userType 0-融易推老用户1-新用户2-合作方老用户
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取valid为0表示用户不能申请该合作机构的产品的原因
     *
     */
    public String getCanLoanTime() {
        return canLoanTime;
    }

    /**
     * 设置valid为0表示用户不能申请该合作机构的产品的原因
     *
     * @param canLoanTime valid为0表示用户不能申请该合作机构的产品的原因
     */
    public void setCanLoanTime(String canLoanTime) {
        this.canLoanTime = canLoanTime == null ? null : canLoanTime.trim();
    }

    /**
     * 获取null，1=黑名单， 2=在途订单 ，3=隔离期， 4=非融易推渠道老用户
     *
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置null，1=黑名单， 2=在途订单 ，3=隔离期， 4=非融易推渠道老用户
     *
     * @param reason null，1=黑名单， 2=在途订单 ，3=隔离期， 4=非融易推渠道老用户
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", checkId=").append(checkId);
        sb.append(", mobileMask=").append(mobileMask);
        sb.append(", idCardMask=").append(idCardMask);
        sb.append(", userName=").append(userName);
        sb.append(", mCMd5=").append(mCMd5);
        sb.append(", createTime=").append(createTime);
        sb.append(", valid=").append(valid);
        sb.append(", userType=").append(userType);
        sb.append(", canLoanTime=").append(canLoanTime);
        sb.append(", reason=").append(reason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}