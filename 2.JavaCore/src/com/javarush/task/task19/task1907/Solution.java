package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName = consoleReader.readLine();
            FileReader fileReader = new FileReader(fileName);
            try {
                int i;
                String result = "";
                while ((i = fileReader.read()) != -1) {
                    char[] c = {(char) i};
                    String s = new String(c);
                    result += s;
                }
                Pattern p = Pattern.compile("\\bworld\\b");
                Matcher m = p.matcher(result);
                int count = 0;
                while (m.find()) {
                    count++;
                }
                System.out.println(count);
            } finally {
                fileReader.close();
            }
        } finally {
            consoleReader.close();
        }
    }

}
