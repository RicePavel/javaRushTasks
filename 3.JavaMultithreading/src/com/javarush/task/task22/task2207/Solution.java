package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

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
            while (!list.isEmpty()) {
                String word_1 = list.get(0);
                StringBuilder sb_1 = new StringBuilder(word_1);
                int index_2 = -1;
                for (int i = 1; i < list.size(); i++) {
                    String word_2 = list.get(i);
                    StringBuilder sb_2 = new StringBuilder(word_2);
                    sb_2 = sb_2.reverse();
                    if (sb_1.toString().equals(sb_2.toString())) {
                        index_2 = i;
                        break;
                    }
                }
                if (index_2 != -1) {
                    Pair pair = new Pair();
                    pair.first = word_1;
                    pair.second = list.get(index_2);
                    result.add(pair);
                    list.remove(index_2);
                }
                list.remove(0);
            }
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                        first == null ? second :
                            second == null ? first :
                                first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
