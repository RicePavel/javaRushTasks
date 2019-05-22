package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.FileInputStream;
import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        String id = args[0];
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        try {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(" ");
                if (arr[0].equals(id)) {
                    System.out.println(line);
                    break;
                }
            }
        } finally {
            reader.close();
        }
    }
}
