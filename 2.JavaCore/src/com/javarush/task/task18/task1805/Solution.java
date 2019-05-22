package com.javarush.task.task18.task1805;

import java.io.FileInputStream;
import java.util.*;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        FileInputStream fis = new FileInputStream(fileName);
        Set<Integer> set = new HashSet<>();
        try {
            while (fis.available() > 0) {
                int data = fis.read();
                set.add(data);
            }
            List<Integer> list = new ArrayList(set);
            Collections.sort(list);
            String str = "";
            for (Integer key: list) {
                str += key + " ";
            }
            System.out.println(str);
        } finally {
            fis.close();
        }
    }
}
