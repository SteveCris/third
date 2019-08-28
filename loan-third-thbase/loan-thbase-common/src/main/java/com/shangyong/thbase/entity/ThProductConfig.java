package com.shangyong.thbase.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ThProductConfig implements Serializable {
    //产品id
    @ApiModelProperty(value = "产品id")
    private String productId;

    //应用id
    @ApiModelProperty(value = "应用id")
    private String appid;

    //产品编号
    @ApiModelProperty(value = "产品编号")
    private String code;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //备注
    @ApiModelProperty(value = "备注")
    private String remark;

    //产品名称
    @ApiModelProperty(value = "产品名称")
    private String name;

    //产品价格
    @ApiModelProperty(value = "产品价格")
    private BigDecimal price;

    //日利率
    @ApiModelProperty(value = "日利率")
    private BigDecimal dayRate;

    //前置产品价格
    @ApiModelProperty(value = "前置产品价格")
    private BigDecimal prePrice;

    //期数
    @ApiModelProperty(value = "期数")
    private Integer periods;

    //每期天数
    @ApiModelProperty(value = "每期天数")
    private Integer cycle;

    //应用标识
    @ApiModelProperty(value = "应用标识")
    private String appName;

    //应用名称
    @ApiModelProperty(value = "应用名称")
    private String applicationName;

    //扩展字段
    @ApiModelProperty(value = "扩展字段")
    private String ext;

    //扩展字段2
    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    //产品类型(0正常1免息)
    @ApiModelProperty(value = "产品类型(0正常1免息)")
    private Integer type;

    private static final long serialVersionUID = 1L;

    /**
     * 获取产品id
     *
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置产品id
     *
     * @param productId 产品id
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
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
     * 获取产品编号
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置产品编号
     *
     * @param code 产品编号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
     * 获取备注
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取产品名称
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 设置产品名称
     *
     * @param name 产品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取产品价格
     *
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置产品价格
     *
     * @param price 产品价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取日利率
     *
     */
    public BigDecimal getDayRate() {
        return dayRate;
    }

    /**
     * 设置日利率
     *
     * @param dayRate 日利率
     */
    public void setDayRate(BigDecimal dayRate) {
        this.dayRate = dayRate;
    }

    /**
     * 获取前置产品价格
     *
     */
    public BigDecimal getPrePrice() {
        return prePrice;
    }

    /**
     * 设置前置产品价格
     *
     * @param prePrice 前置产品价格
     */
    public void setPrePrice(BigDecimal prePrice) {
        this.prePrice = prePrice;
    }

    /**
     * 获取期数
     *
     */
    public Integer getPeriods() {
        return periods;
    }

    /**
     * 设置期数
     *
     * @param periods 期数
     */
    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    /**
     * 获取每期天数
     *
     */
    public Integer getCycle() {
        return cycle;
    }

    /**
     * 设置每期天数
     *
     * @param cycle 每期天数
     */
    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    /**
     * 获取应用标识
     *
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 设置应用标识
     *
     * @param appName 应用标识
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * 获取应用名称
     *
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * 设置应用名称
     *
     * @param applicationName 应用名称
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName == null ? null : applicationName.trim();
    }

    /**
     * 获取扩展字段
     *
     */
    public String getExt() {
        return ext;
    }

    /**
     * 设置扩展字段
     *
     * @param ext 扩展字段
     */
    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    /**
     * 获取扩展字段2
     *
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 设置扩展字段2
     *
     * @param ext2 扩展字段2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    /**
     * 获取产品类型(0正常1免息)
     *
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置产品类型(0正常1免息)
     *
     * @param type 产品类型(0正常1免息)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productId=").append(productId);
        sb.append(", appid=").append(appid);
        sb.append(", code=").append(code);
        sb.append(", createTime=").append(createTime);
        sb.append(", remark=").append(remark);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", dayRate=").append(dayRate);
        sb.append(", prePrice=").append(prePrice);
        sb.append(", periods=").append(periods);
        sb.append(", cycle=").append(cycle);
        sb.append(", appName=").append(appName);
        sb.append(", applicationName=").append(applicationName);
        sb.append(", ext=").append(ext);
        sb.append(", ext2=").append(ext2);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}