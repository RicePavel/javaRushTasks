package com.javarush.task.task09.task0922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.*;

/* 
Какое сегодня число?
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-M-dd");
        Date d = f.parse(str);
        f = new SimpleDateFormat("MMM", Locale.ENGLISH);
        String monthName = f.format(d);
        monthName = monthName.toUpperCase();
        f = new SimpleDateFormat("d, yyyy", Locale.ENGLISH);
        String dayYear = f.format(d);
        String newStr = monthName + " " + dayYear;
        System.out.println(newStr);
    }
}
