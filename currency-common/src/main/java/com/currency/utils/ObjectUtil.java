package com.currency.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        if (source == null) {
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
            return new ArrayList<>();
        }
        return source.stream().map(e -> copy(e, target)).collect(Collectors.toList());
    }

    public static <T> List<T> mapToList(List<Map<String, Object>> mapList, Class<T> target) {
        List<T> list = null;
        if (!CollectionUtils.isEmpty(mapList)) {
            list = new ArrayList<>();
            for (Map<String, Object> result : mapList) {
                if (result != null) {
                    list.add(mapToBean(result, target));
                }
            }
        }
        return list;

    }

    public static <T> T mapToBean(Map<String, Object> map, Class<T> target) {
        if (map != null) {
            String resultJson = JSON.toJSONString(map);
            T t = JSON.parseObject(resultJson, target);
            return t;
        }
        return null;

    }

    public static <T> T mapToBean(List<Map<String, Object>> mapList, Class<T> target) {
        if (!CollectionUtils.isEmpty(mapList)) {
            return mapToBean(mapList.get(0), target);
        }
        return null;

    }


}
