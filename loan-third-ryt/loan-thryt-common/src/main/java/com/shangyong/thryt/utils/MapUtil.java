package com.shangyong.thryt.utils;

import java.util.HashMap;

/**
 * 
 * @Title: MapUtil.java
 * @Package com.sharetimes.util.map
 * @Description: 链式操作 hashmap工具类
 * @author caiJunJun
 * @date 2017年6月1日
 *
 * @param <K>
 * @param <V>
 */
public class MapUtil<K, V> extends HashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8490637813400734425L;

	public static <K, V> MapUtil<K, V> build() {
		return new MapUtil<>();
	}

	public MapUtil<K, V> lput(K key, V value) {
		super.put(key, value);
		return this;
	}

}
