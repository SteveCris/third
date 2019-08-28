package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class OperatorPackages implements Serializable {
    //主键id
    @ApiModelProperty(value = "主键id")
    private String operatorPackagesId;

    //运营商原始数据关联id
    @ApiModelProperty(value = "运营商原始数据关联id")
    private String operatorDataId;

    //用户基础信息关联id
    @ApiModelProperty(value = "用户基础信息关联id")
    private String userInfoId;

    //账单起始日, 格式为yyyy-MM-dd
    @ApiModelProperty(value = "账单起始日, 格式为yyyy-MM-dd")
    private String billStartDate;

    //账单结束日, 格式为yyyy-MM-dd
    @ApiModelProperty(value = "账单结束日, 格式为yyyy-MM-dd")
    private String billEndDate;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     */
    public String getOperatorPackagesId() {
        return operatorPackagesId;
    }

    /**
     * 设置主键id
     *
     * @param operatorPackagesId 主键id
     */
    public void setOperatorPackagesId(String operatorPackagesId) {
        this.operatorPackagesId = operatorPackagesId == null ? null : operatorPackagesId.trim();
    }

    /**
     * 获取运营商原始数据关联id
     *
     */
    public String getOperatorDataId() {
        return operatorDataId;
    }

    /**
     * 设置运营商原始数据关联id
     *
     * @param operatorDataId 运营商原始数据关联id
     */
    public void setOperatorDataId(String operatorDataId) {
        this.operatorDataId = operatorDataId == null ? null : operatorDataId.trim();
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
     * 获取账单起始日, 格式为yyyy-MM-dd
     *
     */
    public String getBillStartDate() {
        return billStartDate;
    }

    /**
     * 设置账单起始日, 格式为yyyy-MM-dd
     *
     * @param billStartDate 账单起始日, 格式为yyyy-MM-dd
     */
    public void setBillStartDate(String billStartDate) {
        this.billStartDate = billStartDate == null ? null : billStartDate.trim();
    }

    /**
     * 获取账单结束日, 格式为yyyy-MM-dd
     *
     */
    public String getBillEndDate() {
        return billEndDate;
    }

    /**
     * 设置账单结束日, 格式为yyyy-MM-dd
     *
     * @param billEndDate 账单结束日, 格式为yyyy-MM-dd
     */
    public void setBillEndDate(String billEndDate) {
        this.billEndDate = billEndDate == null ? null : billEndDate.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operatorPackagesId=").append(operatorPackagesId);
        sb.append(", operatorDataId=").append(operatorDataId);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", billStartDate=").append(billStartDate);
        sb.append(", billEndDate=").append(billEndDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}