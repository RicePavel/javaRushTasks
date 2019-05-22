package com.javarush.task.task18.task1807;

import java.util.*;
import java.io.*;

/* 
Подсчет запятых
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        FileInputStream fis = new FileInputStream(fileName);
        try {
            char c = ',';
            int code = (int) c;
            int countOfSymbol = 0;
            if (fis.available() > 0) {
                byte[] buffer = new byte[fis.available()];
                int count = fis.read(buffer);
                for (byte b: buffer) {
                    if ((int) b == code) {
                        countOfSymbol++;
                    }
                }
            }
            System.out.println(countOfSymbol);
        } finally {
            fis.close();
        }
    }
}
