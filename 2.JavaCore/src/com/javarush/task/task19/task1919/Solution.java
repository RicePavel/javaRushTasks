package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            Map<String, Double> map = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(" ");
                String name = arr[0];
                double salary = Double.parseDouble(arr[1]);
                if (!map.containsKey(name)) {
                    map.put(name, 0.0);
                }
                map.put(name, map.get(name) + salary);
            }
            List<String> list = new ArrayList(map.keySet());
            Collections.sort(list);
            for (String key: list) {
                System.out.println(key + " " + map.get(key));
            }
        }
    }
}
