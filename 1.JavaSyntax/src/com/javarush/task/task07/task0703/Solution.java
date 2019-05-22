package com.javarush.task.task07.task0703;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* 
Общение одиноких массивов
*/

public class Solution {


    public static void main(String[] args) throws Exception {
        String[] stringArr = new String[10];
        int[] intArr = new int[10];
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            stringArr[i] = s.nextLine();
        }
        for (int i = 0; i < 10; i++) {
            intArr[i] = stringArr[i].length();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(intArr[i]);
        }
    }
}
