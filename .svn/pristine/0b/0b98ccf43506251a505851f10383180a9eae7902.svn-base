package com.shangyong.thjdq.event;

import com.shangyong.thjdq.dto.PushPhaseTwoDto;

import java.io.Serializable;

/**
 * 用户二推信息入库事件
 * Created by ybds on 2019-03-19.
 */
public class PushPhaseTwoEvent implements Serializable{

    private static final long serialVersionUID = 2930398996181084869L;

    private PushPhaseTwoDto pushPhaseTwoDto;
    private String jdqOrderId;

    public PushPhaseTwoDto getPushPhaseTwoDto() {
        return pushPhaseTwoDto;
    }

    public void setPushPhaseTwoDto(PushPhaseTwoDto pushPhaseTwoDto) {
        this.pushPhaseTwoDto = pushPhaseTwoDto;
    }

    public String getJdqOrderId() {
        return jdqOrderId;
    }

    public void setJdqOrderId(String jdqOrderId) {
        this.jdqOrderId = jdqOrderId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PushPhaseTwoEvent{");
        sb.append("pushPhaseTwoDto=").append(pushPhaseTwoDto);
        sb.append(", jdqOrderId='").append(jdqOrderId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
