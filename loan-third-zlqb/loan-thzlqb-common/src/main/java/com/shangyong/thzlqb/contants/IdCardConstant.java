package com.shangyong.thzlqb.contants;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:ZlqbIdCardConstant </br>
 * @Description: TODO </br>
 * @Author:童晶继 tongjingji01@gmail.com </br>
 * @Date:2019年7月22日
 * @version:1.0
 */
public class IdCardConstant {

	private static Map<String, String> initMap = new HashMap<>();

	static {
		initMap.put("3503", "莆田");
		initMap.put("3210", "盐城");
		initMap.put("1523", "兴安");
		initMap.put("1403", "大同");
		initMap.put("5325", "红河哈尼");
		initMap.put("5104", "攀枝花");
		initMap.put("5201", "六盘水");
		initMap.put("2108", "营口");
		initMap.put("5224", "毕节");
		initMap.put("5134", "凉山");
		initMap.put("4501", "南宁");
		initMap.put("6501", "新疆");
		initMap.put("6502", "新疆");
		initMap.put("6521", "新疆");
		initMap.put("6522", "新疆");
		initMap.put("6523", "新疆");
		initMap.put("6524", "新疆");
		initMap.put("6525", "新疆");
		initMap.put("6526", "新疆");
		initMap.put("6543", "新疆");
		initMap.put("6590", "新疆");
		initMap.put("6527", "新疆");
		initMap.put("6528", "新疆");
		initMap.put("6529", "新疆");
		initMap.put("6530", "新疆");
		initMap.put("6531", "新疆");
		initMap.put("6532", "新疆");
		initMap.put("6540", "新疆");
		initMap.put("6541", "新疆");
		initMap.put("6542", "新疆");

		initMap.put("5401", "西藏");
		initMap.put("5421", "西藏");
		initMap.put("5422", "西藏");
		initMap.put("5423", "西藏");
		initMap.put("5424", "西藏");
		initMap.put("5425", "西藏");
		initMap.put("5426", "西藏");
		initMap.put("5427", "西藏");

	}

	public static boolean isExistArea(String code) {
		return initMap.get(code) != null;
	}
}
