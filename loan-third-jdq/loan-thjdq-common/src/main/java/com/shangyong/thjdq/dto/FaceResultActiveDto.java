package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * 有源比对结果数据
 * Created by zhengbb on 2019-05-22.
 */
public class FaceResultActiveDto implements Serializable {

    private static final long serialVersionUID = 8646682929318323956L;

    /**
     * 接口请求耗时
     */
    private long time_used;

    private IdExceptionsDto id_exceptions;
    /**
     * 比对结果数据
     */
    private ResultFaceidDto result_faceid;
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

    public IdExceptionsDto getId_exceptions() {
        return id_exceptions;
    }

    public void setId_exceptions(IdExceptionsDto id_exceptions) {
        this.id_exceptions = id_exceptions;
    }

    public ResultFaceidDto getResult_faceid() {
        return result_faceid;
    }

    public void setResult_faceid(ResultFaceidDto result_faceid) {
        this.result_faceid = result_faceid;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FaceResultActiveDto{");
        sb.append("time_used=").append(time_used);
        sb.append(", id_exceptions=").append(id_exceptions);
        sb.append(", result_faceid=").append(result_faceid);
        sb.append(", request_id=").append(request_id);
        sb.append('}');
        return sb.toString();
    }
}
