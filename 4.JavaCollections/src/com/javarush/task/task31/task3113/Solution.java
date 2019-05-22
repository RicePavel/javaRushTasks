package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String directoryPath = scan.nextLine();
        Path path = Paths.get(directoryPath);
        if (Files.isDirectory(path)) {
            System.out.println("Всего папок - " + getCountDirectories(path));
            System.out.println("Всего файлов - " + getCountFiles(path));
            System.out.println("Общий размер - " + getCountBytes(path));
        } else {
            System.out.println(directoryPath + " - не папка");
        }
    }

    static int getCountDirectories(Path dir) throws IOException {
        int count = 0;
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir)) {
            for (Path path: directoryStream) {
                if (Files.isDirectory(path)) {
                    count++;
                    count += getCountDirectories(path);
                }
            }
        }
        return count;
    }

    static int getCountFiles(Path dir) throws IOException  {
        int count = 0;
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir)) {
            for (Path path: directoryStream) {
                if (Files.isDirectory(path)) {
                    count += getCountFiles(path);
                } else {
                    count++;
                }
            }
        }
        return count;
    }

    static int getCountBytes(Path dir) throws IOException {
        int count = 0;
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir)) {
            for (Path path: directoryStream) {
                if (Files.isDirectory(path)) {
                    count += getCountBytes(path);
                } else {
                    count += Files.readAllBytes(path).length;
                }
            }
        }
        return count;
    }

}
