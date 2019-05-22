package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {

    public static void main(String[] args) throws Exception {
        String path = args[0];
        String resultAbsolutePath = args[1];
        File resultFile = new File(resultAbsolutePath);
        String[] arr = resultAbsolutePath.split("/");
        arr[arr.length - 1] = "allFilesContent.txt";
        String newPath = String.join("/", arr);
        File file2 = new File(newPath);
        FileUtils.renameFile(resultFile, file2);
        List<File> files = getFilesList(new File(path));
        Collections.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                String name1 = o1.getName();
                String name2 = o2.getName();
                return name1.compareTo(name2);
            }
        });
        try (FileOutputStream out = new FileOutputStream(file2)) {
            for (File file: files) {
                FileInputStream in = new FileInputStream(file);
                byte[] bytes = new byte[(int)file.length()];
                in.read(bytes);
                out.write(bytes);
                out.write('\n');
            }
        }
    }

    private static List<File> getFilesList(File directory) {
        List<File> files = new ArrayList<>();
        for (File file: directory.listFiles()) {
            if (file.isDirectory()) {
                files.addAll(getFilesList(file));
            } else {
                if (file.length() <= 50) {
                    files.add(file);
                }
            }
        }
        return files;
    }

}
