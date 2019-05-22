package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String name_1 = scan.nextLine();
        String name_2 = scan.nextLine();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(name_1)));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name_2)));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(" ");
                String stringForWrite = "";
                for (String str : arr) {
                    Double d = Double.valueOf(str);
                    long i = Math.round(d);
                    stringForWrite += i + " ";
                }
                writer.write(stringForWrite);
            }
        } finally {
            reader.close();
            writer.close();
        }
    }
}
