package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String name_1 = scan.nextLine();
        String name_2 = scan.nextLine();

        FileInputStream input_1 = new FileInputStream(name_1);
        FileOutputStream output_1 = new FileOutputStream(name_1);
        FileInputStream input_2 = new FileInputStream(name_2);
        try {
            byte[] data_1 = new byte[input_1.available()];
            input_1.read(data_1);
            byte[] data_2 = new byte[input_2.available()];
            input_2.read(data_2);
            output_1.write(data_2);
            output_1.write(data_1);
        } finally {
            input_1.close();
            output_1.close();
            input_2.close();
        }
    }

}
