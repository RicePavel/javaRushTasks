package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String resultFileName = args[0];
        String[] archiveNames = Arrays.copyOfRange(args, 1, args.length);
        List<FileInputStream> list = new ArrayList<>();
        for (String name: archiveNames) {
            list.add(new FileInputStream(name));
        }
        try (ZipInputStream zis = new ZipInputStream(new SequenceInputStream(Collections.enumeration(list)))) {
            ZipEntry entry = null;
            entry = zis.getNextEntry();
            if ((entry = zis.getNextEntry()) != null) {
                try (FileOutputStream out = new FileOutputStream(resultFileName)) {
                    byte[] byteBuff = new byte[4096];
                    int bytesRead = 0;
                    while ((bytesRead = zis.read(byteBuff)) != -1) {
                        out.write(byteBuff, 0, bytesRead);
                    }
                }
            }
        }
    }

    public static byte[] concat(byte[] first, byte[] second) {
        byte[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }


}
