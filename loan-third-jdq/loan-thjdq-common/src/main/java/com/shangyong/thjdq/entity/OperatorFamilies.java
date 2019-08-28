package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class OperatorFamilies implements Serializable {
    //主键id
    @ApiModelProperty(value = "主键id")
    private String operatorFamiliesId;

    //运营商原始数据关联id
    @ApiModelProperty(value = "运营商原始数据关联id")
    private String operatorDataId;

    //用户基础信息关联id
    @ApiModelProperty(value = "用户基础信息关联id")
    private String userInfoId;

    //亲情网编号
    @ApiModelProperty(value = "亲情网编号")
    private String familyNum;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     */
    public String getOperatorFamiliesId() {
        return operatorFamiliesId;
    }

    /**
     * 设置主键id
     *
     * @param operatorFamiliesId 主键id
     */
    public void setOperatorFamiliesId(String operatorFamiliesId) {
        this.operatorFamiliesId = operatorFamiliesId == null ? null : operatorFamiliesId.trim();
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
     * 获取亲情网编号
     *
     */
    public String getFamilyNum() {
        return familyNum;
    }

    /**
     * 设置亲情网编号
     *
     * @param familyNum 亲情网编号
     */
    public void setFamilyNum(String familyNum) {
        this.familyNum = familyNum == null ? null : familyNum.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operatorFamiliesId=").append(operatorFamiliesId);
        sb.append(", operatorDataId=").append(operatorDataId);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", familyNum=").append(familyNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}