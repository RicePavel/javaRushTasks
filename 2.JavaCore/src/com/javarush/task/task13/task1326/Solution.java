package com.javarush.task.task13.task1326;

import java.io.*;
import java.util.*;
/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String filename = reader.readLine();
            FileInputStream fin = null;
            BufferedReader fileReader = null;
            try {
                fin = new FileInputStream(filename);
                fileReader = new BufferedReader(new InputStreamReader(fin));
                List<Integer> list = new ArrayList<>();
                String line;
                while ((line = fileReader.readLine()) != null) {
                    Integer i = Integer.valueOf(line);
                    if (i % 2 == 0) {
                        list.add(i);
                    }
                }
                Collections.sort(list);
                for (Integer i : list) {
                    System.out.println(i);
                }
            } finally {
                if (fin != null) {
                    fin.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            }
            /*
            try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
                List<Integer> list = new ArrayList<>();
                String line;
                while ((line = fileReader.readLine()) != null) {
                    Integer i = Integer.valueOf(line);
                    if (i % 2 == 0) {
                        list.add(i);
                    }
                }
                Collections.sort(list);
                for (Integer i : list) {
                    System.out.println(i);
                }
            }
            */

        }
    }
}
