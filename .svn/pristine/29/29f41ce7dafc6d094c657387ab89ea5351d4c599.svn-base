package com.shangyong.thzlqb;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.loan.ext.util.JacksonUtil;
import com.shangyong.thzlqb.zlqb.utils.AesUtil;

public class TestSign {

	public static void main(String[] args) {
		String str="OyQAtKUTMabN3R8SSIZWtN9QOftNfjSo3iEdzZqWHuSyA8m/n8VNXuU7PHRSv2zT4+JwokjzsDZCA7CP1WRDgGEEpBaCEgMVI5x/sqhUZJ3Dint6MP/MyJHz4iyHnBDbfijQPmppNJUvWAbt6yIgE+IHLvPwrYrt9FsSmOZ13fKMc63L08VtBVRWBh7ooanmh5aohBu0v+C1fmxUk74RfOvbJl9NS6GRMjMreUsVntW8Q3mEbuItcQFh26AmIR7F9jNSP7PZfmb1V2IpS351Hr0uSxhRdPdkd+FpO27MglD5BgEGloWIiTKpQTMG25o2Xru3NVCXnoWuLNsIOMnEuMbSC3OGObwudqLuBaD1Z3h0bnVz7iLIfSWQyv5AWMVe8BQl0UYCiKtjLENhArP+vEqzl268lfBmjFUgvUO6f3FAa0vkbPraIqVXa5hQfCwB/zGFBimgrP7yY4aZ8OQwvkV2GR4/n25KLtIi8SreAtJcQcgPJ9yKA7tqtGTFSW59BPUHXCZCff95tSveqdbJMjiAyAvCEGEQIgR6BRhkbmQD35BmlHs3bEw3ZCxscbtqMfdM/GRpG2BrKK9ge4COmZd883chi5cytfGbUbQeQoU=";
		
		JacksonUtil.build().parseFrom(AesUtil.decrypt(str, "f9b61e9851ffac21"), ObjectNode.class);
		
		System.out.println(AesUtil.decrypt(str, "f9b61e9851ffac21"));
		
		
		/*String appId = "F6J422G6FD51A9C";
		String aeskey = "7fv5WOT6HstUZUfX";
		// 业务请求参数处理
		Map<String, Object> map = new HashMap<>();
		map.put("name", "张三");
		map.put("idCard", "429006198809252423");
		map.put("phone", "18571861408");
		// request 业务参数加密
		String request = AesUtil.encrypt(JacksonUtil.build().parseToJsonString(map), aeskey);
		System.out.println(request);
		String response = AesUtil.decrypt(request, aeskey);
		System.out.println(response);*/
	}
}
