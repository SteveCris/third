package com.shangyong.thzlqb.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class ZlqbOrder implements Serializable {
    //订单id
    @ApiModelProperty(value = "订单id")
    private String orderNo;

    //交互系统订单id
    @ApiModelProperty(value = "交互系统订单id")
    private String orderId;

    //订单状态（0初始化10数据时限去除20基本数据落库30推送审核40审核通过50审核不通过1000订单取消）
    @ApiModelProperty(value = "订单状态（0初始化10数据时限去除20基本数据落库30推送审核40审核通过50审核不通过1000订单取消）")
    private Integer status;

    //是否处理结束
    @ApiModelProperty(value = "是否处理结束")
    private Boolean ifFinish;

    //描述
    @ApiModelProperty(value = "描述")
    private String remark;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    //扩展字段1
    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    //扩展字段2
    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    @ApiModelProperty(value = "是否签约")
    private Integer isSign;
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
     * 获取交互系统订单id
     *
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置交互系统订单id
     *
     * @param orderId 交互系统订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 获取订单状态（0初始化10数据时限去除20基本数据落库30推送审核40审核通过50审核不通过1000订单取消）
     *
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态（0初始化10数据时限去除20基本数据落库30推送审核40审核通过50审核不通过1000订单取消）
     *
     * @param status 订单状态（0初始化10数据时限去除20基本数据落库30推送审核40审核通过50审核不通过1000订单取消）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取是否处理结束
     *
     */
    public Boolean getIfFinish() {
        return ifFinish;
    }

    /**
     * 设置是否处理结束
     *
     * @param ifFinish 是否处理结束
     */
    public void setIfFinish(Boolean ifFinish) {
        this.ifFinish = ifFinish;
    }

    /**
     * 获取描述
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置描述
     *
     * @param remark 描述
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderNo=").append(orderNo);
        sb.append(", orderId=").append(orderId);
        sb.append(", status=").append(status);
        sb.append(", ifFinish=").append(ifFinish);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", ext1=").append(ext1);
        sb.append(", ext2=").append(ext2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}