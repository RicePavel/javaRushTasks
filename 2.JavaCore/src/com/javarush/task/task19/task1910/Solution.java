package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName = consoleReader.readLine();
            String fileName_2 = consoleReader.readLine();
            BufferedReader fileReader =new BufferedReader(new FileReader(fileName));
            try {
                int i;
                String content = "";
                String[] signs = {".", ",", ":", ":"};
                String str;
                while ((str = fileReader.readLine()) != null) {
                    content += str;
                }
                //String result = content.replaceAll("[-.?!)(,:]", "");
                String result = content.replaceAll("\\p{Punct}", "");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName_2))) {
                    writer.write(result);
                }
            } finally {
                fileReader.close();
            }
        } finally {
            consoleReader.close();
        }
    }
}
