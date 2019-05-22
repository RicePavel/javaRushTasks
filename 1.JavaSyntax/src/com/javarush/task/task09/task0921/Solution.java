package com.javarush.task.task09.task0921;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

/* 
Метод в try..catch
*/

public class Solution {
    public static void main(String[] args) {
        readData();
    }

    public static void readData() {
        List<Integer> list = new ArrayList<>();
        try {
            Scanner scan = new Scanner(System.in);
            while (true) {
                int i = scan.nextInt();
                list.add(i);
            }
        } catch (Exception e) {
            for (Integer n: list) {
                System.out.println(n);
            }
        }
    }
}
