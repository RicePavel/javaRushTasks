package com.javarush.task.task08.task0802;
import java.util.*;
/* 
HashMap из 10 пар
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {

        HashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("арбуз", "ягода");
        map.put("банан ", "трава");
        map.put("вишня", "ягода");
        map.put("груша", "фрукт");
        map.put("дыня", "овощ");
        map.put("ежевика", "куст");
        map.put("жень-шень", "корень");
        map.put("земляника", "ягода");
        map.put("ирис", "цветок");
        map.put("картофель", "клубень");

        // Вывести на экран result
        printMap(map);
    }

    public static <T, V> void printMap(Map<T, V> map) {
        for (T t: map.keySet()) {
            System.out.println(t + " - " + map.get(t));
        }
    }
}
