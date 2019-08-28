package com.shangyong.thryt;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class HGoodsCommonDetails implements Serializable {
    //主键
    @ApiModelProperty(value = "主键")
    private String guid;

    //商品guid
    @ApiModelProperty(value = "商品guid")
    private String goodsGuid;

    //扩展字段1
    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    //扩展字段2
    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    //扩展字段3
    @ApiModelProperty(value = "扩展字段3")
    private String ext3;

    //扩展字段4
    @ApiModelProperty(value = "扩展字段4")
    private String ext4;

    //扩展字段5
    @ApiModelProperty(value = "扩展字段5")
    private String ext5;

    //扩展字段6
    @ApiModelProperty(value = "扩展字段6")
    private String ext6;

    //扩展字段7
    @ApiModelProperty(value = "扩展字段7")
    private String ext7;

    //扩展字段8
    @ApiModelProperty(value = "扩展字段8")
    private String ext8;

    //扩展字段9
    @ApiModelProperty(value = "扩展字段9")
    private String ext9;

    //扩展字段10
    @ApiModelProperty(value = "扩展字段10")
    private String ext10;

    //扩展字段1地址
    @ApiModelProperty(value = "扩展字段1地址")
    private String ext1Url;

    //扩展字段2地址
    @ApiModelProperty(value = "扩展字段2地址")
    private String ext2Url;

    //扩展字段3地址
    @ApiModelProperty(value = "扩展字段3地址")
    private String ext3Url;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

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
     * 获取商品guid
     *
     */
    public String getGoodsGuid() {
        return goodsGuid;
    }

    /**
     * 设置商品guid
     *
     * @param goodsGuid 商品guid
     */
    public void setGoodsGuid(String goodsGuid) {
        this.goodsGuid = goodsGuid == null ? null : goodsGuid.trim();
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

    /**
     * 获取扩展字段3
     *
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * 设置扩展字段3
     *
     * @param ext3 扩展字段3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    /**
     * 获取扩展字段4
     *
     */
    public String getExt4() {
        return ext4;
    }

    /**
     * 设置扩展字段4
     *
     * @param ext4 扩展字段4
     */
    public void setExt4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }

    /**
     * 获取扩展字段5
     *
     */
    public String getExt5() {
        return ext5;
    }

    /**
     * 设置扩展字段5
     *
     * @param ext5 扩展字段5
     */
    public void setExt5(String ext5) {
        this.ext5 = ext5 == null ? null : ext5.trim();
    }

    /**
     * 获取扩展字段6
     *
     */
    public String getExt6() {
        return ext6;
    }

    /**
     * 设置扩展字段6
     *
     * @param ext6 扩展字段6
     */
    public void setExt6(String ext6) {
        this.ext6 = ext6 == null ? null : ext6.trim();
    }

    /**
     * 获取扩展字段7
     *
     */
    public String getExt7() {
        return ext7;
    }

    /**
     * 设置扩展字段7
     *
     * @param ext7 扩展字段7
     */
    public void setExt7(String ext7) {
        this.ext7 = ext7 == null ? null : ext7.trim();
    }

    /**
     * 获取扩展字段8
     *
     */
    public String getExt8() {
        return ext8;
    }

    /**
     * 设置扩展字段8
     *
     * @param ext8 扩展字段8
     */
    public void setExt8(String ext8) {
        this.ext8 = ext8 == null ? null : ext8.trim();
    }

    /**
     * 获取扩展字段9
     *
     */
    public String getExt9() {
        return ext9;
    }

    /**
     * 设置扩展字段9
     *
     * @param ext9 扩展字段9
     */
    public void setExt9(String ext9) {
        this.ext9 = ext9 == null ? null : ext9.trim();
    }

    /**
     * 获取扩展字段10
     *
     */
    public String getExt10() {
        return ext10;
    }

    /**
     * 设置扩展字段10
     *
     * @param ext10 扩展字段10
     */
    public void setExt10(String ext10) {
        this.ext10 = ext10 == null ? null : ext10.trim();
    }

    /**
     * 获取扩展字段1地址
     *
     */
    public String getExt1Url() {
        return ext1Url;
    }

    /**
     * 设置扩展字段1地址
     *
     * @param ext1Url 扩展字段1地址
     */
    public void setExt1Url(String ext1Url) {
        this.ext1Url = ext1Url == null ? null : ext1Url.trim();
    }

    /**
     * 获取扩展字段2地址
     *
     */
    public String getExt2Url() {
        return ext2Url;
    }

    /**
     * 设置扩展字段2地址
     *
     * @param ext2Url 扩展字段2地址
     */
    public void setExt2Url(String ext2Url) {
        this.ext2Url = ext2Url == null ? null : ext2Url.trim();
    }

    /**
     * 获取扩展字段3地址
     *
     */
    public String getExt3Url() {
        return ext3Url;
    }

    /**
     * 设置扩展字段3地址
     *
     * @param ext3Url 扩展字段3地址
     */
    public void setExt3Url(String ext3Url) {
        this.ext3Url = ext3Url == null ? null : ext3Url.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guid=").append(guid);
        sb.append(", goodsGuid=").append(goodsGuid);
        sb.append(", ext1=").append(ext1);
        sb.append(", ext2=").append(ext2);
        sb.append(", ext3=").append(ext3);
        sb.append(", ext4=").append(ext4);
        sb.append(", ext5=").append(ext5);
        sb.append(", ext6=").append(ext6);
        sb.append(", ext7=").append(ext7);
        sb.append(", ext8=").append(ext8);
        sb.append(", ext9=").append(ext9);
        sb.append(", ext10=").append(ext10);
        sb.append(", ext1Url=").append(ext1Url);
        sb.append(", ext2Url=").append(ext2Url);
        sb.append(", ext3Url=").append(ext3Url);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}