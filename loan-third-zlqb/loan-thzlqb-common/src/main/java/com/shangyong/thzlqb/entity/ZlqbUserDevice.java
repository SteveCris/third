package com.shangyong.thzlqb.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class ZlqbUserDevice implements Serializable {
    //订单id
    @ApiModelProperty(value = "订单id")
    private String orderNo;

    //IP(真实 IP)
    @ApiModelProperty(value = "IP(真实 IP)")
    private String ip;

    //设备类型
    @ApiModelProperty(value = "设备类型")
    private String devicetype;

    //用户id
    @ApiModelProperty(value = "用户id")
    private String mac;

    //IDFA
    @ApiModelProperty(value = "IDFA")
    private String idfa;

    //IMEI
    @ApiModelProperty(value = "IMEI")
    private String imei;

    //GPS 经度
    @ApiModelProperty(value = "GPS 经度")
    private String longitude;

    //GPS 纬度
    @ApiModelProperty(value = "GPS 纬度")
    private String latitude;

    //是否 root 越狱
    @ApiModelProperty(value = "是否 root 越狱")
    private String isroot;

    private static final long serialVersionUID = 1L;

    /**
     * 获取订单id
     *
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单id
     *
     * @param orderNo 订单id
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }


    /**
     * 获取IP(真实 IP)
     *
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP(真实 IP)
     *
     * @param ip IP(真实 IP)
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取设备类型
     *
     */
    public String getDevicetype() {
        return devicetype;
    }

    /**
     * 设置设备类型
     *
     * @param devicetype 设备类型
     */
    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype == null ? null : devicetype.trim();
    }

    /**
     * 获取用户id
     *
     */
    public String getMac() {
        return mac;
    }

    /**
     * 设置用户id
     *
     * @param mac 用户id
     */
    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    /**
     * 获取IDFA
     *
     */
    public String getIdfa() {
        return idfa;
    }

    /**
     * 设置IDFA
     *
     * @param idfa IDFA
     */
    public void setIdfa(String idfa) {
        this.idfa = idfa == null ? null : idfa.trim();
    }

    /**
     * 获取IMEI
     *
     */
    public String getImei() {
        return imei;
    }

    /**
     * 设置IMEI
     *
     * @param imei IMEI
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    /**
     * 获取GPS 经度
     *
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置GPS 经度
     *
     * @param longitude GPS 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * 获取GPS 纬度
     *
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置GPS 纬度
     *
     * @param latitude GPS 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * 获取是否 root 越狱
     *
     */
    public String getIsroot() {
        return isroot;
    }

    /**
     * 设置是否 root 越狱
     *
     * @param isroot 是否 root 越狱
     */
    public void setIsroot(String isroot) {
        this.isroot = isroot == null ? null : isroot.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderNo=").append(orderNo);
        sb.append(", ip=").append(ip);
        sb.append(", devicetype=").append(devicetype);
        sb.append(", mac=").append(mac);
        sb.append(", idfa=").append(idfa);
        sb.append(", imei=").append(imei);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", isroot=").append(isroot);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}