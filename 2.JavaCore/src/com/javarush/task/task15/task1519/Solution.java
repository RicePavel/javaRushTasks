package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String str = scan.nextLine();
            if (str.equals("exit")) {
                break;
            }
            Double d = getDouble(str);
            if (d != null) {
                print(d);
                continue;
            }
            Short s = getShort(str);
            if (s != null) {
                print(s);
                continue;
            }
            Integer i = getInteger(str);
            if (i != null && (i <= 0 || i >= 128)) {
                print(i);
                continue;
            }
            print(str);
        }
    }

    public static Double getDouble(String str) {
        try {
            if (str.indexOf(".") != -1) {
                return Double.valueOf(str);
            }
        } catch (NumberFormatException e) { }
        return null;
    }

    public static Short getShort(String str) {
        try {
            int i = Integer.parseInt(str);
            if (i > 0 && i < 128) {
                return (short) i;
            }
        } catch (NumberFormatException e) {

        }
        return null;
    }

    public static Integer getInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {

        }
        return null;
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
