package com.javarush.task.task10.task1004;

/* 
Задача №4 на преобразование целых типов
*/

public class Solution {
    public static void main(String[] args) {
        short number = 9;
        char zero = '0';
        int nine = ((int) number + Integer.valueOf(String.valueOf(zero)));
        System.out.println(nine);
    }
}
