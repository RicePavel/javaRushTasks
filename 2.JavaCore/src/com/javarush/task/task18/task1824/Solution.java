package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String name = scan.nextLine();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(name);
            } catch (FileNotFoundException e) {
                System.out.println(name);
                break;
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        }
    }

}
