package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        Scanner s = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(s.nextInt());
        }
        int maxCount = 1;
        int count = 1;
        for (int i = 1; i < list.size(); i++) {
            int prev = list.get(i - 1);
            int current = list.get(i);
            if (prev == current) {
                count++;
            } else {
                count = 1;
            }
            if (count > maxCount) {
                maxCount = count;
            }
        }
        System.out.println(maxCount);
    }
}