package com.javarush.task.task40.task4008;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("9.10.2017 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        String[] arr = date.split(" ");
        if (arr.length == 2) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            LocalDate localDate = LocalDate.parse(arr[0], formatter);

            formatter = DateTimeFormatter.ofPattern("H:mm:ss");
            LocalTime localtime = LocalTime.parse(arr[1], formatter);

            TemporalField tf = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
            int weekOfYear = localDate.get(tf);
            tf = WeekFields.of(Locale.getDefault()).weekOfMonth();
            int weekOfMonth = localDate.get(tf);

            formatter = DateTimeFormatter.ofPattern("a");
            String am_pm = localtime.format(formatter);

            formatter = DateTimeFormatter.ofPattern("h");
            String hours = localtime.format(formatter);

            System.out.println("День: " + localDate.getDayOfMonth());
            System.out.println("День недели: " + (localDate.getDayOfWeek().getValue()));
            System.out.println("День месяца: " + localDate.getDayOfMonth());
            System.out.println("День года: " + localDate.getDayOfYear());
            System.out.println("Неделя месяца: " + weekOfMonth);
            System.out.println("Неделя года: " + weekOfYear);
            System.out.println("Месяц: " + (localDate.getMonth().getValue()));
            System.out.println("Год: " + localDate.getYear());
            System.out.println("AM или PM: " + am_pm);
            System.out.println("Часы: " + hours);
            System.out.println("Часы дня: " + localtime.getHour());
            System.out.println("Минуты: " + localtime.getMinute());
            System.out.println("Секунды: " + localtime.getSecond());
            return;
        }

        if (date.contains(".")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);
            TemporalField tf = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
            int weekOfYear = localDate.get(tf);
            tf = WeekFields.of(Locale.getDefault()).weekOfMonth();
            int weekOfMonth = localDate.get(tf);
            System.out.println("День: " + localDate.getDayOfMonth());
            System.out.println("День недели: " + (localDate.getDayOfWeek().getValue()));
            System.out.println("День месяца: " + localDate.getDayOfMonth());
            System.out.println("День года: " + localDate.getDayOfYear());
            System.out.println("Неделя месяца: " + weekOfMonth);
            System.out.println("Неделя года: " + weekOfYear);
            System.out.println("Месяц: " + (localDate.getMonth().getValue()));
            System.out.println("Год: " + localDate.getYear());
            return;
        }

        if (date.contains(":")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
            LocalTime localtime = LocalTime.parse(date, formatter);
            formatter = DateTimeFormatter.ofPattern("a");
            String am_pm = localtime.format(formatter);
            formatter = DateTimeFormatter.ofPattern("h");
            String hours = localtime.format(formatter);
            System.out.println("AM или PM: " + am_pm);
            System.out.println("Часы: " + hours);
            System.out.println("Часы дня: " + localtime.getHour());
            System.out.println("Минуты: " + localtime.getMinute());
            System.out.println("Секунды: " + localtime.getSecond());
            return;
        }

    }
}
