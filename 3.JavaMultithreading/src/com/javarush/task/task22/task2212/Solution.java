package com.javarush.task.task22.task2212;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона
*/
public class Solution {

    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null || telNumber.isEmpty()) {
            return false;
        }
        String startSymbol = getSymbolOfPosition(telNumber, 0);
        String endSymbol = getSymbolOfPosition(telNumber, telNumber.length() - 1);
        if (startSymbol.equals("+") && countOccurences(telNumber, "[\\d]") != 12) {
            return false;
        }
        if ((startSymbol.matches("[\\d]") || startSymbol.equals("(")) && countOccurences(telNumber, "[\\d]") != 10) {
            return false;
        }
        if (countOccurences(telNumber, "-") > 2) {
            return false;
        }
        if (countOccurences(telNumber, "--") > 0) {
            return false;
        }
        if (countOccurences(telNumber, "\\(") > 1) {
            return false;
        }
        if (countOccurences(telNumber, "\\(") == 1) {
            if (countOccurences(telNumber, "\\)") != 1) {
                return false;
            }
            int openBracketIndex = getStartIndex(telNumber, "\\(");
            int closeBracketIndex = getStartIndex(telNumber, "\\)");
            if (closeBracketIndex < openBracketIndex) {
                return false;
            }
            int dashIndex = getStartIndex(telNumber, "-");
            if (dashIndex != -1 && dashIndex < openBracketIndex) {
                return false;
            }
            String insideBrackets = telNumber.substring(openBracketIndex + 1, closeBracketIndex);
            if (!insideBrackets.matches("[\\d]{3}")) {
                return false;
            }
        }
        if (!telNumber.matches("[\\d\\)\\(\\+-]+")) {
            return false;
        }
        if (!endSymbol.matches("[\\d]")) {
            return false;
        }
        return true;
    }

    private static int getStartIndex(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return m.start();
        }
        return -1;
    }

    private static int countOccurences(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        int count = 0;
        while (m.find()) {
            count++;
        }
        return count;
    }

    private static String getSymbolOfPosition(String str, int pos) {
        char[] c = {str.charAt(pos)};
        return new String(c);
    }

    public static void main(String[] args) {
        /* System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38(050)1234567")); */
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));
    }



}
