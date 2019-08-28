package com.shangyong.thryt;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class HStore implements Serializable {
    //主键
    @ApiModelProperty(value = "主键")
    private String guid;

    //商店名称
    @ApiModelProperty(value = "商店名称")
    private String name;

    //备注说明
    @ApiModelProperty(value = "备注说明")
    private String descr;

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
     * 获取商店名称
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商店名称
     *
     * @param name 商店名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        sb.append(", name=").append(name);
        sb.append(", descr=").append(descr);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}