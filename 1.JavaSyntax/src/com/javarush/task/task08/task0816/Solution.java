package com.javarush.task.task08.task0816;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Добрая Зинаида и летние каникулы
*/

public class Solution {
    public static HashMap<String, Date> createMap() throws ParseException {
        DateFormat df = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", df.parse("JUNE 1 1980"));
        for (int i = 1; i <= 9; i++) {
            map.put("111" + i, new Date());
        }
        return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map) {
        //напишите тут ваш код
        List<String> fordelete = new ArrayList<>();
        for (String name: map.keySet()) {
            Date value = map.get(name);
            int month = value.getMonth();
            if (month >= 5 && month <= 7) {
                fordelete.add(name);
            }
        }
        for (String name: fordelete) {
            map.remove(name);
        }
    }

    public static void main(String[] args) {

    }
}
