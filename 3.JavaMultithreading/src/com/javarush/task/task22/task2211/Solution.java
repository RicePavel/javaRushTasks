package com.javarush.task.task22.task2211;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String firstFileName = args[0];
        String secondFileName = args[1];
        try (FileInputStream fis = new FileInputStream(firstFileName)) {
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            String str = new String(bytes, Charset.forName("Windows-1251"));
            byte[] bytes_2 = str.getBytes(Charset.forName("UTF-8"));
            try (FileOutputStream out = new FileOutputStream(secondFileName)) {
                out.write(bytes_2);
            }
        }
    }
}
