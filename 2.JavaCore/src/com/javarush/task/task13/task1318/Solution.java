package com.javarush.task.task13.task1318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileInputStream;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufReader.readLine();
        FileInputStream fileStream = new FileInputStream(fileName);
        int i = -1;
        while ((i = fileStream.read()) != -1) {
            System.out.print((char) i);
        }
        bufReader.close();
        fileStream.close();
    }
}