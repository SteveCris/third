package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * 无源比对结果数据
 * Created by zhengbb on 2019-05-21.
 */
public class FaceResultDto implements Serializable {

    private static final long serialVersionUID = 2815159400703625444L;

    /**
     * 接口请求耗时
     */
    private long time_used;
    /**
     * 比对结果数据
     */
    private ResultRef1Dto result_ref1;
    /**
     * 请求唯一标识
     */
    private String request_id;

    public long getTime_used() {
        return time_used;
    }

    public void setTime_used(long time_used) {
        this.time_used = time_used;
    }

    public ResultRef1Dto getResult_ref1() {
        return result_ref1;
    }

    public void setResult_ref1(ResultRef1Dto result_ref1) {
        this.result_ref1 = result_ref1;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FaceResultDto{");
        sb.append("time_used=").append(time_used);
        sb.append(", result_ref1=").append(result_ref1);
        sb.append(", request_id=").append(request_id);
        sb.append('}');
        return sb.toString();
    }
}
