package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int size = 0;
        for (List list: map.values()) {
            size += list.size();
        }
        return size;
    }

    @Override
    public V put(K key, V value) {
        V previous = null;
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<V>());
        }
        List<V> list = map.get(key);
        if (list.size() > 0) {
            previous = list.get(list.size() - 1);
        }
        if (list.size() == repeatCount) {
            list.remove(0);
        }
        list.add(value);
        return previous;
    }

    @Override
    public V remove(Object key) {
        V deleted = null;
        if (map.containsKey(key)) {
            List<V> list = map.get(key);
            deleted = list.remove(0);
            if (list.isEmpty()) {
                map.remove(key);
            }
        }
        return deleted;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (List<V> l: map.values()) {
            values.addAll(l);
        }
        return values;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}