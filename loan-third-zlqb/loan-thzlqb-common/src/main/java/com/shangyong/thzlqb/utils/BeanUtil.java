package com.shangyong.thzlqb.utils;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 不同对象，属性的互相转化类
 */
public class BeanUtil {

    public static <S, T> T do2bo(S source, Class<T> targetClass) {
        if (null == source) {
            return null;
        }
        T result;
        try {
            result = targetClass.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            throw new IllegalArgumentException("对象copy失败，请检查相关module", e);
        }
        return result;
    }

    public static <S, T> List<T> do2bo(List<S> source, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>(source.size());
        for (S obj : source) {
            result.add(do2bo(obj, targetClass));
        }
        return result;
    }
    public static <S, T> void do2bo(S source, T target) {
        if (null == source) {
            return ;
        }
        try {
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            throw new IllegalArgumentException("对象copy失败，请检查相关module", e);
        }
    }

    public static <S, T> List<T> do2bo4List(List<S> source, Class<T> targetClass) {
        List<T> result = Lists.newArrayList();
        if(CollectionUtils.isEmpty(source)){
            return result;
        }
        for (S obj : source) {
            result.add(do2bo(obj, targetClass));
        }
        return result;
    }

    public static <V> boolean isPrimitiveType(V v) {
        Class<?> vClass = v.getClass();
        return vClass.equals(String.class) || vClass.equals(Integer.class) || vClass.equals(Byte.class)
                || vClass.equals(Long.class) || vClass.equals(Double.class) || vClass.equals(Float.class)
                || vClass.equals(Character.class) || vClass.equals(Short.class) || vClass.equals(BigDecimal.class)
                || vClass.equals(BigInteger.class) || vClass.equals(Boolean.class) || vClass.equals(Date.class)
                || vClass.equals(Timestamp.class) || vClass.isPrimitive();
    }
}
