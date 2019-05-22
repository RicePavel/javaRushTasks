package com.javarush.task.task39.task3904;

import java.util.*;

/* 
Лестница
*/
public class Solution {
    private static int n = 0;
    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if ( n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 4;
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);
            list.add(4);
            for (int i = 4; i <= n; i++) {
                int count = list.get(list.size() - 1) + list.get(list.size() - 2) + list.get(list.size() - 3);
                list.add(count);
            }
            return (long) list.get(list.size() - 1);
        }
    }

}

