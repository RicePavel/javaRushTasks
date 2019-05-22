package com.javarush.task.task08.task0817;

import java.util.*;

/* 
Нам повторы не нужны
*/

public class Solution {
    public static HashMap<String, String> createMap() {
        //напишите тут ваш код
        HashMap<String, String> map = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            map.put("dd" + i, "ddd");
        }
        return map;
    }

    public static void removeTheFirstNameDuplicates(Map<String, String> map) {
        //напишите тут ваш код
        Collection<String> list = map.values();
        Iterator<String> iter = list.iterator();
        Set<String> dublicates = new HashSet<>();
        while (iter.hasNext()) {
            String value = iter.next();
            if (Collections.frequency(list, value) > 1) {
                dublicates.add(value);
            }
        }
        for (String s: dublicates) {
            removeItemFromMapByValue(map, s);
        }
    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {

    }
}
