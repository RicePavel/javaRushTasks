package com.javarush.task.task10.task1019;

import java.io.*;
import java.util.HashMap;

/* 
Функциональности маловато!
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Integer> map = new HashMap<>();

        while (true) {
            String str = reader.readLine();
            if (str.isEmpty()) {
                break;
            }
            int id = Integer.parseInt(str);
            String name = reader.readLine();
            map.put(name, id);
            if (name.isEmpty()) {
                break;
            }
        }

        for (String key: map.keySet()) {
            System.out.println(map.get(key) + " " + key);
        }
    }

}
