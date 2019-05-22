package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        String archivePath = args[1];
        List<ZipEntry> list = new ArrayList<>();
        List<byte[]> contents = new ArrayList<>();
        ZipFile zipFile = new ZipFile(archivePath);
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(archivePath))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                list.add(entry);
                InputStream is = zipFile.getInputStream(entry);
                byte[] bytes = new byte[is.available()];
                is.read(bytes);
                contents.add(bytes);
            }
        }
        Path file = Paths.get(fileName);
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(archivePath))) {
            out.putNextEntry(new ZipEntry("new/" + file.getFileName().toString()));
            Files.copy(file, out);

            int index = 0;
            for (ZipEntry e : list) {
                out.putNextEntry(new ZipEntry(e.getName()));
                byte[] bytes = contents.get(index);
                out.write(bytes);
                index++;
            }

        }
    }
}
