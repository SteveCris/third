package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UserLoanInfo implements Serializable {
    //主键id
    @ApiModelProperty(value = "主键id")
    private String loanInfoId;

    //用户基础信息关联id
    @ApiModelProperty(value = "用户基础信息关联id")
    private String userInfoId;

    //借点钱订单号
    @ApiModelProperty(value = "借点钱订单号")
    private String jdqOrderId;

    //贷款金额
    @ApiModelProperty(value = "贷款金额")
    private String loanAmount;

    //贷款期限，若为单期按天还款产品即传天数，多期产品即传期数
    @ApiModelProperty(value = "贷款期限，若为单期按天还款产品即传天数，多期产品即传期数")
    private String loanTerm;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     */
    public String getLoanInfoId() {
        return loanInfoId;
    }

    /**
     * 设置主键id
     *
     * @param loanInfoId 主键id
     */
    public void setLoanInfoId(String loanInfoId) {
        this.loanInfoId = loanInfoId == null ? null : loanInfoId.trim();
    }

    /**
     * 获取用户基础信息关联id
     *
     */
    public String getUserInfoId() {
        return userInfoId;
    }

    /**
     * 设置用户基础信息关联id
     *
     * @param userInfoId 用户基础信息关联id
     */
    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId == null ? null : userInfoId.trim();
    }

    /**
     * 获取借点钱订单号
     *
     */
    public String getJdqOrderId() {
        return jdqOrderId;
    }

    /**
     * 设置借点钱订单号
     *
     * @param jdqOrderId 借点钱订单号
     */
    public void setJdqOrderId(String jdqOrderId) {
        this.jdqOrderId = jdqOrderId == null ? null : jdqOrderId.trim();
    }

    /**
     * 获取贷款金额
     *
     */
    public String getLoanAmount() {
        return loanAmount;
    }

    /**
     * 设置贷款金额
     *
     * @param loanAmount 贷款金额
     */
    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount == null ? null : loanAmount.trim();
    }

    /**
     * 获取贷款期限，若为单期按天还款产品即传天数，多期产品即传期数
     *
     */
    public String getLoanTerm() {
        return loanTerm;
    }

    /**
     * 设置贷款期限，若为单期按天还款产品即传天数，多期产品即传期数
     *
     * @param loanTerm 贷款期限，若为单期按天还款产品即传天数，多期产品即传期数
     */
    public void setLoanTerm(String loanTerm) {
        this.loanTerm = loanTerm == null ? null : loanTerm.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", loanInfoId=").append(loanInfoId);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", jdqOrderId=").append(jdqOrderId);
        sb.append(", loanAmount=").append(loanAmount);
        sb.append(", loanTerm=").append(loanTerm);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}