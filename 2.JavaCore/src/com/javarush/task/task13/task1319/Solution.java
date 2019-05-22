package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        while (true) {
            String str = reader.readLine();
            bufferedWriter.write(str + "\n");
            if (str.equals("exit")) {
                break;
            }
        }
        bufferedWriter.close();
        reader.close();
    }
}
