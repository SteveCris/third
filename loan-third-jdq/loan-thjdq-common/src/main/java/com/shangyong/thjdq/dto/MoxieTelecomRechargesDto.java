package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * Created by ybds on 2019-03-14.
 */
public class MoxieTelecomRechargesDto implements Serializable {

    private static final long serialVersionUID = 7230757861972188511L;
    private String details_id;

    private int amount;

    private String recharge_time;

    private String type;

    public String getDetails_id() {
        return details_id;
    }

    public void setDetails_id(String details_id) {
        this.details_id = details_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRecharge_time() {
        return recharge_time;
    }

    public void setRecharge_time(String recharge_time) {
        this.recharge_time = recharge_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
