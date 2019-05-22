package com.javarush.task.task18.task1803;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        FileInputStream fis = new FileInputStream(fileName);
        Map<Integer, Integer> map = new HashMap<>();
        try {
            while (fis.available() > 0) {
                int data = fis.read();
                if (!map.containsKey(data)) {
                    map.put(data, 0);
                }
                map.put(data, map.get(data) + 1);
            }
            int maxRepeat = Collections.max(map.values());
            String str = "";
            for (Integer key: map.keySet()) {
                if (map.get(key) == maxRepeat) {
                    str += key + " ";
                }
            }
            System.out.println(str);
        } finally {
            fis.close();
        }
    }
}
