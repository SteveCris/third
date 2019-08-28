package com.shangyong.thryt;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HGoods implements Serializable {
    //主键
    @ApiModelProperty(value = "主键")
    private String guid;

    //商店编号
    @ApiModelProperty(value = "商店编号")
    private String storeGuid;

    //商品图片
    @ApiModelProperty(value = "商品图片")
    private String picUrl;

    //商品价格
    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    //商品名称
    @ApiModelProperty(value = "商品名称")
    private String name;

    //英文名称
    @ApiModelProperty(value = "英文名称")
    private String englishName;

    //备注说明
    @ApiModelProperty(value = "备注说明")
    private String descr;

    //星级(0-5)
    @ApiModelProperty(value = "星级(0-5)")
    private Integer starLevel;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //商品大图
    @ApiModelProperty(value = "商品大图")
    private String bigPicUrl;

    //服务类别
    @ApiModelProperty(value = "服务类别")
    private String serviceType;

    //流行状态（正常、新品）
    @ApiModelProperty(value = "流行状态（正常、新品）")
    private String fashionType;

    //扩展字段1
    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    //扩展字段2
    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     */
    public String getGuid() {
        return guid;
    }

    /**
     * 设置主键
     *
     * @param guid 主键
     */
    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    /**
     * 获取商店编号
     *
     */
    public String getStoreGuid() {
        return storeGuid;
    }

    /**
     * 设置商店编号
     *
     * @param storeGuid 商店编号
     */
    public void setStoreGuid(String storeGuid) {
        this.storeGuid = storeGuid == null ? null : storeGuid.trim();
    }

    /**
     * 获取商品图片
     *
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置商品图片
     *
     * @param picUrl 商品图片
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    /**
     * 获取商品价格
     *
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     *
     * @param price 商品价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取商品名称
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     *
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取英文名称
     *
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * 设置英文名称
     *
     * @param englishName 英文名称
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    /**
     * 获取备注说明
     *
     */
    public String getDescr() {
        return descr;
    }

    /**
     * 设置备注说明
     *
     * @param descr 备注说明
     */
    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    /**
     * 获取星级(0-5)
     *
     */
    public Integer getStarLevel() {
        return starLevel;
    }

    /**
     * 设置星级(0-5)
     *
     * @param starLevel 星级(0-5)
     */
    public void setStarLevel(Integer starLevel) {
        this.starLevel = starLevel;
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
     * 获取商品大图
     *
     */
    public String getBigPicUrl() {
        return bigPicUrl;
    }

    /**
     * 设置商品大图
     *
     * @param bigPicUrl 商品大图
     */
    public void setBigPicUrl(String bigPicUrl) {
        this.bigPicUrl = bigPicUrl == null ? null : bigPicUrl.trim();
    }

    /**
     * 获取服务类别
     *
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * 设置服务类别
     *
     * @param serviceType 服务类别
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    /**
     * 获取流行状态（正常、新品）
     *
     */
    public String getFashionType() {
        return fashionType;
    }

    /**
     * 设置流行状态（正常、新品）
     *
     * @param fashionType 流行状态（正常、新品）
     */
    public void setFashionType(String fashionType) {
        this.fashionType = fashionType == null ? null : fashionType.trim();
    }

    /**
     * 获取扩展字段1
     *
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 设置扩展字段1
     *
     * @param ext1 扩展字段1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guid=").append(guid);
        sb.append(", storeGuid=").append(storeGuid);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", price=").append(price);
        sb.append(", name=").append(name);
        sb.append(", englishName=").append(englishName);
        sb.append(", descr=").append(descr);
        sb.append(", starLevel=").append(starLevel);
        sb.append(", createTime=").append(createTime);
        sb.append(", bigPicUrl=").append(bigPicUrl);
        sb.append(", serviceType=").append(serviceType);
        sb.append(", fashionType=").append(fashionType);
        sb.append(", ext1=").append(ext1);
        sb.append(", ext2=").append(ext2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}