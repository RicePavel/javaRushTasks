package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String fileName = reader.readLine();
            List<String> lines = new ArrayList<>();
            try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    int count = 0;
                    for (String word : words) {
                        Pattern pattern = Pattern.compile("\\b" + word + "\\b");
                        Matcher m = pattern.matcher(line);
                        while (m.find()) {
                            count++;
                        }
                    }
                    if (count == 2) {
                        lines.add(line);
                    }
                }
            }
            for (String l: lines) {
                System.out.println(l);
            }
        }
    }
}
