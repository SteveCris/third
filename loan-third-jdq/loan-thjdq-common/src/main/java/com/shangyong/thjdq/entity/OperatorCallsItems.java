package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class OperatorCallsItems implements Serializable {
    //主键id
    @ApiModelProperty(value = "主键id")
    private String operatorCallsItemsId;

    //运营商-语音详情关联id
    @ApiModelProperty(value = "运营商-语音详情关联id")
    private String operatorCallsId;

    //详情唯一标识
    @ApiModelProperty(value = "详情唯一标识")
    private String detailsId;

    //通话时间，格式：yyyy-MM-dd HH:mm:ss
    @ApiModelProperty(value = "通话时间，格式：yyyy-MM-dd HH:mm:ss")
    private String callTime;

    //对方通话号码
    @ApiModelProperty(value = "对方通话号码")
    private String peerNumber;

    //通话地(自己的)
    @ApiModelProperty(value = "通话地(自己的)")
    private String location;

    //通话地类型. e.g.省内漫游
    @ApiModelProperty(value = "通话地类型. e.g.省内漫游")
    private String locationType;

    //通话时长(单位:秒)
    @ApiModelProperty(value = "通话时长(单位:秒)")
    private Integer duration;

    //呼叫类型. DIAL-主叫; DIALED-被叫
    @ApiModelProperty(value = "呼叫类型. DIAL-主叫; DIALED-被叫")
    private String dialType;

    //通话费(单位:分)
    @ApiModelProperty(value = "通话费(单位:分)")
    private Integer fee;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     */
    public String getOperatorCallsItemsId() {
        return operatorCallsItemsId;
    }

    /**
     * 设置主键id
     *
     * @param operatorCallsItemsId 主键id
     */
    public void setOperatorCallsItemsId(String operatorCallsItemsId) {
        this.operatorCallsItemsId = operatorCallsItemsId == null ? null : operatorCallsItemsId.trim();
    }

    /**
     * 获取运营商-语音详情关联id
     *
     */
    public String getOperatorCallsId() {
        return operatorCallsId;
    }

    /**
     * 设置运营商-语音详情关联id
     *
     * @param operatorCallsId 运营商-语音详情关联id
     */
    public void setOperatorCallsId(String operatorCallsId) {
        this.operatorCallsId = operatorCallsId == null ? null : operatorCallsId.trim();
    }

    /**
     * 获取详情唯一标识
     *
     */
    public String getDetailsId() {
        return detailsId;
    }

    /**
     * 设置详情唯一标识
     *
     * @param detailsId 详情唯一标识
     */
    public void setDetailsId(String detailsId) {
        this.detailsId = detailsId == null ? null : detailsId.trim();
    }

    /**
     * 获取通话时间，格式：yyyy-MM-dd HH:mm:ss
     *
     */
    public String getCallTime() {
        return callTime;
    }

    /**
     * 设置通话时间，格式：yyyy-MM-dd HH:mm:ss
     *
     * @param callTime 通话时间，格式：yyyy-MM-dd HH:mm:ss
     */
    public void setCallTime(String callTime) {
        this.callTime = callTime == null ? null : callTime.trim();
    }

    /**
     * 获取对方通话号码
     *
     */
    public String getPeerNumber() {
        return peerNumber;
    }

    /**
     * 设置对方通话号码
     *
     * @param peerNumber 对方通话号码
     */
    public void setPeerNumber(String peerNumber) {
        this.peerNumber = peerNumber == null ? null : peerNumber.trim();
    }

    /**
     * 获取通话地(自己的)
     *
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置通话地(自己的)
     *
     * @param location 通话地(自己的)
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * 获取通话地类型. e.g.省内漫游
     *
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * 设置通话地类型. e.g.省内漫游
     *
     * @param locationType 通话地类型. e.g.省内漫游
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType == null ? null : locationType.trim();
    }

    /**
     * 获取通话时长(单位:秒)
     *
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 设置通话时长(单位:秒)
     *
     * @param duration 通话时长(单位:秒)
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 获取呼叫类型. DIAL-主叫; DIALED-被叫
     *
     */
    public String getDialType() {
        return dialType;
    }

    /**
     * 设置呼叫类型. DIAL-主叫; DIALED-被叫
     *
     * @param dialType 呼叫类型. DIAL-主叫; DIALED-被叫
     */
    public void setDialType(String dialType) {
        this.dialType = dialType == null ? null : dialType.trim();
    }

    /**
     * 获取通话费(单位:分)
     *
     */
    public Integer getFee() {
        return fee;
    }

    /**
     * 设置通话费(单位:分)
     *
     * @param fee 通话费(单位:分)
     */
    public void setFee(Integer fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operatorCallsItemsId=").append(operatorCallsItemsId);
        sb.append(", operatorCallsId=").append(operatorCallsId);
        sb.append(", detailsId=").append(detailsId);
        sb.append(", callTime=").append(callTime);
        sb.append(", peerNumber=").append(peerNumber);
        sb.append(", location=").append(location);
        sb.append(", locationType=").append(locationType);
        sb.append(", duration=").append(duration);
        sb.append(", dialType=").append(dialType);
        sb.append(", fee=").append(fee);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}