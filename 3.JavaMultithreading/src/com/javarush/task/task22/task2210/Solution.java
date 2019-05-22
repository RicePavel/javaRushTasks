package com.javarush.task.task22.task2210;

import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {

    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer t = new StringTokenizer(query, delimiter);
        String[] arr = new String[t.countTokens()];
        int i = 0;
        while (t.hasMoreTokens()) {
            arr[i] = t.nextToken();
            i++;
        }
        return arr;
    }
}
