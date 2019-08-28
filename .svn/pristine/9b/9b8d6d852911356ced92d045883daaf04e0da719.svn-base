package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class OperatorSmses implements Serializable {
    //主键id
    @ApiModelProperty(value = "主键id")
    private String operatorSmsesId;

    //运营商原始数据关联id
    @ApiModelProperty(value = "运营商原始数据关联id")
    private String operatorDataId;

    //用户基础信息关联id
    @ApiModelProperty(value = "用户基础信息关联id")
    private String userInfoId;

    //详情月份，格式 yyyy-MM
    @ApiModelProperty(value = "详情月份，格式 yyyy-MM")
    private String billMonth;

    //记录总数
    @ApiModelProperty(value = "记录总数")
    private Integer totalSize;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     */
    public String getOperatorSmsesId() {
        return operatorSmsesId;
    }

    /**
     * 设置主键id
     *
     * @param operatorSmsesId 主键id
     */
    public void setOperatorSmsesId(String operatorSmsesId) {
        this.operatorSmsesId = operatorSmsesId == null ? null : operatorSmsesId.trim();
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
     * 获取详情月份，格式 yyyy-MM
     *
     */
    public String getBillMonth() {
        return billMonth;
    }

    /**
     * 设置详情月份，格式 yyyy-MM
     *
     * @param billMonth 详情月份，格式 yyyy-MM
     */
    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth == null ? null : billMonth.trim();
    }

    /**
     * 获取记录总数
     *
     */
    public Integer getTotalSize() {
        return totalSize;
    }

    /**
     * 设置记录总数
     *
     * @param totalSize 记录总数
     */
    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operatorSmsesId=").append(operatorSmsesId);
        sb.append(", operatorDataId=").append(operatorDataId);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", billMonth=").append(billMonth);
        sb.append(", totalSize=").append(totalSize);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}