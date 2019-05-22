package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.M.yyy HH:mm:ss");
        try {
            Date dateObj = formatter.parse(date);
            Calendar cl = Calendar.getInstance();
            cl.setTime(dateObj);
            System.out.println("День: " + cl.get(Calendar.DATE));
            System.out.println("День недели: " + (cl.get(Calendar.DAY_OF_WEEK) > 1 ? cl.get(Calendar.DAY_OF_WEEK) - 1 : 7));
            System.out.println("День месяца: " + cl.get(Calendar.DAY_OF_MONTH));
            System.out.println("День года: " + cl.get(Calendar.DAY_OF_YEAR));
            System.out.println("Неделя месяца: " + cl.get(Calendar.WEEK_OF_MONTH));
            System.out.println("Неделя года: " + cl.get(Calendar.WEEK_OF_YEAR));
            System.out.println("Месяц: " + (cl.get(Calendar.MONTH) + 1));
            System.out.println("Год: " + cl.get(Calendar.YEAR));
            System.out.println("AM или PM: " + (cl.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM"));
            System.out.println("Часы: " + cl.get(Calendar.HOUR));
            System.out.println("Часы дня: " + cl.get(Calendar.HOUR_OF_DAY));
            System.out.println("Минуты: " + cl.get(Calendar.MINUTE));
            System.out.println("Секунды: " + cl.get(Calendar.SECOND));
            return;
        } catch (ParseException e) {

        }
        try {
            formatter = new SimpleDateFormat("dd.M.yyy");
            Date dateObj = formatter.parse(date);
            Calendar cl = Calendar.getInstance();
            cl.setTime(dateObj);
            System.out.println("День: " + cl.get(Calendar.DATE));
            System.out.println("День недели: " + (cl.get(Calendar.DAY_OF_WEEK) > 1 ? cl.get(Calendar.DAY_OF_WEEK) - 1 : 7));
            System.out.println("День месяца: " + cl.get(Calendar.DAY_OF_MONTH));
            System.out.println("День года: " + cl.get(Calendar.DAY_OF_YEAR));
            System.out.println("Неделя месяца: " + cl.get(Calendar.WEEK_OF_MONTH));
            System.out.println("Неделя года: " + cl.get(Calendar.WEEK_OF_YEAR));
            System.out.println("Месяц: " + (cl.get(Calendar.MONTH) + 1));
            System.out.println("Год: " + cl.get(Calendar.YEAR));
            return;
        } catch (ParseException e) {

        }
        try {
            formatter = new SimpleDateFormat("HH:mm:ss");
            Date dateObj = formatter.parse(date);
            Calendar cl = Calendar.getInstance();
            cl.setTime(dateObj);
            System.out.println("AM или PM: " + (cl.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM"));
            System.out.println("Часы: " + cl.get(Calendar.HOUR));
            System.out.println("Часы дня: " + cl.get(Calendar.HOUR_OF_DAY));
            System.out.println("Минуты: " + cl.get(Calendar.MINUTE));
            System.out.println("Секунды: " + cl.get(Calendar.SECOND));
            return;
        } catch (ParseException e) {

        }
    }
}
