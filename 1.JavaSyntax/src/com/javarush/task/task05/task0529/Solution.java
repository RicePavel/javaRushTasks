package com.javarush.task.task05.task0529;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* 
Консоль-копилка
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        while (true) {
            String s = sc.nextLine();
            if (s.equals("сумма")) {
                break;
            }
            sum += Integer.valueOf(s);
        }
        System.out.println(sum);
    }
}
