package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

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
                while ((i = fileReader.read()) != -1) {
                    char[] c = {(char) i};
                    String s = new String(c);
                    content += s;
                }
                String result = content.replaceAll("\\.", "!");
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
