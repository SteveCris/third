package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * 跳转机构绑卡接口
 * Created by ybds on 2019-03-22.
 */
public class BindCardExtDto implements Serializable {

    private static final long serialVersionUID = -3754308116602927102L;

    /**
     * 借点钱订单号
     */
    private String jdq_order_id;
    /**
     * 银行卡类型：1-借记卡，2-信用卡
     */
    private int card_type;
    /**
     * 绑卡类型：1-绑定新卡，2-选定旧卡
     */
    private int bind_type;
    /**
     * 银行卡号(bind_type=2时必传)
     */
    private String card_no;
    /**
     * 银行code(bind_type=2时必传)
     */
    private String bank_code;
    /**
     * 用户姓名
     */
    private String user_name;
    /**
     * 用户身份证号
     */
    private String id_number;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 绑卡操作成功(仅指绑卡操作成功，绑卡结果通过"已绑银行卡列表接口和订单状态查询/推送接口告知平台")。如果回调页面作为参数附加在跳转落地页的地址上，建议先URLEncode，回跳时URLDecode。
     */
    private String success_return_url;
    /**
     * 绑卡操作失败(仅指绑卡操作失败)。如果回调页面作为参数附加在跳转落地页的地址上，建议先UrlEncode，回跳时URLDecode。
     */
    private String fail_return_url;

    public String getJdq_order_id() {
        return jdq_order_id;
    }

    public void setJdq_order_id(String jdq_order_id) {
        this.jdq_order_id = jdq_order_id;
    }

    public int getCard_type() {
        return card_type;
    }

    public void setCard_type(int card_type) {
        this.card_type = card_type;
    }

    public int getBind_type() {
        return bind_type;
    }

    public void setBind_type(int bind_type) {
        this.bind_type = bind_type;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSuccess_return_url() {
        return success_return_url;
    }

    public void setSuccess_return_url(String success_return_url) {
        this.success_return_url = success_return_url;
    }

    public String getFail_return_url() {
        return fail_return_url;
    }

    public void setFail_return_url(String fail_return_url) {
        this.fail_return_url = fail_return_url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BindCardExtDto{");
        sb.append("jdq_order_id='").append(jdq_order_id).append('\'');
        sb.append(", card_type=").append(card_type);
        sb.append(", bind_type=").append(bind_type);
        sb.append(", card_no='").append(card_no).append('\'');
        sb.append(", bank_code='").append(bank_code).append('\'');
        sb.append(", user_name='").append(user_name).append('\'');
        sb.append(", id_number='").append(id_number).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", success_return_url='").append(success_return_url).append('\'');
        sb.append(", fail_return_url='").append(fail_return_url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
