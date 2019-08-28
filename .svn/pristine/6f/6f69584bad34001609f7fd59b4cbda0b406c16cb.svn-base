package com.shangyong.thorder.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class OrderLoan implements Serializable {
    //订单id
    @ApiModelProperty(value = "订单id")
    private String orderId;

    //第三方订单id
    @ApiModelProperty(value = "第三方订单id")
    private String otherOrderId;

    //订单状态（0初始化10待审核20审核中30审核失败40待绑卡50绑卡中60待前置签约70待后置签约80待放款90放款中100放款失败110待还款120还款中130已结清1000订单取消）
    @ApiModelProperty(value = "订单状态（0初始化10待审核20审核中30审核失败40待绑卡50绑卡中60待前置签约70待后置签约80待放款90放款中100放款失败110待还款120还款中130已结清1000订单取消）")
    private Integer status;

    //还款状态（0初始化10待还款20已结清30已逾期40逾期结清）
    @ApiModelProperty(value = "还款状态（0初始化10待还款20已结清30已逾期40逾期结清）")
    private Integer repaymentStatus;

    //订单是否完结
    @ApiModelProperty(value = "订单是否完结")
    private Boolean ifFinish;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    //备注
    @ApiModelProperty(value = "备注")
    private String remark;

    //扩展字段
    @ApiModelProperty(value = "扩展字段")
    private String ext;

    //扩展字段2
    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    //应用id
    @ApiModelProperty(value = "应用id")
    private String appid;

    private static final long serialVersionUID = 1L;

    /**
     * 获取订单id
     *
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 获取第三方订单id
     *
     */
    public String getOtherOrderId() {
        return otherOrderId;
    }

    /**
     * 设置第三方订单id
     *
     * @param otherOrderId 第三方订单id
     */
    public void setOtherOrderId(String otherOrderId) {
        this.otherOrderId = otherOrderId == null ? null : otherOrderId.trim();
    }

    /**
     * 获取订单状态（0初始化10待审核20审核中30审核失败40待绑卡50绑卡中60待签约70签约中80待放款90放款中100放款失败110待还款120还款中130已结清140逾期1000订单取消）
     *
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态（0初始化10待审核20审核中30审核失败40待绑卡50绑卡中60待签约70签约中80待放款90放款中100放款失败110待还款120还款中130已结清140逾期1000订单取消）
     *
     * @param status 订单状态（0初始化10待审核20审核中30审核失败40待绑卡50绑卡中60待签约70签约中80待放款90放款中100放款失败110待还款120还款中130已结清140逾期1000订单取消）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取还款状态（0初始化10待还款20已结清30已逾期40逾期结清）
     *
     */
    public Integer getRepaymentStatus() {
        return repaymentStatus;
    }

    /**
     * 设置还款状态（0初始化10待还款20已结清30已逾期40逾期结清）
     *
     * @param repaymentStatus 还款状态（0初始化10待还款20已结清30已逾期40逾期结清）
     */
    public void setRepaymentStatus(Integer repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }

    /**
     * 获取订单是否完结
     *
     */
    public Boolean getIfFinish() {
        return ifFinish;
    }

    /**
     * 设置订单是否完结
     *
     * @param ifFinish 订单是否完结
     */
    public void setIfFinish(Boolean ifFinish) {
        this.ifFinish = ifFinish;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", otherOrderId=").append(otherOrderId);
        sb.append(", status=").append(status);
        sb.append(", repaymentStatus=").append(repaymentStatus);
        sb.append(", ifFinish=").append(ifFinish);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", remark=").append(remark);
        sb.append(", ext=").append(ext);
        sb.append(", ext2=").append(ext2);
        sb.append(", appid=").append(appid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}