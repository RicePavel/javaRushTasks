package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        if (string == null) {
            throw new TooShortStringException();
        }
        int spaceIndex_1 = string.indexOf(' ');
        if (spaceIndex_1 != -1) {
            int count = 1;
            int index = spaceIndex_1 + 1;
            while (count < 4) {
                index = string.indexOf(' ', index + 1);
                count++;
                if (index == -1) {
                    break;
                }
            }
            if (index != -1) {
                int spaceIndex_5 = string.indexOf(' ', index + 1);
                int startSubstring = spaceIndex_1 + 1;
                int endSubstring = spaceIndex_5 != -1 ? spaceIndex_5 : string.length();
                return string.substring(startSubstring, endSubstring);
            }
        }
        throw new TooShortStringException();
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
