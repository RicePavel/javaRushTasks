package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("qwq", "qq"));
    }

    public static boolean isOneEditAway(String first, String second) {
        if (first.equals(second)) {
            return true;
        }
        if (second.isEmpty() && first.isEmpty()) {
            return false;
        }
        if (second.isEmpty() && first.length() == 1) {
            return true;
        }
        if (first.isEmpty() && second.length() == 1) {
            return true;
        }
        if (first.length() + 1 == second.length()) {
            if (second.indexOf(first) != -1) {
                return true;
            }
        }
        if (second.length() + 1 == first.length()) {
            if (first.indexOf(second) != -1) {
                return true;
            }
        }
        if (first.length() == second.length()) {
            int countDifferences = 0;
            for (int i = 0; i < first.length(); i++) {
                char firstC = first.charAt(i);
                char secondC = second.charAt(i);
                if (firstC != secondC) {
                    countDifferences++;
                }
            }
            if (countDifferences == 1) {
                return true;
            }
        }
        if (first.length() + 1 == second.length() || second.length() + 1 == first.length()) {
            String larger;
            String lesser;
            if (first.length() > second.length()) {
                larger = first;
                lesser = second;
            } else {
                larger = second;
                lesser = first;
            }
            for (int i = 0; i < larger.length(); i++) {
                if (deleteSymbol(larger, i).equals(lesser)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String deleteSymbol(String str, int pos) {
        return str.substring(0, pos) + str.substring(pos + 1, str.length());
    }

}
