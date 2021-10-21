package com.currency.common.util;

import com.currency.common.bean.NativeSqlParam;

import java.util.Map;

public final class NativeSqlParamUitl {
    public static NativeSqlParam<String, Object> createParam() {
        return new NativeSqlParam<>();
    }

    public static NativeSqlParam<String, Object> createParam(String key, Object value) {
        return createParam().putVal(key,value);
    }
    public static NativeSqlParam<String, Object> createParam(Map<String,Object> params) {
        return createParam().putAllVal(params);
    }


}
