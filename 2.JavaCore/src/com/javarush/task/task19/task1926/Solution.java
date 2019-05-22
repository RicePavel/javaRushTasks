package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            String fileName = consoleReader.readLine();
            List<String> newLines = new ArrayList<>();
            try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    String[] symbols = line.split("");
                    List<String> list = Arrays.asList(symbols);
                    Collections.reverse(list);
                    list.toArray(symbols);
                    String newLine = String.join("", symbols);
                    newLines.add(newLine);
                }
            }
            for (String newLine: newLines) {
                System.out.println(newLine);
            }
        }
    }
}
