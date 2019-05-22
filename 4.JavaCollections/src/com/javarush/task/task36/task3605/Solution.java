package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        FileReader reader = new FileReader(fileName);
        TreeSet<String> set = new TreeSet<>();
        int c;
        String pattern = "[a-z]+";
        while ((c =reader.read()) != -1) {
            char[] chars = {(char) c};
            String str = new String(chars);
            str = str.toLowerCase();
            if (str.matches(pattern)) {
                set.add(str);
            }
        }
        Iterator<String> iter = set.iterator();
        int idx = 1;
        while(iter.hasNext() && idx <= 5) {
            System.out.print(iter.next());
            idx++;
        }
    }

}
