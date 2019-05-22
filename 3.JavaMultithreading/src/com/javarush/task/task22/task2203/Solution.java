package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException  {
        if (string == null) {
            throw new TooShortStringException();
        }
        int i1 = string.indexOf('\t');
        if (i1 != -1) {
            int i2 = string.indexOf('\t', i1 + 1);
            if (i2 != -1) {
                return string.substring(i1 + 1, i2);
            }
        }
        throw new TooShortStringException();
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
