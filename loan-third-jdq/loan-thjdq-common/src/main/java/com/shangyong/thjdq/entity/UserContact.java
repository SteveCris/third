package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UserContact implements Serializable {
    //主键id
    @ApiModelProperty(value = "主键id")
    private String userContactId;

    //关联用户id
    @ApiModelProperty(value = "关联用户id")
    private String userInfoId;

    //借点钱订单id
    @ApiModelProperty(value = "借点钱订单id")
    private String jdqOrderId;

    //第一联系人关系
    @ApiModelProperty(value = "第一联系人关系")
    private String relation;

    //第一联系人姓名
    @ApiModelProperty(value = "第一联系人姓名")
    private String name;

    //第一联系人手机号码
    @ApiModelProperty(value = "第一联系人手机号码")
    private String mobile;

    //第二联系人姓名
    @ApiModelProperty(value = "第二联系人姓名")
    private String nameSpare;

    //第二联系人手机号码
    @ApiModelProperty(value = "第二联系人手机号码")
    private String mobileSpare;

    //第二联系人关系
    @ApiModelProperty(value = "第二联系人关系")
    private String relationSpare;

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
    public String getUserContactId() {
        return userContactId;
    }

    /**
     * 设置主键id
     *
     * @param userContactId 主键id
     */
    public void setUserContactId(String userContactId) {
        this.userContactId = userContactId == null ? null : userContactId.trim();
    }

    /**
     * 获取关联用户id
     *
     */
    public String getUserInfoId() {
        return userInfoId;
    }

    /**
     * 设置关联用户id
     *
     * @param userInfoId 关联用户id
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
     * 获取第一联系人关系
     *
     */
    public String getRelation() {
        return relation;
    }

    /**
     * 设置第一联系人关系
     *
     * @param relation 第一联系人关系
     */
    public void setRelation(String relation) {
        this.relation = relation == null ? null : relation.trim();
    }

    /**
     * 获取第一联系人姓名
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 设置第一联系人姓名
     *
     * @param name 第一联系人姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取第一联系人手机号码
     *
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置第一联系人手机号码
     *
     * @param mobile 第一联系人手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取第二联系人姓名
     *
     */
    public String getNameSpare() {
        return nameSpare;
    }

    /**
     * 设置第二联系人姓名
     *
     * @param nameSpare 第二联系人姓名
     */
    public void setNameSpare(String nameSpare) {
        this.nameSpare = nameSpare == null ? null : nameSpare.trim();
    }

    /**
     * 获取第二联系人手机号码
     *
     */
    public String getMobileSpare() {
        return mobileSpare;
    }

    /**
     * 设置第二联系人手机号码
     *
     * @param mobileSpare 第二联系人手机号码
     */
    public void setMobileSpare(String mobileSpare) {
        this.mobileSpare = mobileSpare == null ? null : mobileSpare.trim();
    }

    /**
     * 获取第二联系人关系
     *
     */
    public String getRelationSpare() {
        return relationSpare;
    }

    /**
     * 设置第二联系人关系
     *
     * @param relationSpare 第二联系人关系
     */
    public void setRelationSpare(String relationSpare) {
        this.relationSpare = relationSpare == null ? null : relationSpare.trim();
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
        sb.append(", userContactId=").append(userContactId);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", jdqOrderId=").append(jdqOrderId);
        sb.append(", relation=").append(relation);
        sb.append(", name=").append(name);
        sb.append(", mobile=").append(mobile);
        sb.append(", nameSpare=").append(nameSpare);
        sb.append(", mobileSpare=").append(mobileSpare);
        sb.append(", relationSpare=").append(relationSpare);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}