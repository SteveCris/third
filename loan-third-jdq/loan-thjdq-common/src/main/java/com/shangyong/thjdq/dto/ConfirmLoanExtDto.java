package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * Created by ybds on 2019-03-22.
 */
public class ConfirmLoanExtDto implements Serializable {

    private static final long serialVersionUID = 8803382643880239591L;

    /**
     * 借点钱订单号
     */
    private String jdq_order_id;
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
     * 确认借款成功回调地址，用户确认借款成功无需再次进行确认借款(平台方会将订单置为待放款)。如果回调页面作为参数附加在跳转落地页的地址上，建议先UrlEncode，回跳时URLDecode。
     */
    private String success_return_url;
    /**
     * 确认借款操作失败回调地址，需要用户重新跳转机构h5页面进行确认借款。如果回调页面作为参数附加在跳转落地页的地址上，建议先URLEncode，回跳时URLDecode。
     */
    private String fail_return_url;

    public String getJdq_order_id() {
        return jdq_order_id;
    }

    public void setJdq_order_id(String jdq_order_id) {
        this.jdq_order_id = jdq_order_id;
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
        final StringBuilder sb = new StringBuilder("ConfirmLoanExtDto{");
        sb.append("jdq_order_id='").append(jdq_order_id).append('\'');
        sb.append(", user_name='").append(user_name).append('\'');
        sb.append(", id_number='").append(id_number).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", success_return_url='").append(success_return_url).append('\'');
        sb.append(", fail_return_url='").append(fail_return_url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
