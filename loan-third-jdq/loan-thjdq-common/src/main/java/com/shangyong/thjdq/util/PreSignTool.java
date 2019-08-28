package com.shangyong.thjdq.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * PreSignTool
 *
 * @author hejiangshan on 2018年12月19日
 * @version 1.0
 */
public class PreSignTool {

    /**
     * 生成原始待签名字符串，会忽视类属性，忽视为null的字段，或空字符串
     */
    public static <T> String buildToSignString(Class<T> clz, T t) throws IllegalAccessException {
        Map<String, Object> signMap = new TreeMap<>(new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                return obj1.compareTo(obj2);
            }
        });
        for (Field field : clz.getDeclaredFields()) {
            field.setAccessible(true);
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if ("sign".equals(field.getName())) {
                continue;
            }
            final Object val = field.get(t);
            if (val == null) {
                continue;
            }
            if (val instanceof String && ((String) val).length() == 0) {
                continue;
            }
            signMap.put(field.getName(), val);
        }
        //        System.out.println(signMap);
        StringBuilder sb = new StringBuilder();
        for (String key : signMap.keySet()) {
            sb.append(key).append("=").append(signMap.get(key)).append("&");
        }
        if (sb.lastIndexOf("&") != -1) {
            sb.deleteCharAt(sb.lastIndexOf("&"));
        }
        return sb.toString();
    }

    /**
     * 生成原始待签名字符串，忽视为null的字段，或空字符串
     */
    public static String buildToSignString(Map<String, ?> params) {
        Map<String, Object> signMap = new TreeMap<>(new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                return obj1.compareTo(obj2);
            }
        });
        for (String key : params.keySet()) {
            if ("sign".equals(key)) {
                continue;
            }
            final Object val = params.get(key);
            if (val == null) {
                continue;
            }
            if (val instanceof String && ((String) val).length() == 0) {
                continue;
            }
            signMap.put(key, val);
        }
        StringBuilder sb = new StringBuilder();
        for (String key : signMap.keySet()) {
            sb.append(key).append("=").append(signMap.get(key)).append("&");
        }
        final int lastIndexOf = sb.lastIndexOf("&");
        if (lastIndexOf != -1) {
            sb.deleteCharAt(lastIndexOf);
        }
        return sb.toString();
    }

}
