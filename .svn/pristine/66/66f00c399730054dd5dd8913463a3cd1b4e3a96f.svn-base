package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class OperatorRecharges implements Serializable {
    //主键id
    @ApiModelProperty(value = "主键id")
    private String operatorRechargesId;

    //运营商原始数据关联id
    @ApiModelProperty(value = "运营商原始数据关联id")
    private String operatorDataId;

    //用户基础信息关联id
    @ApiModelProperty(value = "用户基础信息关联id")
    private String userInfoId;

    //详情标识，标识唯一一条记录
    @ApiModelProperty(value = "详情标识，标识唯一一条记录")
    private String detailsId;

    //充值时间，格式：yyyy-MM-dd HH:mm:ss
    @ApiModelProperty(value = "充值时间，格式：yyyy-MM-dd HH:mm:ss")
    private String rechargeTime;

    //充值金额(单位: 分)
    @ApiModelProperty(value = "充值金额(单位: 分)")
    private Integer amount;

    //充值方式. e.g. 现金
    @ApiModelProperty(value = "充值方式. e.g. 现金")
    private String type;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     */
    public String getOperatorRechargesId() {
        return operatorRechargesId;
    }

    /**
     * 设置主键id
     *
     * @param operatorRechargesId 主键id
     */
    public void setOperatorRechargesId(String operatorRechargesId) {
        this.operatorRechargesId = operatorRechargesId == null ? null : operatorRechargesId.trim();
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
     * 获取详情标识，标识唯一一条记录
     *
     */
    public String getDetailsId() {
        return detailsId;
    }

    /**
     * 设置详情标识，标识唯一一条记录
     *
     * @param detailsId 详情标识，标识唯一一条记录
     */
    public void setDetailsId(String detailsId) {
        this.detailsId = detailsId == null ? null : detailsId.trim();
    }

    /**
     * 获取充值时间，格式：yyyy-MM-dd HH:mm:ss
     *
     */
    public String getRechargeTime() {
        return rechargeTime;
    }

    /**
     * 设置充值时间，格式：yyyy-MM-dd HH:mm:ss
     *
     * @param rechargeTime 充值时间，格式：yyyy-MM-dd HH:mm:ss
     */
    public void setRechargeTime(String rechargeTime) {
        this.rechargeTime = rechargeTime == null ? null : rechargeTime.trim();
    }

    /**
     * 获取充值金额(单位: 分)
     *
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置充值金额(单位: 分)
     *
     * @param amount 充值金额(单位: 分)
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取充值方式. e.g. 现金
     *
     */
    public String getType() {
        return type;
    }

    /**
     * 设置充值方式. e.g. 现金
     *
     * @param type 充值方式. e.g. 现金
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operatorRechargesId=").append(operatorRechargesId);
        sb.append(", operatorDataId=").append(operatorDataId);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", detailsId=").append(detailsId);
        sb.append(", rechargeTime=").append(rechargeTime);
        sb.append(", amount=").append(amount);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}