package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
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
         List<String> lines = new ArrayList<>();
        String newContent = "";
         try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName_1))) {
             String line;
             while ((line = fileReader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word: words) {
                    Pattern p = Pattern.compile("([0-9])");
                    Matcher m = p.matcher(word);
                    if (m.find()) {
                        newContent += word + " ";
                    }
                }
             }
         }
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName_2))) {
             writer.write(newContent);
         }
         for (String l: lines) {
             System.out.println(l);
         }
    }
}
