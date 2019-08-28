package com.shangyong.thjdq.event;

import com.shangyong.thjdq.dto.PushPhaseOneDto;

import java.io.Serializable;

/**
 * 用户一推信息入库事件
 * Created by ybds on 2019-03-15.
 */
public class PushPhaseOneEvent implements Serializable{

    private static final long serialVersionUID = 4906293807826036662L;


    private PushPhaseOneDto pushPhaseOneDto;
    private String jdqOrderId;

    public PushPhaseOneDto getPushPhaseOneDto() {
        return pushPhaseOneDto;
    }

    public void setPushPhaseOneDto(PushPhaseOneDto pushPhaseOneDto) {
        this.pushPhaseOneDto = pushPhaseOneDto;
    }

    public String getJdqOrderId() {
        return jdqOrderId;
    }

    public void setJdqOrderId(String jdqOrderId) {
        this.jdqOrderId = jdqOrderId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PushPhaseOneEvent{");
        sb.append("pushPhaseOneDto=").append(pushPhaseOneDto);
        sb.append(", jdqOrderId='").append(jdqOrderId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
