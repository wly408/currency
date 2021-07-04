package com.currency.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class ObjectUtil {
    /**
     * 反射执行方法
     *
     * @param obj
     * @param methodName
     * @param param
     * @param paramValue
     * @return
     */
    public static Object invokeSimple(Object obj, String methodName, Class param, Object paramValue) {
        if (obj == null) {
            return null;
        }
        try {
            Method method = null;
            if (param != null) {
                method = obj.getClass().getMethod(methodName, param);
                if (method != null) {
                    return method.invoke(obj, paramValue);
                }
            } else {
                method = obj.getClass().getMethod(methodName);
                if (method != null) {
                    return method.invoke(obj);
                }
            }


        } catch (Exception e) {



        }
        return null;

    }

    public static void copyBean(Object source, Object target) {

        if (source == null) {
            target = null;
            return;
        }
        if (target == null) {
            return;
        }
        BeanUtils.copyProperties(source, target);

    }
    public static <T> T copy(Object source, Class<T> target) {
        if(source==null){
            return null;
        }
        try {
            T newInstance = target.newInstance();
            BeanUtils.copyProperties(source, newInstance);
            return newInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, K> List<K> copyList(List<T> source, Class<K> target) {

        if (null == source || source.isEmpty()) {
            return Collections.emptyList();
        }
        return source.stream().map(e -> copy(e, target)).collect(Collectors.toList());
    }



}
