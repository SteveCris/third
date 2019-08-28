package com.shangyong.thjdq.handler;

import com.shangyong.thjdq.enums.ResponseCode;
import com.shangyong.thjdq.handler.exception.JdqSignException;
import com.shangyong.thjdq.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常捕获
 * Created by ybds on 2019-03-18.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value=Exception.class)
    public Response defaultExceptionHandler(HttpServletRequest request, Exception e) {

        if (e instanceof JdqSignException) {
            return ((JdqSignException) e).getResponseCode().toResponse();
        }

        log.error("出现异常 {}", e);
        return ResponseCode.FAILED.toResponse();
    }

    @ExceptionHandler(BindException.class)
    public Response bindExceptionHandler(HttpServletRequest request, BindException e) {
        BindingResult result = ((BindException) e).getBindingResult();
        StringBuilder sb = new StringBuilder("");
        for (ObjectError error : result.getAllErrors()) {
            sb.append("[").append(error.getDefaultMessage()).append("] ");
        }
        log.error("出现异常 {}", sb.toString());
        return ResponseCode.VALIDATOR_ERROR.toResponse(sb.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        BindingResult result = ((MethodArgumentNotValidException) e).getBindingResult();
        StringBuilder sb = new StringBuilder("");
        for (ObjectError error : result.getAllErrors()) {
            sb.append("[").append(error.getDefaultMessage()).append("] ");
        }
        log.error("出现异常 {}", sb.toString());
        return ResponseCode.VALIDATOR_ERROR.toResponse(sb.toString());
    }

}
