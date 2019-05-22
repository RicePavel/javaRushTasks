package com.javarush.task.task18.task1808;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.*;

/* 
Разделение файла
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String name_1 = scan.nextLine();
        String name_2 = scan.nextLine();
        String name_3 = scan.nextLine();
        FileInputStream input = new FileInputStream(name_1);
        FileOutputStream output_2 = new FileOutputStream(name_2);
        FileOutputStream output_3 = new FileOutputStream(name_3);
        try {
            int count_1 = input.available();
            int count_2;
            if ((count_1 % 2) == 0) {
                count_2 = count_1 / 2;
            } else {
                count_2 = ((count_1 - 1) / 2) + 1;
            }
            int count_3 = count_1 - count_2;

            byte[] buffer = new byte[count_2];
            input.read(buffer);
            output_2.write(buffer);

            buffer = new byte[count_3];
            input.read(buffer);
            output_3.write(buffer);
        } finally {
            input.close();
            output_2.close();
            output_3.close();
        }
    }
}
