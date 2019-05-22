package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        double sum = 0;
        int count = 0;
        while (true) {
            double d = scan.nextDouble();
            if (d == -1) {
                break;
            }
            sum += d;
            count++;
        }
        double avg = sum / count;
        System.out.println(avg);
    }
}

