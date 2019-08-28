package com.shangyong.thryt.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class RytLinkman implements Serializable {
    //联系人id
    @ApiModelProperty(value = "联系人id")
    private String linkId;

    //订单id
    @ApiModelProperty(value = "订单id")
    private String orderNo;

    //姓名
    @ApiModelProperty(value = "姓名")
    private String name;

    //手机号
    @ApiModelProperty(value = "手机号")
    private String mobile;

    //联系人类型 1、父亲2、母亲3、兄弟姐妹4、子女5、配偶6、其它亲属7、朋友8、同学9、同事
    @ApiModelProperty(value = "联系人类型 1、父亲2、母亲3、兄弟姐妹4、子女5、配偶6、其它亲属7、朋友8、同学9、同事")
    private String type;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //扩展字段1
    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    //扩展字段2
    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    private static final long serialVersionUID = 1L;

    /**
     * 获取联系人id
     *
     */
    public String getLinkId() {
        return linkId;
    }

    /**
     * 设置联系人id
     *
     * @param linkId 联系人id
     */
    public void setLinkId(String linkId) {
        this.linkId = linkId == null ? null : linkId.trim();
    }

    /**
     * 获取订单id
     *
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单id
     *
     * @param orderNo 订单id
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
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
     * 获取手机号
     *
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取联系人类型 1、父亲2、母亲3、兄弟姐妹4、子女5、配偶6、其它亲属7、朋友8、同学9、同事
     *
     */
    public String getType() {
        return type;
    }

    /**
     * 设置联系人类型 1、父亲2、母亲3、兄弟姐妹4、子女5、配偶6、其它亲属7、朋友8、同学9、同事
     *
     * @param type 联系人类型 1、父亲2、母亲3、兄弟姐妹4、子女5、配偶6、其它亲属7、朋友8、同学9、同事
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
        sb.append(", linkId=").append(linkId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", name=").append(name);
        sb.append(", mobile=").append(mobile);
        sb.append(", type=").append(type);
        sb.append(", createTime=").append(createTime);
        sb.append(", ext1=").append(ext1);
        sb.append(", ext2=").append(ext2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}