package com.shangyong.thorder.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderBorrowApply implements Serializable {
    //记录id
    @ApiModelProperty(value = "记录id")
    private String applyId;

    //账务系统订单id
    @ApiModelProperty(value = "账务系统订单id")
    private String financeOrderId;

    //期数
    @ApiModelProperty(value = "期数")
    private Integer periods;

    //每期天数
    @ApiModelProperty(value = "每期天数")
    private Integer cycle;

    //产品编号
    @ApiModelProperty(value = "产品编号")
    private String productCode;

    //前置借款金额
    @ApiModelProperty(value = "前置借款金额")
    private BigDecimal preBorrowAmount;

    //借款金额
    @ApiModelProperty(value = "借款金额")
    private BigDecimal borrowAmount;

    //授信uuid
    @ApiModelProperty(value = "授信uuid")
    private String creditUseUuid;

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

    //是否有效
    @ApiModelProperty(value = "是否有效")
    private Boolean ifValid;

    //预计还款时间
    @ApiModelProperty(value = "预计还款时间")
    private Date repaymentPlanTime;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //备注
    @ApiModelProperty(value = "备注")
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录id
     *
     */
    public String getApplyId() {
        return applyId;
    }

    /**
     * 设置记录id
     *
     * @param applyId 记录id
     */
    public void setApplyId(String applyId) {
        this.applyId = applyId == null ? null : applyId.trim();
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
     * 获取产品编号
     *
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置产品编号
     *
     * @param productCode 产品编号
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    /**
     * 获取前置借款金额
     *
     */
    public BigDecimal getPreBorrowAmount() {
        return preBorrowAmount;
    }

    /**
     * 设置前置借款金额
     *
     * @param preBorrowAmount 前置借款金额
     */
    public void setPreBorrowAmount(BigDecimal preBorrowAmount) {
        this.preBorrowAmount = preBorrowAmount;
    }

    /**
     * 获取借款金额
     *
     */
    public BigDecimal getBorrowAmount() {
        return borrowAmount;
    }

    /**
     * 设置借款金额
     *
     * @param borrowAmount 借款金额
     */
    public void setBorrowAmount(BigDecimal borrowAmount) {
        this.borrowAmount = borrowAmount;
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
     * 获取是否有效
     *
     */
    public Boolean getIfValid() {
        return ifValid;
    }

    /**
     * 设置是否有效
     *
     * @param ifValid 是否有效
     */
    public void setIfValid(Boolean ifValid) {
        this.ifValid = ifValid;
    }

    /**
     * 获取预计还款时间
     *
     */
    public Date getRepaymentPlanTime() {
        return repaymentPlanTime;
    }

    /**
     * 设置预计还款时间
     *
     * @param repaymentPlanTime 预计还款时间
     */
    public void setRepaymentPlanTime(Date repaymentPlanTime) {
        this.repaymentPlanTime = repaymentPlanTime;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", applyId=").append(applyId);
        sb.append(", financeOrderId=").append(financeOrderId);
        sb.append(", periods=").append(periods);
        sb.append(", cycle=").append(cycle);
        sb.append(", productCode=").append(productCode);
        sb.append(", preBorrowAmount=").append(preBorrowAmount);
        sb.append(", borrowAmount=").append(borrowAmount);
        sb.append(", creditUseUuid=").append(creditUseUuid);
        sb.append(", ext=").append(ext);
        sb.append(", ext2=").append(ext2);
        sb.append(", appid=").append(appid);
        sb.append(", orderId=").append(orderId);
        sb.append(", ifValid=").append(ifValid);
        sb.append(", repaymentPlanTime=").append(repaymentPlanTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}