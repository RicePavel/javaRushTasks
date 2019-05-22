package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isPowerOfThree(int n) {
        int st = 1;
        while (st < n) {
            st = st * 3;
        }
        if (st == n) {
            return true;
        } else {
            return false;
        }
    }
}
