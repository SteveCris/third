package com.shangyong.thbase.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class ThCenterConfig implements Serializable {
    //信贷中心配置id
    @ApiModelProperty(value = "信贷中心配置id")
    private String centerConfigId;

    //应用id
    @ApiModelProperty(value = "应用id")
    private String appid;

    //应用secret
    @ApiModelProperty(value = "应用secret")
    private String appsecret;

    //应用服务器地址
    @ApiModelProperty(value = "应用服务器地址")
    private String baseUrl;

    //应用环境参数
    @ApiModelProperty(value = "应用环境参数")
    private String environment;

    //应用令牌缓存key前缀
    @ApiModelProperty(value = "应用令牌缓存key前缀")
    private String tokenKey;

    //应用子令牌缓存key前缀
    @ApiModelProperty(value = "应用子令牌缓存key前缀")
    private String subTokenKey;

    private static final long serialVersionUID = 1L;

    /**
     * 获取信贷中心配置id
     *
     */
    public String getCenterConfigId() {
        return centerConfigId;
    }

    /**
     * 设置信贷中心配置id
     *
     * @param centerConfigId 信贷中心配置id
     */
    public void setCenterConfigId(String centerConfigId) {
        this.centerConfigId = centerConfigId == null ? null : centerConfigId.trim();
    }

    /**
     * 获取应用id
     *
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 设置应用id
     *
     * @param appid 应用id
     */
    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    /**
     * 获取应用secret
     *
     */
    public String getAppsecret() {
        return appsecret;
    }

    /**
     * 设置应用secret
     *
     * @param appsecret 应用secret
     */
    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }

    /**
     * 获取应用服务器地址
     *
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * 设置应用服务器地址
     *
     * @param baseUrl 应用服务器地址
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl == null ? null : baseUrl.trim();
    }

    /**
     * 获取应用环境参数
     *
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * 设置应用环境参数
     *
     * @param environment 应用环境参数
     */
    public void setEnvironment(String environment) {
        this.environment = environment == null ? null : environment.trim();
    }

    /**
     * 获取应用令牌缓存key前缀
     *
     */
    public String getTokenKey() {
        return tokenKey;
    }

    /**
     * 设置应用令牌缓存key前缀
     *
     * @param tokenKey 应用令牌缓存key前缀
     */
    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey == null ? null : tokenKey.trim();
    }

    /**
     * 获取应用子令牌缓存key前缀
     *
     */
    public String getSubTokenKey() {
        return subTokenKey;
    }

    /**
     * 设置应用子令牌缓存key前缀
     *
     * @param subTokenKey 应用子令牌缓存key前缀
     */
    public void setSubTokenKey(String subTokenKey) {
        this.subTokenKey = subTokenKey == null ? null : subTokenKey.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", centerConfigId=").append(centerConfigId);
        sb.append(", appid=").append(appid);
        sb.append(", appsecret=").append(appsecret);
        sb.append(", baseUrl=").append(baseUrl);
        sb.append(", environment=").append(environment);
        sb.append(", tokenKey=").append(tokenKey);
        sb.append(", subTokenKey=").append(subTokenKey);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}