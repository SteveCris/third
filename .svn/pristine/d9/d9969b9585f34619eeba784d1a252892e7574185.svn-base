package com.shangyong.thbase.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class ThBankMatch implements Serializable {
    //主键
    @ApiModelProperty(value = "主键")
    private String matchId;

    //应用id
    @ApiModelProperty(value = "应用id")
    private String appid;

    //银行代码
    @ApiModelProperty(value = "银行代码")
    private String bankCode;

    //银行名称
    @ApiModelProperty(value = "银行名称")
    private String bankName;

    //卡号前缀
    @ApiModelProperty(value = "卡号前缀")
    private String cardNoPrefix;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * 设置主键
     *
     * @param matchId 主键
     */
    public void setMatchId(String matchId) {
        this.matchId = matchId == null ? null : matchId.trim();
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
     * 获取银行代码
     *
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * 设置银行代码
     *
     * @param bankCode 银行代码
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    /**
     * 获取银行名称
     *
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 设置银行名称
     *
     * @param bankName 银行名称
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * 获取卡号前缀
     *
     */
    public String getCardNoPrefix() {
        return cardNoPrefix;
    }

    /**
     * 设置卡号前缀
     *
     * @param cardNoPrefix 卡号前缀
     */
    public void setCardNoPrefix(String cardNoPrefix) {
        this.cardNoPrefix = cardNoPrefix == null ? null : cardNoPrefix.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", matchId=").append(matchId);
        sb.append(", appid=").append(appid);
        sb.append(", bankCode=").append(bankCode);
        sb.append(", bankName=").append(bankName);
        sb.append(", cardNoPrefix=").append(cardNoPrefix);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}