package com.javarush.task.task22.task2208;

import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {

    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder("");
        for (String key: params.keySet()) {
            String value = params.get(key);
            if (value != null) {
                if (!sb.toString().isEmpty()) {
                    sb = sb.append(" and ");
                }
                sb = sb.append(key + " = " + "'" + value + "'");
            }
        }
        return sb.toString();
    }
}
