package com.javarush.task.task18.task1816;

import java.util.*;
import java.io.*;

/* 
Английские буквы
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
        try {
            String line;
            int count = 0;
            String letters = "qwertyuiopasdfghjklzxcvbnm";
            letters += letters.toUpperCase();
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    String symbol = String.valueOf(line.charAt(i));
                    if (letters.contains(symbol)) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        } finally {
            reader.close();
        }
    }

}
