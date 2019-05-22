package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
        try {
            String line;
            int countAll = 0;
            int countSpace = 0;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    String symbol = String.valueOf(line.charAt(i));
                    countAll++;
                    if (symbol.equals(" ")) {
                        countSpace++;
                    }
                }
            }
            double result = (((double) countSpace /(double) countAll) * (double) 100);
            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println(df.format(result));
        } finally {
            reader.close();
        }
    }
}
