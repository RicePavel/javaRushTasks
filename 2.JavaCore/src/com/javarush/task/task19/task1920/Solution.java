package com.javarush.task.task19.task1920;

/* 
Самый богатый
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
            Collection<Double> values = map.values();
            double max = Collections.max(values);
            List<String> names = new ArrayList<>();
            for (String name: map.keySet()) {
                if (map.get(name) == max) {
                    names.add(name);
                }
            }
            Collections.sort(names);
            String result = String.join(" ", names);
            System.out.println(result);
        }
    }
}
