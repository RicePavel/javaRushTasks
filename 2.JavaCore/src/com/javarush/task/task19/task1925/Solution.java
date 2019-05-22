package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName_1 = args[0];
        String fileName_2 = args[1];
        List<String> newWords = new ArrayList<>();
        String newContent = "";
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName_1))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word: words) {
                    if (word.length() > 6) {
                        newWords.add(word);
                    }
                }
            }
        }
        newContent = String.join(",", newWords);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName_2))) {
            writer.write(newContent);
        }
    }
}
