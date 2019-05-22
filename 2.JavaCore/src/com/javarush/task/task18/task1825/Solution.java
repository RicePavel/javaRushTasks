package com.javarush.task.task18.task1825;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        List<String> names = new ArrayList<>();
        while (true) {
            String name = scan.nextLine();
            if (name.equals("end")) {
                break;
            }
            names.add(name);
        }
        int count = names.size();
        String newFileName = "";
        if (count > 0) {
            String firstName = names.get(0);
            newFileName = firstName.substring(0, firstName.lastIndexOf("."));
        }
        FileOutputStream output = new FileOutputStream(newFileName);
        try {
            for (int i = 1; i <= count; i++) {
                String fileName = newFileName + ".part" + i;
                if (names.contains(fileName)) {
                    FileInputStream input = new FileInputStream(fileName);
                    try {
                        byte[] bytes = new byte[input.available()];
                        input.read(bytes);
                        output.write(bytes);
                    } finally {
                        input.close();
                    }
                }
            }
        } finally {
            output.close();
        }
    }
}
