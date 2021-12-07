package com.currency.common.bean;

import java.util.HashMap;
import java.util.Map;

public class NativeSqlParam<K, V> extends HashMap<K, V> {

    private NativeSqlParam(){

    }
    public static <K, V> NativeSqlParam newInstance(){
        return new NativeSqlParam();
    }
    public static <K, V> NativeSqlParam newInstance(K key, V value){
        return new NativeSqlParam().add(key,value);
    }

    public NativeSqlParam add(K key, V value) {
        this.put(key, value);
        return this;
    }

    public NativeSqlParam addAll(Map<? extends K, ? extends V> m) {
        this.putAll(m);
        return this;
    }

}
