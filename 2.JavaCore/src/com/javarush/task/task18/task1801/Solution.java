package com.javarush.task.task18.task1801;

import java.io.FileInputStream;
import java.util.*;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        FileInputStream fis = new FileInputStream(fileName);
        int max = 0;
        try {
            while (fis.available() > 0) {
                int data = fis.read();
                if (data > max) {
                    max = data;
                }
            }
            System.out.println(max);
        } finally {
            fis.close();
        }
    }
}
