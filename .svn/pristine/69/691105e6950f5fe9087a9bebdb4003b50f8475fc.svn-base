package com.shangyong.thzlqb.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shangyong.common.entity.RestResult;
import com.shangyong.loan.ext.util.PropertiesUtil;
import com.shangyong.thzlqb.enums.CoreResultEnum;

/**
 * 属性操作类
 * @author caijunjun
 * @date 2019年8月20日
 */
@RestController
@RequestMapping("/properties")
public class PropertiesController {

	@RequestMapping(value = "/cachePut", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RestResult<Void> put(@RequestParam("checkToken") String checkToken, @RequestBody Cache cache) {
		if (PropertiesUtil.put(checkToken, cache.getKey(), cache.getValue())) {
			return CoreResultEnum.SUCCESS.with();
		} else {
			return CoreResultEnum.FAILURE.with();
		}
	}

	static class Cache {

		private String key;

		private String value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Cache [key=");
			builder.append(key);
			builder.append(", value=");
			builder.append(value);
			builder.append("]");
			return builder.toString();
		}

	}
}
