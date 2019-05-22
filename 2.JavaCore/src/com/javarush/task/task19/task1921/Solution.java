package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            Map<String, Double> map = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                String arr[] = line.split(" ");
                int year = Integer.parseInt(arr[arr.length - 1]);
                int month = Integer.parseInt(arr[arr.length - 2]);
                int day = Integer.parseInt(arr[arr.length - 3]);
                String[] arrForName = Arrays.copyOfRange(arr, 0, arr.length - 3);
                String name = String.join(" ", arrForName);
                Calendar calendar = new GregorianCalendar();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month -1);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                Date date = calendar.getTime();
                Person person = new Person(name, date);
                PEOPLE.add(person);
            }

        }
    }
}
