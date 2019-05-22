package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String name_1 = scan.nextLine();
        String name_2 = scan.nextLine();

        FileInputStream input = new FileInputStream(name_1);
        FileOutputStream output_2 = new FileOutputStream(name_2);

        try {
            if (input.available() > 0) {
                byte[] buffer = new byte[input.available()];
                input.read(buffer);
                for (int n = buffer.length - 1; n >= 0; n--) {
                    output_2.write(buffer[n]);
                }
            }
        } finally {
            input.close();
            output_2.close();
        }
    }
}
