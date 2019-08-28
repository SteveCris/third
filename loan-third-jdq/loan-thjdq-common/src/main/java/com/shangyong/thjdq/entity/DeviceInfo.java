package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class DeviceInfo implements Serializable {
    //设备信息主键id
    @ApiModelProperty(value = "设备信息主键id")
    private String deviceInfoId;

    //关联用户id
    @ApiModelProperty(value = "关联用户id")
    private String userInfoId;

    //借点钱订单id
    @ApiModelProperty(value = "借点钱订单id")
    private String jdqOrderId;

    //设备号
    @ApiModelProperty(value = "设备号")
    private String deviceId;

    //IP地址
    @ApiModelProperty(value = "IP地址")
    private String ip;

    //设备类型如：IOS或Android
    @ApiModelProperty(value = "设备类型如：IOS或Android")
    private String deviceType;

    //手机设备型号
    @ApiModelProperty(value = "手机设备型号")
    private String deviceModel;

    //设备操作系统版本
    @ApiModelProperty(value = "设备操作系统版本")
    private String deviceOs;

    //iOS的openudid
    @ApiModelProperty(value = "iOS的openudid")
    private String openudid;

    //IOS 是否越狱 (0 : 没有越狱；1 ：已经越狱)
    @ApiModelProperty(value = "IOS 是否越狱 (0 : 没有越狱；1 ：已经越狱)")
    private String jailbreakFlag;

    //Android是否root (0 : 没有root；1 ：已经root)
    @ApiModelProperty(value = "Android是否root (0 : 没有root；1 ：已经root)")
    private String rootFlag;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取设备信息主键id
     *
     */
    public String getDeviceInfoId() {
        return deviceInfoId;
    }

    /**
     * 设置设备信息主键id
     *
     * @param deviceInfoId 设备信息主键id
     */
    public void setDeviceInfoId(String deviceInfoId) {
        this.deviceInfoId = deviceInfoId == null ? null : deviceInfoId.trim();
    }

    /**
     * 获取关联用户id
     *
     */
    public String getUserInfoId() {
        return userInfoId;
    }

    /**
     * 设置关联用户id
     *
     * @param userInfoId 关联用户id
     */
    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId == null ? null : userInfoId.trim();
    }

    /**
     * 获取借点钱订单id
     *
     */
    public String getJdqOrderId() {
        return jdqOrderId;
    }

    /**
     * 设置借点钱订单id
     *
     * @param jdqOrderId 借点钱订单id
     */
    public void setJdqOrderId(String jdqOrderId) {
        this.jdqOrderId = jdqOrderId == null ? null : jdqOrderId.trim();
    }

    /**
     * 获取设备号
     *
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备号
     *
     * @param deviceId 设备号
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    /**
     * 获取IP地址
     *
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP地址
     *
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取设备类型如：IOS或Android
     *
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * 设置设备类型如：IOS或Android
     *
     * @param deviceType 设备类型如：IOS或Android
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    /**
     * 获取手机设备型号
     *
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * 设置手机设备型号
     *
     * @param deviceModel 手机设备型号
     */
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel == null ? null : deviceModel.trim();
    }

    /**
     * 获取设备操作系统版本
     *
     */
    public String getDeviceOs() {
        return deviceOs;
    }

    /**
     * 设置设备操作系统版本
     *
     * @param deviceOs 设备操作系统版本
     */
    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs == null ? null : deviceOs.trim();
    }

    /**
     * 获取iOS的openudid
     *
     */
    public String getOpenudid() {
        return openudid;
    }

    /**
     * 设置iOS的openudid
     *
     * @param openudid iOS的openudid
     */
    public void setOpenudid(String openudid) {
        this.openudid = openudid == null ? null : openudid.trim();
    }

    /**
     * 获取IOS 是否越狱 (0 : 没有越狱；1 ：已经越狱)
     *
     */
    public String getJailbreakFlag() {
        return jailbreakFlag;
    }

    /**
     * 设置IOS 是否越狱 (0 : 没有越狱；1 ：已经越狱)
     *
     * @param jailbreakFlag IOS 是否越狱 (0 : 没有越狱；1 ：已经越狱)
     */
    public void setJailbreakFlag(String jailbreakFlag) {
        this.jailbreakFlag = jailbreakFlag == null ? null : jailbreakFlag.trim();
    }

    /**
     * 获取Android是否root (0 : 没有root；1 ：已经root)
     *
     */
    public String getRootFlag() {
        return rootFlag;
    }

    /**
     * 设置Android是否root (0 : 没有root；1 ：已经root)
     *
     * @param rootFlag Android是否root (0 : 没有root；1 ：已经root)
     */
    public void setRootFlag(String rootFlag) {
        this.rootFlag = rootFlag == null ? null : rootFlag.trim();
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
        sb.append(", deviceInfoId=").append(deviceInfoId);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", jdqOrderId=").append(jdqOrderId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", ip=").append(ip);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", deviceModel=").append(deviceModel);
        sb.append(", deviceOs=").append(deviceOs);
        sb.append(", openudid=").append(openudid);
        sb.append(", jailbreakFlag=").append(jailbreakFlag);
        sb.append(", rootFlag=").append(rootFlag);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}