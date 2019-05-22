package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V v =  cache.get(key);
        if (v == null) {
            Class[] params = {key.getClass()};
            Constructor constructor = clazz.getConstructor(params);
            v = (V) constructor.newInstance(key);
            put(v);
        }
        return v;
    }

    public boolean put(V obj) {
        try {
            Method m = obj.getClass().getDeclaredMethod("getKey", null);
            m.setAccessible(true);
            K key = (K) m.invoke(obj, null);
            cache.put(key, obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int size() {
        return cache.size();
    }
}
