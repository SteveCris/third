package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    //用户来源id
    @ApiModelProperty(value = "用户来源id")
    private String userId;

    //身份证
    @ApiModelProperty(value = "身份证")
    private String idNumber;

    //掩码身份证
    @ApiModelProperty(value = "掩码身份证")
    private String maskIdNumber;

    //掩码手机号码
    @ApiModelProperty(value = "掩码手机号码")
    private String maskPhone;

    //掩码姓名
    @ApiModelProperty(value = "掩码姓名")
    private String maskUserName;

    //用户状态 0:新用户;1:老用户
    @ApiModelProperty(value = "用户状态 0:新用户;1:老用户")
    private Integer userState;

    //来源渠道
    @ApiModelProperty(value = "来源渠道")
    private String channel;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户来源id
     *
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户来源id
     *
     * @param userId 用户来源id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取身份证
     *
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置身份证
     *
     * @param idNumber 身份证
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    /**
     * 获取掩码身份证
     *
     */
    public String getMaskIdNumber() {
        return maskIdNumber;
    }

    /**
     * 设置掩码身份证
     *
     * @param maskIdNumber 掩码身份证
     */
    public void setMaskIdNumber(String maskIdNumber) {
        this.maskIdNumber = maskIdNumber == null ? null : maskIdNumber.trim();
    }

    /**
     * 获取掩码手机号码
     *
     */
    public String getMaskPhone() {
        return maskPhone;
    }

    /**
     * 设置掩码手机号码
     *
     * @param maskPhone 掩码手机号码
     */
    public void setMaskPhone(String maskPhone) {
        this.maskPhone = maskPhone == null ? null : maskPhone.trim();
    }

    /**
     * 获取掩码姓名
     *
     */
    public String getMaskUserName() {
        return maskUserName;
    }

    /**
     * 设置掩码姓名
     *
     * @param maskUserName 掩码姓名
     */
    public void setMaskUserName(String maskUserName) {
        this.maskUserName = maskUserName == null ? null : maskUserName.trim();
    }

    /**
     * 获取用户状态 0:新用户;1:老用户
     *
     */
    public Integer getUserState() {
        return userState;
    }

    /**
     * 设置用户状态 0:新用户;1:老用户
     *
     * @param userState 用户状态 0:新用户;1:老用户
     */
    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    /**
     * 获取来源渠道
     *
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置来源渠道
     *
     * @param channel 来源渠道
     */
    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
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
     * 获取修改时间
     *
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", idNumber=").append(idNumber);
        sb.append(", maskIdNumber=").append(maskIdNumber);
        sb.append(", maskPhone=").append(maskPhone);
        sb.append(", maskUserName=").append(maskUserName);
        sb.append(", userState=").append(userState);
        sb.append(", channel=").append(channel);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}