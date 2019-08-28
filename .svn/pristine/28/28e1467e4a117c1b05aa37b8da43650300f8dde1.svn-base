package com.shangyong.thryt;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class HGoodsTags implements Serializable {
    //主键
    @ApiModelProperty(value = "主键")
    private String guid;

    //商品外键
    @ApiModelProperty(value = "商品外键")
    private String goodsGuid;

    //标签外键
    @ApiModelProperty(value = "标签外键")
    private String tagsGuid;

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
     * 获取商品外键
     *
     */
    public String getGoodsGuid() {
        return goodsGuid;
    }

    /**
     * 设置商品外键
     *
     * @param goodsGuid 商品外键
     */
    public void setGoodsGuid(String goodsGuid) {
        this.goodsGuid = goodsGuid == null ? null : goodsGuid.trim();
    }

    /**
     * 获取标签外键
     *
     */
    public String getTagsGuid() {
        return tagsGuid;
    }

    /**
     * 设置标签外键
     *
     * @param tagsGuid 标签外键
     */
    public void setTagsGuid(String tagsGuid) {
        this.tagsGuid = tagsGuid == null ? null : tagsGuid.trim();
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
        sb.append(", tagsGuid=").append(tagsGuid);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}