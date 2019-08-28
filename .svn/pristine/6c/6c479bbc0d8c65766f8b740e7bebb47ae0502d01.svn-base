package com.shangyong.thjdq.handler.exception;

import com.shangyong.thjdq.enums.ResponseCode;
import com.shangyong.thjdq.vo.Response;

/**
 * Created by ybds on 2019-03-18.
 */
public class JdqSignException extends RuntimeException {

    private static final long serialVersionUID = -2471525506801402126L;

    private ResponseCode responseCode;

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public JdqSignException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.responseCode = responseCode;
    }

    public JdqSignException(String message,ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public JdqSignException(String message, Throwable cause,ResponseCode responseCode) {
        super(message, cause);
        this.responseCode = responseCode;
    }

    public JdqSignException(Throwable cause, ResponseCode responseCode) {
        super(cause);
        this.responseCode = responseCode;
    }

}
