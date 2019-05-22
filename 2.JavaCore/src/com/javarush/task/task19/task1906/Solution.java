package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String name_1 = consoleReader.readLine();
            String name_2 = consoleReader.readLine();
            FileReader reader = new FileReader(name_1);
            FileWriter writer = new FileWriter(name_2);
            try {
                int c;
                int pos = 1;
                while ((c = reader.read()) != -1) {
                    if (pos % 2 == 0) {
                        writer.write(c);
                    }
                    pos++;
                }
            } finally {
                reader.close();
                writer.close();
            }
        } finally {
            consoleReader.close();
        }
    }

}
