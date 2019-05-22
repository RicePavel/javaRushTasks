package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        String newStr = "";
        try {
            String str = "";
            int i;
            while ((i = reader.read()) != -1) {
                char c = (char) i;
                str += String.valueOf(c);
            }

            for (int n = 0; n < str.length(); n++) {
                char c = (char) (str.charAt(n) + key);
                newStr += String.valueOf(c);
            }
            return newStr;
        } catch (Exception e) {
            return newStr;
        }
    }
}
