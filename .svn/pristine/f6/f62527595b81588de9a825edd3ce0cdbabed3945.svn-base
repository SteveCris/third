package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * 推送基本信息接口DTO对象
 * Created by zbb on 2019-03-14.
 */
public class PushPhaseOneDto implements Serializable {

    private static final long serialVersionUID = -1743809035435430816L;
    /**
     * 用户基本信息id
     */
    private String userInfoId;

    /**
     * 用户基本信息
     */
    private UserInfoDto user_info;
    /**
     * 贷款信息
     */
    private LoanInfoDto loan_info;
//    /**
//     * 运营商标准数据(借点钱统一格式)
//     */
//    private QjqTelecom telecom;
    /**
     * 运营商原始数据
     */
    private MoxieTelecomDto moxie_telecom;
//    /**
//     * 	运营商分析报告数据
//     */
//    private QjqMoxieTelecomReport moxie_telecom_report;
    /**
     * 无源比对结果数据
     */
    private String faceResult;
    /**
     * 有源比对结果数据
     */
    private String faceResultActive;
    /**
     * 借点钱订单号
     */
    private String jdq_order_id;
    /**
     * 是否已同意协议，1-是，0-否
     */
    private int agree_contract_flag;
    
    /**
     * 用户申请时所用APP名称，如“借点钱”、“快贷宝”
     */
    private String app_name;

    public UserInfoDto getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoDto user_info) {
        this.user_info = user_info;
    }

    public LoanInfoDto getLoan_info() {
        return loan_info;
    }

    public void setLoan_info(LoanInfoDto loan_info) {
        this.loan_info = loan_info;
    }

    public MoxieTelecomDto getMoxie_telecom() {
        return moxie_telecom;
    }

    public void setMoxie_telecom(MoxieTelecomDto moxie_telecom) {
        this.moxie_telecom = moxie_telecom;
    }

    public String getFaceResult() {
        return faceResult;
    }

    public void setFaceResult(String faceResult) {
        this.faceResult = faceResult;
    }

    public String getFaceResultActive() {
        return faceResultActive;
    }

    public void setFaceResultActive(String faceResultActive) {
        this.faceResultActive = faceResultActive;
    }

    public String getJdq_order_id() {
        return jdq_order_id;
    }

    public void setJdq_order_id(String jdq_order_id) {
        this.jdq_order_id = jdq_order_id;
    }

    public int getAgree_contract_flag() {
        return agree_contract_flag;
    }

    public void setAgree_contract_flag(int agree_contract_flag) {
        this.agree_contract_flag = agree_contract_flag;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }
    

    public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PushPhaseOneDto{");
        sb.append("userInfoId=").append(userInfoId);
        sb.append(", user_info=").append(user_info);
        sb.append(", loan_info=").append(loan_info);
        // 不打印运营商
//        sb.append(", moxie_telecom=").append(moxie_telecom);
//        sb.append(", faceResult=").append(faceResult);
//        sb.append(", faceResultActive=").append(faceResultActive);
        sb.append(", jdq_order_id=").append(jdq_order_id);
        sb.append(", agree_contract_flag=").append(agree_contract_flag);
        sb.append(", app_name=").append(app_name);
        sb.append('}');
        return sb.toString();
    }
}
