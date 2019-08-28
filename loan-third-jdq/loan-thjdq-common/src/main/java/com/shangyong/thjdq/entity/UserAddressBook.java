package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UserAddressBook implements Serializable {
    //主键id
    @ApiModelProperty(value = "主键id")
    private String addressBookId;

    //用户基础信息关联id
    @ApiModelProperty(value = "用户基础信息关联id")
    private String userInfoId;

    //借点钱订单id
    @ApiModelProperty(value = "借点钱订单id")
    private String jdqOrderId;

    //姓名
    @ApiModelProperty(value = "姓名")
    private String name;

    //手机号码,多个手机号以英文逗号分隔
    @ApiModelProperty(value = "手机号码,多个手机号以英文逗号分隔")
    private String mobile;

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
    public String getAddressBookId() {
        return addressBookId;
    }

    /**
     * 设置主键id
     *
     * @param addressBookId 主键id
     */
    public void setAddressBookId(String addressBookId) {
        this.addressBookId = addressBookId == null ? null : addressBookId.trim();
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
     * 获取借点钱订单id
     *
     */
    public String getJdqOrderId() {
        return jdqOrderId;
    }

    /**
     * 设置借点钱订单id
     *
     * @param jdqOrderId 借点钱订单id
     */
    public void setJdqOrderId(String jdqOrderId) {
        this.jdqOrderId = jdqOrderId == null ? null : jdqOrderId.trim();
    }

    /**
     * 获取姓名
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取手机号码,多个手机号以英文逗号分隔
     *
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码,多个手机号以英文逗号分隔
     *
     * @param mobile 手机号码,多个手机号以英文逗号分隔
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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
        sb.append(", addressBookId=").append(addressBookId);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", jdqOrderId=").append(jdqOrderId);
        sb.append(", name=").append(name);
        sb.append(", mobile=").append(mobile);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}