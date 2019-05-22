package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(byteArrayOutputStream);
        System.setOut(stream);
        testString.printSomething();
        String initialResult = byteArrayOutputStream.toString();
        String result = initialResult;
        String[] arr = result.split("=");
        result = arr[0];
        arr = result.split(" ");
        String s_1 = arr[0];
        String s_2 = arr[2];
        int i_1 = Integer.parseInt(s_1);
        int i_2 = Integer.parseInt(s_2);
        String sign = arr[1].trim();
        String newResult = "";
        if (sign.equals("+")) {
            newResult = String.valueOf(i_1 + i_2);
        } else if (sign.equals("-")) {
            newResult = String.valueOf(i_1 - i_2);
        } else if (sign.equals("*")) {
            newResult = String.valueOf(i_1 * i_2);
        }
        System.setOut(consoleStream);
        System.out.println(initialResult + newResult);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

