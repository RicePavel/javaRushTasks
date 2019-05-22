package com.javarush.task.task07.task0708;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

/* 
Самая длинная строка
*/

public class Solution {
    private static List<String> strings = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < 5 ; i++) {
            strings.add(s.nextLine());
        }
        int maxLenght = 0;
        for (String str : strings) {
            if (str.length() > maxLenght) {
                maxLenght = str.length();
            }
        }
        for (String str: strings) {
            if (str.length() == maxLenght) {
                System.out.println(str);
            }
        }
    }
}
