package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws Exception {
        String fileName = args[0];
        int number = Integer.valueOf(args[1]);
        String text = args[2];
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            raf.seek(number);
            byte[] bytes = new byte[text.getBytes().length];
            long lengthFile = raf.length();
            int lenghtForRead;
            if (lengthFile - number >= text.getBytes().length) {
                lenghtForRead = text.getBytes().length;
            } else {
                lenghtForRead = (int) lengthFile - number;
            }
            raf.read(bytes, 0, lenghtForRead);
            String newString = new String(bytes);
            raf.seek(raf.length());
            String addedString = "";
            if (newString.equals(text)) {
                addedString = "true";
            } else {
                addedString = "false";
            }
            raf.write(addedString.getBytes());
        }
    }
}
