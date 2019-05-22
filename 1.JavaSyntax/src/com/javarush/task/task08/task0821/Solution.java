package com.javarush.task.task08.task0821;

import java.util.HashMap;
import java.util.Map;

/* 
Однофамильцы и тёзки
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = createPeopleList();
        printPeopleList(map);
    }

    public static Map<String, String> createPeopleList() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "Иван Бобров");
        map.put("2", "Иван Бобров");
        map.put("3", "Иван Бобров");
        map.put("3", "Иван Бобров");
        map.put("5", "Иван Бобров");
        map.put("6", "Иван Бобров");
        map.put("7", "Иван Бобров");
        map.put("8", "Иван Бобров");
        map.put("9", "Иван Бобров");
        map.put("10", "Иван Бобров");
        return map;
    }

    public static void printPeopleList(Map<String, String> map) {
        for (Map.Entry<String, String> s : map.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}
