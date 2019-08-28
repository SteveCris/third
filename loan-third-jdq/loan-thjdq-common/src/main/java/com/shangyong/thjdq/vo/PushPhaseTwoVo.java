package com.shangyong.thjdq.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 推送补充信息接口返回对象
 * Created by zbb on 2019-03-14.
 */
public class PushPhaseTwoVo implements Serializable {

    private static final long serialVersionUID = -8705373894337080713L;
    /**
     * 机构方订单号，进件失败时该值为null（如果机构此时没有订单号可以不传，但审批通过后需通过订单查询/推送接口传给借点钱）
     */
    @ApiModelProperty(value = "机构方订单号")
    private String order_id;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PushPhaseTwoVo{");
        sb.append("order_id='").append(order_id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
