package com.shangyong.thorder.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderCreditRule implements Serializable {
    //规则主键id
    @ApiModelProperty(value = "规则主键id")
    private String ruleId;

    //订单id
    @ApiModelProperty(value = "订单id")
    private String orderId;

    //应用id
    @ApiModelProperty(value = "应用id")
    private String appid;

    //选择金额
    @ApiModelProperty(value = "选择金额")
    private BigDecimal creditLine;

    //放款规则参数
    @ApiModelProperty(value = "放款规则参数")
    private String rule;

    //场景规则参数
    @ApiModelProperty(value = "场景规则参数")
    private String sceneRule;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //扩展字段
    @ApiModelProperty(value = "扩展字段")
    private String ext;

    //扩展字段2
    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    private static final long serialVersionUID = 1L;

    /**
     * 获取规则主键id
     *
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置规则主键id
     *
     * @param ruleId 规则主键id
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
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
     * 获取选择金额
     *
     */
    public BigDecimal getCreditLine() {
        return creditLine;
    }

    /**
     * 设置选择金额
     *
     * @param creditLine 选择金额
     */
    public void setCreditLine(BigDecimal creditLine) {
        this.creditLine = creditLine;
    }

    /**
     * 获取放款规则参数
     *
     */
    public String getRule() {
        return rule;
    }

    /**
     * 设置放款规则参数
     *
     * @param rule 放款规则参数
     */
    public void setRule(String rule) {
        this.rule = rule == null ? null : rule.trim();
    }

    /**
     * 获取场景规则参数
     *
     */
    public String getSceneRule() {
        return sceneRule;
    }

    /**
     * 设置场景规则参数
     *
     * @param sceneRule 场景规则参数
     */
    public void setSceneRule(String sceneRule) {
        this.sceneRule = sceneRule == null ? null : sceneRule.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ruleId=").append(ruleId);
        sb.append(", orderId=").append(orderId);
        sb.append(", appid=").append(appid);
        sb.append(", creditLine=").append(creditLine);
        sb.append(", rule=").append(rule);
        sb.append(", sceneRule=").append(sceneRule);
        sb.append(", createTime=").append(createTime);
        sb.append(", ext=").append(ext);
        sb.append(", ext2=").append(ext2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}