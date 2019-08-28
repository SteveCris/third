package com.shangyong.thzlqb.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class ZlqbBandCardInfo implements Serializable {
    //绑卡id
    @ApiModelProperty(value = "绑卡id")
    private String bindId;

    //订单id
    @ApiModelProperty(value = "订单id")
    private String orderNo;

    //身份证号码
    @ApiModelProperty(value = "身份证号码")
    private String idCard;

    //银行卡号
    @ApiModelProperty(value = "银行卡号")
    private String bankCardNo;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    //扩展字段1
    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    //扩展字段2
    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    private static final long serialVersionUID = 1L;

    /**
     * 获取绑卡id
     *
     */
    public String getBindId() {
        return bindId;
    }

    /**
     * 设置绑卡id
     *
     * @param bindId 绑卡id
     */
    public void setBindId(String bindId) {
        this.bindId = bindId == null ? null : bindId.trim();
    }

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
     * 获取身份证号码
     *
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置身份证号码
     *
     * @param idCard 身份证号码
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    /**
     * 获取银行卡号
     *
     */
    public String getBankCardNo() {
        return bankCardNo;
    }

    /**
     * 设置银行卡号
     *
     * @param bankCardNo 银行卡号
     */
    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
    }

    /**
     * 获取创建时间
     *
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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
        sb.append(", bindId=").append(bindId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", idCard=").append(idCard);
        sb.append(", bankCardNo=").append(bankCardNo);
        sb.append(", createTime=").append(createTime);
        sb.append(", ext1=").append(ext1);
        sb.append(", ext2=").append(ext2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}