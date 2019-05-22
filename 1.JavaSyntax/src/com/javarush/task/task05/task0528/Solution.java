package com.javarush.task.task05.task0528;

/* 
Вывести на экран сегодняшнюю дату
*/

import java.util.Calendar;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        Calendar cl = Calendar.getInstance();
        int day = cl.get(Calendar.DAY_OF_MONTH);
        int month = cl.get(Calendar.MONTH) + 1;
        int year = cl.get(Calendar.YEAR);
        System.out.println(day + " " + month + " " + year);
    }
}
