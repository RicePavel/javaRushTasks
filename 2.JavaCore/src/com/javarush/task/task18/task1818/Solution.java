package com.javarush.task.task18.task1818;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

/* 
Два в одном
*/

public class Solution {

    public static void main(String[] args)throws Exception {
        Scanner scan = new Scanner(System.in);
        String name_1 = scan.nextLine();
        String name_2 = scan.nextLine();
        String name_3 = scan.nextLine();
        FileOutputStream output_1 = new FileOutputStream(name_1);
        FileInputStream input_2 = new FileInputStream(name_2);
        FileInputStream input_3 = new FileInputStream(name_3);
        try {
            while (input_2.available() > 0) {
                int b = input_2.read();
                output_1.write(b);
            }
            while (input_3.available() > 0) {
                int b = input_3.read();
                output_1.write(b);
            }
        } finally {
            output_1.close();
            input_2.close();
            input_3.close();
        }
    }

}
