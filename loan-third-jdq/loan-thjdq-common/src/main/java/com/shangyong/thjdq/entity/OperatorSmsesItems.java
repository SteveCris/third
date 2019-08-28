package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class OperatorSmsesItems implements Serializable {
    //主键id
    @ApiModelProperty(value = "主键id")
    private String operatorSmsesItemsId;

    //短信详情关联id
    @ApiModelProperty(value = "短信详情关联id")
    private String operatorSmsesId;

    //详情唯一标识
    @ApiModelProperty(value = "详情唯一标识")
    private String detailsId;

    //收/发短信时间
    @ApiModelProperty(value = "收/发短信时间")
    private String msgTime;

    //对方号码
    @ApiModelProperty(value = "对方号码")
    private String peerNumber;

    //通话地(自己的)
    @ApiModelProperty(value = "通话地(自己的)")
    private String location;

    //SEND-发送; RECEIVE-收取
    @ApiModelProperty(value = "SEND-发送; RECEIVE-收取")
    private String sendType;

    //SMS-短信; MMS-彩信
    @ApiModelProperty(value = "SMS-短信; MMS-彩信")
    private String msgType;

    //业务名称. e.g. 点对点(网内)
    @ApiModelProperty(value = "业务名称. e.g. 点对点(网内)")
    private String serviceName;

    //通话费(单位:分)
    @ApiModelProperty(value = "通话费(单位:分)")
    private Integer fee;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     */
    public String getOperatorSmsesItemsId() {
        return operatorSmsesItemsId;
    }

    /**
     * 设置主键id
     *
     * @param operatorSmsesItemsId 主键id
     */
    public void setOperatorSmsesItemsId(String operatorSmsesItemsId) {
        this.operatorSmsesItemsId = operatorSmsesItemsId == null ? null : operatorSmsesItemsId.trim();
    }

    /**
     * 获取短信详情关联id
     *
     */
    public String getOperatorSmsesId() {
        return operatorSmsesId;
    }

    /**
     * 设置短信详情关联id
     *
     * @param operatorSmsesId 短信详情关联id
     */
    public void setOperatorSmsesId(String operatorSmsesId) {
        this.operatorSmsesId = operatorSmsesId == null ? null : operatorSmsesId.trim();
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
     * 获取收/发短信时间
     *
     */
    public String getMsgTime() {
        return msgTime;
    }

    /**
     * 设置收/发短信时间
     *
     * @param msgTime 收/发短信时间
     */
    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime == null ? null : msgTime.trim();
    }

    /**
     * 获取对方号码
     *
     */
    public String getPeerNumber() {
        return peerNumber;
    }

    /**
     * 设置对方号码
     *
     * @param peerNumber 对方号码
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
     * 获取SEND-发送; RECEIVE-收取
     *
     */
    public String getSendType() {
        return sendType;
    }

    /**
     * 设置SEND-发送; RECEIVE-收取
     *
     * @param sendType SEND-发送; RECEIVE-收取
     */
    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    /**
     * 获取SMS-短信; MMS-彩信
     *
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * 设置SMS-短信; MMS-彩信
     *
     * @param msgType SMS-短信; MMS-彩信
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    /**
     * 获取业务名称. e.g. 点对点(网内)
     *
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * 设置业务名称. e.g. 点对点(网内)
     *
     * @param serviceName 业务名称. e.g. 点对点(网内)
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
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
        sb.append(", operatorSmsesItemsId=").append(operatorSmsesItemsId);
        sb.append(", operatorSmsesId=").append(operatorSmsesId);
        sb.append(", detailsId=").append(detailsId);
        sb.append(", msgTime=").append(msgTime);
        sb.append(", peerNumber=").append(peerNumber);
        sb.append(", location=").append(location);
        sb.append(", sendType=").append(sendType);
        sb.append(", msgType=").append(msgType);
        sb.append(", serviceName=").append(serviceName);
        sb.append(", fee=").append(fee);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}