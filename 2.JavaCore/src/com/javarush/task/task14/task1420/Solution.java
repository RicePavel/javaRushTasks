package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        if (a <= 0 || b <= 0) {
            throw new Exception("");
        }
        System.out.println(nod(a, b));
    }

    public static int nod(int a, int b) {
        int nod = 1;
        int min = (a < b ? a : b);
        for (int i = 1; i <= min; i++) {
            if (a % i == 0 && b % i == 0 && i > nod) {
                nod = i;
            }
        }
        return nod;
    }



}
