package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class OperatorMonthInfo implements Serializable {
    //主键id
    @ApiModelProperty(value = "主键id")
    private String operatorMonthInfoId;

    //运营商原始数据关联id
    @ApiModelProperty(value = "运营商原始数据关联id")
    private String operatorDataId;

    //用户基础信息关联id
    @ApiModelProperty(value = "用户基础信息关联id")
    private String userInfoId;

    //用户手机号码
    @ApiModelProperty(value = "用户手机号码")
    private String phoneNo;

    //有通话记录月份数
    @ApiModelProperty(value = "有通话记录月份数")
    private Integer monthCount;

    //通话记录获取失败月份数
    @ApiModelProperty(value = "通话记录获取失败月份数")
    private Integer missMonthCount;

    //无通话记录月份数
    @ApiModelProperty(value = "无通话记录月份数")
    private Integer noCallMonth;

    //调用接口传入user_id
    @ApiModelProperty(value = "调用接口传入user_id")
    private String userId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     */
    public String getOperatorMonthInfoId() {
        return operatorMonthInfoId;
    }

    /**
     * 设置主键id
     *
     * @param operatorMonthInfoId 主键id
     */
    public void setOperatorMonthInfoId(String operatorMonthInfoId) {
        this.operatorMonthInfoId = operatorMonthInfoId == null ? null : operatorMonthInfoId.trim();
    }

    /**
     * 获取运营商原始数据关联id
     *
     */
    public String getOperatorDataId() {
        return operatorDataId;
    }

    /**
     * 设置运营商原始数据关联id
     *
     * @param operatorDataId 运营商原始数据关联id
     */
    public void setOperatorDataId(String operatorDataId) {
        this.operatorDataId = operatorDataId == null ? null : operatorDataId.trim();
    }

    /**
     * 获取用户基础信息关联id
     *
     */
    public String getUserInfoId() {
        return userInfoId;
    }

    /**
     * 设置用户基础信息关联id
     *
     * @param userInfoId 用户基础信息关联id
     */
    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId == null ? null : userInfoId.trim();
    }

    /**
     * 获取用户手机号码
     *
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * 设置用户手机号码
     *
     * @param phoneNo 用户手机号码
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    /**
     * 获取有通话记录月份数
     *
     */
    public Integer getMonthCount() {
        return monthCount;
    }

    /**
     * 设置有通话记录月份数
     *
     * @param monthCount 有通话记录月份数
     */
    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    /**
     * 获取通话记录获取失败月份数
     *
     */
    public Integer getMissMonthCount() {
        return missMonthCount;
    }

    /**
     * 设置通话记录获取失败月份数
     *
     * @param missMonthCount 通话记录获取失败月份数
     */
    public void setMissMonthCount(Integer missMonthCount) {
        this.missMonthCount = missMonthCount;
    }

    /**
     * 获取无通话记录月份数
     *
     */
    public Integer getNoCallMonth() {
        return noCallMonth;
    }

    /**
     * 设置无通话记录月份数
     *
     * @param noCallMonth 无通话记录月份数
     */
    public void setNoCallMonth(Integer noCallMonth) {
        this.noCallMonth = noCallMonth;
    }

    /**
     * 获取调用接口传入user_id
     *
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置调用接口传入user_id
     *
     * @param userId 调用接口传入user_id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operatorMonthInfoId=").append(operatorMonthInfoId);
        sb.append(", operatorDataId=").append(operatorDataId);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", phoneNo=").append(phoneNo);
        sb.append(", monthCount=").append(monthCount);
        sb.append(", missMonthCount=").append(missMonthCount);
        sb.append(", noCallMonth=").append(noCallMonth);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}