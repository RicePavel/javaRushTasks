package com.javarush.task.task21.task2103;

/* 
Все гениальное - просто!
*/
public class Solution {
    public static boolean calculate(boolean a, boolean b, boolean c, boolean d) {
        return (a && b && c && !d) || (c && (!a || !b || d));
    }

    public static void main(String[] args) {

    }
}