package com.shangyong.thzlqb.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ZlqbOrderReview implements Serializable {
    //平台订单号
    @ApiModelProperty(value = "平台订单号")
    private String orderNo;

    //审批金额,单位分
    @ApiModelProperty(value = "审批金额,单位分")
    private BigDecimal approveAmount;

    //总本金,单位分
    @ApiModelProperty(value = "总本金,单位分")
    private BigDecimal totalPrincipal;

    //借款期数
    @ApiModelProperty(value = "借款期数")
    private Integer loanPeriod;

    //借款期限
    @ApiModelProperty(value = "借款期限")
    private Integer loanTerms;

    //审批时间
    @ApiModelProperty(value = "审批时间")
    private String approveDate;

    //订单状态 0初始化10待审核20审核中30拒绝 40 无 50审核通过 待签约 60 审核通过 已签约 70 待开户 80 无 90 审核通过 待放款 100 放款失败 110 无 120 已放款 还款中 130 结清 140 逾期中  1000 签约失败 ）
    @ApiModelProperty(value = "订单状态 0初始化10待审核20审核中30拒绝 40 无 50审核通过 待签约 60 审核通过 已签约 70 待开户 80 无 90 审核通过 待放款 100 放款失败 110 无 120 已放款 还款中 130 结清 140 逾期中  1000 签约失败 ）")
    private Integer status;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //更新时间
    @ApiModelProperty(value = "更新时间")
    private Date modifyTime;

    //扩展字段1
    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    //扩展字段2
    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    private static final long serialVersionUID = 1L;

    /**
     * 获取平台订单号
     *
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置平台订单号
     *
     * @param orderNo 平台订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 获取审批金额,单位分
     *
     */
    public BigDecimal getApproveAmount() {
        return approveAmount;
    }

    /**
     * 设置审批金额,单位分
     *
     * @param approveAmount 审批金额,单位分
     */
    public void setApproveAmount(BigDecimal approveAmount) {
        this.approveAmount = approveAmount;
    }

    /**
     * 获取总本金,单位分
     *
     */
    public BigDecimal getTotalPrincipal() {
        return totalPrincipal;
    }

    /**
     * 设置总本金,单位分
     *
     * @param totalPrincipal 总本金,单位分
     */
    public void setTotalPrincipal(BigDecimal totalPrincipal) {
        this.totalPrincipal = totalPrincipal;
    }

    /**
     * 获取借款期数
     *
     */
    public Integer getLoanPeriod() {
        return loanPeriod;
    }

    /**
     * 设置借款期数
     *
     * @param loanPeriod 借款期数
     */
    public void setLoanPeriod(Integer loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    /**
     * 获取借款期限
     *
     */
    public Integer getLoanTerms() {
        return loanTerms;
    }

    /**
     * 设置借款期限
     *
     * @param loanTerms 借款期限
     */
    public void setLoanTerms(Integer loanTerms) {
        this.loanTerms = loanTerms ;
    }

    /**
     * 获取审批时间
     *
     */
    public String getApproveDate() {
        return approveDate;
    }

    /**
     * 设置审批时间
     *
     * @param approveDate 审批时间
     */
    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate == null ? null : approveDate.trim();
    }

    /**
     * 获取订单状态 0初始化10待审核20审核中30拒绝 40 无 50审核通过 待签约 60 审核通过 已签约 70 待开户 80 无 90 审核通过 待放款 100 放款失败 110 无 120 已放款 还款中 130 结清 140 逾期中  1000 签约失败 ）
     *
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态 0初始化10待审核20审核中30拒绝 40 无 50审核通过 待签约 60 审核通过 已签约 70 待开户 80 无 90 审核通过 待放款 100 放款失败 110 无 120 已放款 还款中 130 结清 140 逾期中  1000 签约失败 ）
     *
     * @param status 订单状态 0初始化10待审核20审核中30拒绝 40 无 50审核通过 待签约 60 审核通过 已签约 70 待开户 80 无 90 审核通过 待放款 100 放款失败 110 无 120 已放款 还款中 130 结清 140 逾期中  1000 签约失败 ）
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 获取更新时间
     *
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置更新时间
     *
     * @param modifyTime 更新时间
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderNo=").append(orderNo);
        sb.append(", approveAmount=").append(approveAmount);
        sb.append(", totalPrincipal=").append(totalPrincipal);
        sb.append(", loanPeriod=").append(loanPeriod);
        sb.append(", loanTerms=").append(loanTerms);
        sb.append(", approveDate=").append(approveDate);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", ext1=").append(ext1);
        sb.append(", ext2=").append(ext2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}