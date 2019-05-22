package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Составить цепочку слов
*/
public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            List<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(" ");
                for (String s: arr) {
                    list.add(s);
                }
            }
            String[] arr = new String[list.size()];
            list.toArray(arr);
            StringBuilder result = getLine(arr);
            System.out.println(result.toString());
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    public static StringBuilder getLine(String... words) {
        List<String> wordsList = new ArrayList<>();
        for (String word: words) {
            wordsList.add(word);
        }
        List<String> combinations = getCombinations(wordsList);
        if (combinations.size() > 0) {
            return new StringBuilder(combinations.get(0));
        } else {
            return new StringBuilder("");
        }
    }


    private static List<String> getCombinations(List<String> words) {
        List<String> combinations = new ArrayList<>();
        if (words.size() == 2) {
            String word_1 = words.get(0);
            String word_2 = words.get(1);
            if (matchingToStart(word_1, word_2)) {
                combinations.add(word_1 + " " + word_2);
            }
            if (matchingToStart(word_2, word_1)) {
                combinations.add(word_2 + " " + word_1);
            }
        } else if (words.size() > 2) {
            for (int i = 0; i < words.size(); i++) {
                String word = words.get(i);
                List<String> newWords = new ArrayList<>(words);
                newWords.remove(i);
                List<String> newCombinations = getCombinations(newWords);
                for (String combination: newCombinations) {
                    if (matchingToStart(word, combination)) {
                        combinations.add(word + " " + combination);
                    }
                    if (matchingToStart(combination, word)) {
                        combinations.add(combination + " " + word);
                    }
                }
            }
        }
        return combinations;
    }

    private static boolean matchingToStart(String word, String combination) {
        char[] start = {combination.charAt(0)};
        String startString = new String(start);
        char[] end = {word.charAt(word.length() - 1)};
        String endString = new String(end);
        if (endString.equalsIgnoreCase(startString)) {
            return true;
        } else {
            return false;
        }
    }


}
