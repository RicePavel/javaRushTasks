package com.javarush.task.task18.task1802;

import java.io.FileInputStream;
import java.util.Scanner;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        FileInputStream fis = new FileInputStream(fileName);
        int min = Integer.MAX_VALUE;
        try {
            while (fis.available() > 0) {
                int data = fis.read();
                if (data < min) {
                    min = data;
                }
            }
            System.out.println(min);
        } finally {
            fis.close();
        }
    }
}
