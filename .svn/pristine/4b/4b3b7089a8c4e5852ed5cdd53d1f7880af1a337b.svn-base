package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * 设备信息
 * Created by ybds on 2019-03-26.
 */
public class DeviceInfoDto implements Serializable {

    private static final long serialVersionUID = 5882934642631025110L;

    /**
     * 设备号：Android是device_imei（示例：862238037063545），ios是IDFA地址（示例：693BFDD0-E1D7-457F-9687-519FC7D495C6）
     */
    private String device_id;
    /**
     * IP地址（如 192.168.26.160）
     */
    private String ip;
    /**
     * 设备类型如：IOS或Android
     */
    private String device_type;
    /**
     * 手机设备型号如：OPPO R9m、iPhone、HUAWEI MT7-TL00 等
     */
    private String device_model;
    /**
     * 设备操作系统版本（Android如：8.1.0、5.1 IOS如：ios 10.3.3、ios 9.3.2）
     */
    private String device_os;
    /**
     * iOS的openudid（如：02d9562c284cbd139f99a3c87fd3c7432822f0d4）
     */
    private String openudid;
    /**
     * iOS 是否越狱 (0 : 没有越狱；1 ：已经越狱)
     */
    private String jailbreak_flag;
    /**
     * Android是否root (0 : 没有root；1 ：已经root)
     */
    private String root_flag;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getDevice_os() {
        return device_os;
    }

    public void setDevice_os(String device_os) {
        this.device_os = device_os;
    }

    public String getOpenudid() {
        return openudid;
    }

    public void setOpenudid(String openudid) {
        this.openudid = openudid;
    }

    public String getJailbreak_flag() {
        return jailbreak_flag;
    }

    public void setJailbreak_flag(String jailbreak_flag) {
        this.jailbreak_flag = jailbreak_flag;
    }

    public String getRoot_flag() {
        return root_flag;
    }

    public void setRoot_flag(String root_flag) {
        this.root_flag = root_flag;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeviceInfoDto{");
        sb.append("device_id=").append(device_id);
        sb.append(", ip=").append(ip);
        sb.append(", device_type=").append(device_type);
        sb.append(", device_model=").append(device_model);
        sb.append(", device_os=").append(device_os);
        sb.append(", openudid=").append(openudid);
        sb.append(", jailbreak_flag=").append(jailbreak_flag);
        sb.append(", root_flag=").append(root_flag);
        sb.append('}');
        return sb.toString();
    }
}
