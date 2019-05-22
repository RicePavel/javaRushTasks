package com.javarush.task.task07.task0702;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* 
Массив из строчек в обратном порядке
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        String[] arr = new String[10];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 8; i++) {
            arr[i] = sc.nextLine();
        }
        for (int n = 9; n >= 0; n--) {
            System.out.println(arr[n]);
        }
    }
}