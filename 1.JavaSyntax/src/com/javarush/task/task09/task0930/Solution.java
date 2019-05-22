package com.javarush.task.task09.task0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s);
        }

        String[] array = list.toArray(new String[0]);
        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        List<Integer> stringPositions = new ArrayList<>();
        List<Integer> numberPositions = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (isNumber(array[i])) {
                numberPositions.add(i);
            } else {
                stringPositions.add(i);
            }
        }
        for (int n = 1; n < stringPositions.size(); n++) {
            for (int k = stringPositions.size() - 1; k >= n; k--) {
                if (isGreaterThan(array[stringPositions.get(k - 1)], array[stringPositions.get(k)])) {
                    String x = array[stringPositions.get(k - 1)];
                    array[stringPositions.get(k - 1)] = array[stringPositions.get(k)];
                    array[stringPositions.get(k)] = x;
                }
            }
        }
        for (int n = 1; n < numberPositions.size(); n++) {
            for (int k = numberPositions.size() - 1; k >= n; k--) {
                if (Integer.valueOf(array[numberPositions.get(k - 1)]) < Integer.valueOf(array[numberPositions.get(k)])) {
                    String x = array[numberPositions.get(k - 1)];
                    array[numberPositions.get(k - 1)] = array[numberPositions.get(k)];
                    array[numberPositions.get(k)] = x;
                }
            }
        }
    }

    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String s) {
        if (s.length() == 0) return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((i != 0 && c == '-') // Строка содержит '-'
                    || (!Character.isDigit(c) && c != '-') // или не цифра и не начинается с '-'
                    || (chars.length == 1 && c == '-')) // или одиночный '-'
            {
                return false;
            }
        }
        return true;
    }
}
