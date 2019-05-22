package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream input_1 = new FileInputStream(args[0]);
        try {
            byte[] data_1 = new byte[input_1.available()];
            input_1.read(data_1);
            Map<String, Integer> map = new HashMap<>();
            for (byte b: data_1) {
                byte[] arr = {b};
                String str = new String(arr);
                if (!map.containsKey(str)) {
                    map.put(str, 0);
                }
                map.put(str, map.get(str) + 1);
            }
            List<String> keys = new ArrayList(map.keySet());
            Collections.sort(keys, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    char c1 = o1.charAt(0);
                    char c2 = o2.charAt(0);
                    return ((int) c1) - ((int) c2);
                }
            });
            for (String key: keys) {
                System.out.println(key + " " + map.get(key));
            }
        } finally {
            input_1.close();
        }
    }
}
