package com.shangyong.thjdq.enums;


import com.shangyong.thjdq.vo.Response;

/**
 * Created by zbb on 2019-03-12.
 */
public enum  ResponseCode {

    OK(0, "成功"),
    FAILED(-2,"系统发生异常，请稍后再试"),
    REPEAT_REQUEST(801,"重复请求"),
    BUSINESS_DATA_NOT_EXIST(-2,"系统发生异常，请稍后再试"),
    VALIDATOR_ERROR(900,"参数异常"),
    IP_NOT_ON_THE_WHITE_LIST(-2,"ip在不白名单内，无法访问");

    private Integer code;

    private String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseCode setMsg(String msg){
        this.msg = msg;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Response toResponse() {
        return new Response(code, msg);
    }

    public Response toResponse(String msg) {
        return new Response(code, msg);
    }

    public <T> Response<T> toResponse(T data) {
        return new Response<>(code, msg, data);
    }

    public <T> Response<T> toResponse(T data, String msg) {
        return new Response<>(code, msg, data);
    }
}
