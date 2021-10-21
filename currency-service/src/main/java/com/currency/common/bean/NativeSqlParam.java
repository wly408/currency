package com.currency.common.bean;

import java.util.HashMap;
import java.util.Map;

public class NativeSqlParam<K, V> extends HashMap<K, V> {


    public NativeSqlParam putVal(K key, V value) {
        this.put(key, value);
        return this;
    }

    public NativeSqlParam putAllVal(Map<? extends K, ? extends V> m) {
        this.putAll(m);
        return this;
    }
}
