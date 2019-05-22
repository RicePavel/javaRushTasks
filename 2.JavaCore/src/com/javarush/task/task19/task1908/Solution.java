package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName = consoleReader.readLine();
            String fileName_2 = consoleReader.readLine();
            BufferedReader fileReader =new BufferedReader(new FileReader(fileName));
            try {
                int i;
                String content = "";
                while ((i = fileReader.read()) != -1) {
                    char[] c = {(char) i};
                    String s = new String(c);
                    content += s;
                }
                String[] arr = content.split(" ");
                String result = "";
                for (String str: arr) {
                    Integer number = null;
                    try {
                        number = Integer.valueOf(str);
                    } catch (NumberFormatException e) {}
                    if (number != null) {
                        result += str + " ";
                    }
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName_2))) {
                    writer.write(result);
                }
            } finally {
                fileReader.close();
            }
        } finally {
            consoleReader.close();
        }
    }

}
