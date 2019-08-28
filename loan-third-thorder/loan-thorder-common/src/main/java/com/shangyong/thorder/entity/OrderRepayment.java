package com.shangyong.thorder.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderRepayment implements Serializable {
    //还款记录id
    @ApiModelProperty(value = "还款记录id")
    private String repaymentId;

    //是不是逾期还款
    @ApiModelProperty(value = "是不是逾期还款")
    private Boolean ifOverdue;

    //扩展字段
    @ApiModelProperty(value = "扩展字段")
    private String ext;

    //扩展字段2
    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    //应用id
    @ApiModelProperty(value = "应用id")
    private String appid;

    //订单id
    @ApiModelProperty(value = "订单id")
    private String orderId;

    //备注
    @ApiModelProperty(value = "备注")
    private String remark;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //前置还款金额
    @ApiModelProperty(value = "前置还款金额")
    private BigDecimal preRepaymentAmount;

    //正常还款金额
    @ApiModelProperty(value = "正常还款金额")
    private BigDecimal repaymentAmount;

    //还款总金额
    @ApiModelProperty(value = "还款总金额")
    private BigDecimal totalRepaymentAmount;

    //授信uuid
    @ApiModelProperty(value = "授信uuid")
    private String creditUseUuid;

    //账务系统订单id
    @ApiModelProperty(value = "账务系统订单id")
    private String financeOrderId;

    //借款订单id
    @ApiModelProperty(value = "借款订单id")
    private String applyId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取还款记录id
     *
     */
    public String getRepaymentId() {
        return repaymentId;
    }

    /**
     * 设置还款记录id
     *
     * @param repaymentId 还款记录id
     */
    public void setRepaymentId(String repaymentId) {
        this.repaymentId = repaymentId == null ? null : repaymentId.trim();
    }

    /**
     * 获取是不是逾期还款
     *
     */
    public Boolean getIfOverdue() {
        return ifOverdue;
    }

    /**
     * 设置是不是逾期还款
     *
     * @param ifOverdue 是不是逾期还款
     */
    public void setIfOverdue(Boolean ifOverdue) {
        this.ifOverdue = ifOverdue;
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
     * 获取前置还款金额
     *
     */
    public BigDecimal getPreRepaymentAmount() {
        return preRepaymentAmount;
    }

    /**
     * 设置前置还款金额
     *
     * @param preRepaymentAmount 前置还款金额
     */
    public void setPreRepaymentAmount(BigDecimal preRepaymentAmount) {
        this.preRepaymentAmount = preRepaymentAmount;
    }

    /**
     * 获取正常还款金额
     *
     */
    public BigDecimal getRepaymentAmount() {
        return repaymentAmount;
    }

    /**
     * 设置正常还款金额
     *
     * @param repaymentAmount 正常还款金额
     */
    public void setRepaymentAmount(BigDecimal repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    /**
     * 获取还款总金额
     *
     */
    public BigDecimal getTotalRepaymentAmount() {
        return totalRepaymentAmount;
    }

    /**
     * 设置还款总金额
     *
     * @param totalRepaymentAmount 还款总金额
     */
    public void setTotalRepaymentAmount(BigDecimal totalRepaymentAmount) {
        this.totalRepaymentAmount = totalRepaymentAmount;
    }

    /**
     * 获取授信uuid
     *
     */
    public String getCreditUseUuid() {
        return creditUseUuid;
    }

    /**
     * 设置授信uuid
     *
     * @param creditUseUuid 授信uuid
     */
    public void setCreditUseUuid(String creditUseUuid) {
        this.creditUseUuid = creditUseUuid == null ? null : creditUseUuid.trim();
    }

    /**
     * 获取账务系统订单id
     *
     */
    public String getFinanceOrderId() {
        return financeOrderId;
    }

    /**
     * 设置账务系统订单id
     *
     * @param financeOrderId 账务系统订单id
     */
    public void setFinanceOrderId(String financeOrderId) {
        this.financeOrderId = financeOrderId == null ? null : financeOrderId.trim();
    }

    /**
     * 获取借款订单id
     *
     */
    public String getApplyId() {
        return applyId;
    }

    /**
     * 设置借款订单id
     *
     * @param applyId 借款订单id
     */
    public void setApplyId(String applyId) {
        this.applyId = applyId == null ? null : applyId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", repaymentId=").append(repaymentId);
        sb.append(", ifOverdue=").append(ifOverdue);
        sb.append(", ext=").append(ext);
        sb.append(", ext2=").append(ext2);
        sb.append(", appid=").append(appid);
        sb.append(", orderId=").append(orderId);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", preRepaymentAmount=").append(preRepaymentAmount);
        sb.append(", repaymentAmount=").append(repaymentAmount);
        sb.append(", totalRepaymentAmount=").append(totalRepaymentAmount);
        sb.append(", creditUseUuid=").append(creditUseUuid);
        sb.append(", financeOrderId=").append(financeOrderId);
        sb.append(", applyId=").append(applyId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}