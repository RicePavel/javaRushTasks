package com.javarush.task.task08.task0827;

import sun.java2d.pipe.SpanShapeRenderer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* 
Работа с датой
*/

public class Solution {
    public static void main(String[] args) throws ParseException {
        System.out.println(isDateOdd("MAY 1 2013"));
    }

    public static boolean isDateOdd(String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("MMMM d yyyy", Locale.ENGLISH);
        Date d = formatter.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return (calendar.get(Calendar.DAY_OF_YEAR) % 2 == 1);
    }
}
