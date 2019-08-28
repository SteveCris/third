package com.shangyong.thbase.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangyong.thbase.enums.CoreResult;
import com.shangyong.thbase.enums.CoreResultEnum;
import com.shangyong.thbase.exception.CalfException;

@ControllerAdvice
public class ExceptionHandle {

	private Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public CoreResult<Object> handle(Exception e) {
		if (e instanceof CalfException) {
			CalfException calfException = (CalfException) e;
			CoreResultEnum coreResultEnum = calfException.getCoreResultEnum();
			return coreResultEnum.with(calfException.getBody()).withMessage(e.getMessage());
		} else if (e instanceof MethodArgumentNotValidException) {
			StringBuilder sb = new StringBuilder("");
			for (ObjectError error : ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors()) {
				sb.append("[").append(error.getDefaultMessage()).append("] ");
			}
			return CoreResultEnum.PARAM_ERROR.with().withDynaMessage(sb.toString());
		} else if (e instanceof MissingServletRequestParameterException) {
			return CoreResultEnum.PARAM_MISSING_ERROR.with();
		} else if (e instanceof NullPointerException) {
			logger.error("[空指针异常]", e);
			return CoreResultEnum.NULL_ERROR.with();
		} else {
			logger.error("[服务器开小差]", e);
			return CoreResultEnum.ERROR.with();
		}
	}

}
