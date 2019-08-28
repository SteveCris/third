package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * Created by ybds on 2019-03-22.
 */
public class RepaymentExtDto implements Serializable {

    private static final long serialVersionUID = 1713760627949026662L;

    /**
     * 借点钱订单号
     */
    private String jdq_order_id;
    /**
     * 还款操作处理成功回调地址(并非指用户扣款成功，单指用户扣款这一动作操作完成，具体扣款结果以推送通知为准)。如果回调页面作为参数附加在跳转落地页的地址上，建议先URLEncode，回跳时URLDecode。
     */
    private String success_return_url;
    /**
     * 还款操作处理失败回调地址(单指用户扣款这一动作操作失败)。如果回调页面作为参数附加在跳转落地页的地址上，建议先URLEncode，回跳时URLDecode。
     */
    private String fail_return_url;

    public String getJdq_order_id() {
        return jdq_order_id;
    }

    public void setJdq_order_id(String jdq_order_id) {
        this.jdq_order_id = jdq_order_id;
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
        final StringBuilder sb = new StringBuilder("RepaymentExtDto{");
        sb.append("jdq_order_id='").append(jdq_order_id).append('\'');
        sb.append(", success_return_url='").append(success_return_url).append('\'');
        sb.append(", fail_return_url='").append(fail_return_url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
