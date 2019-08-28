package com.shangyong.thjdq.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 推送基本信息接口返回对象
 * Created by zbb on 2019-03-14.
 */
public class PushPhaseOneVo implements Serializable {

    private static final long serialVersionUID = -8705373894337080713L;
    /**
     * 机构方订单号，进件失败时该值为null（如果机构此时没有订单号可以不传，但审批通过后需通过订单查询/推送接口传给借点钱）
     */
    @ApiModelProperty(value = "机构方订单号")
    private String order_id;
    /**
     * 审批前是否需要绑卡：1-是，0-否。如果是审批前绑卡，默认是需要，优先以机构返回结果来判断；如果是审批后绑卡，则忽略该字段。若机构返回1，表示机构需要收到用户的绑卡信息后，才会进入审批流程；若机构返回0，表示用户在机构已有绑卡信息，无需再次绑卡即可进入审批流程，注意后续流程需要推用户绑卡数据时仍须推送。
     */
    @ApiModelProperty(value = "审批前是否需要绑卡")
    private String bind_card_flag = "1";

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getBind_card_flag() {
        return bind_card_flag;
    }

    public void setBind_card_flag(String bind_card_flag) {
        this.bind_card_flag = bind_card_flag;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PushPhaseOneVo{");
        sb.append("order_id='").append(order_id).append('\'');
        sb.append(", bind_card_flag='").append(bind_card_flag).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
