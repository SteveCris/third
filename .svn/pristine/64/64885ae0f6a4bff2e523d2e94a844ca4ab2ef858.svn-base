package com.shangyong.thjdq.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户检测返回对象
 * Created by zbb on 2019-03-13.
 */
public class CheckUserVo implements Serializable {

    private static final long serialVersionUID = 6535010454037476909L;
    /**
     * 是否可借，0=否，1=是
     */
    @ApiModelProperty(value = "是否可借")
    private String if_can_loan;
    /**
     * 用户类型：0-借点钱老用户 (定义：由借点钱渠道导流到合作方的用户，非第一次申请) 1-新用户 （定义：由借点钱渠道导流到合作方的用户，第一次申请） 2-合作方老用户 （定义：该用户为机构原有用户，且非借点钱渠道注册）
     */
    @ApiModelProperty(value = "用户类型")
    private String user_type;
    /**
     * 用户在指定日期后才可以借款，格式：yyyy-MM-dd 注：if_can_loan为0表示用户不能申请该合作机构的产品，原因有以下情况分别处理： A：命中合作机构黑名单的情况，can_loan_time传空即可 B：审核失败后没有过隔离期的情况，can_loan_time需要传该用户可以申请的时间，精确到天即可例如：2017-02-29
     */
    @ApiModelProperty(value = "用户在指定日期后才可以借款")
    private String can_loan_time;
    /**
     * 不可借原因码，if_can_loan为0时,则该字段必须有值，值类型如下： 1=黑名单 2=在途订单 3=隔离期 4=非借点钱渠道老用户 5=借点钱渠道老用户
     */
    @ApiModelProperty(value = "不可借原因码")
    private String reason;

    public String getIf_can_loan() {
        return if_can_loan;
    }

    public void setIf_can_loan(String if_can_loan) {
        this.if_can_loan = if_can_loan;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getCan_loan_time() {
        return can_loan_time;
    }

    public void setCan_loan_time(String can_loan_time) {
        this.can_loan_time = can_loan_time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CheckUserVo{");
        sb.append("if_can_loan='").append(if_can_loan).append('\'');
        sb.append(", user_type='").append(user_type).append('\'');
        sb.append(", can_loan_time='").append(can_loan_time).append('\'');
        sb.append(", reason='").append(reason).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
