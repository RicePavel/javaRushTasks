package com.javarush.task.task07.task0704;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* 
Переверни массив
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int[] arr = new int[10];
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            arr[i] = s.nextInt();
        }
        for (int i = 9; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }
}

