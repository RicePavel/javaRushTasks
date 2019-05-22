package com.javarush.task.task32.task3201;

import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws Exception {
        String fileName = args[0];
        int number = Integer.valueOf(args[1]);
        String text = args[2];
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            long length = raf.length();
            if (length < number) {
                raf.seek(length);
            } else {
                raf.seek(number);
            }
            raf.write(text.getBytes());
        }
    }
}
