package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        if (args.length == 4 && args[0].equals("-c")) {
            Scanner scan = new Scanner(System.in);
            String fileName = scan.nextLine();
            String productName = args[1];
            String price = args[2];
            String quantity = args[3];
            BufferedWriter writer = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            try {
                int maxId = 0;
                String line = "";
                List<String> lines = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                    int id = Integer.valueOf(line.substring(0, 8).trim());
                    if (id > maxId) {
                        maxId = id;
                    }
                }
                String newId = String.valueOf((maxId + 1));
                newId = padString(newId, 8);
                productName = padString(productName, 30);
                price = padString(price, 8);
                quantity = padString(quantity, 4);
                String newLine = newId + productName + price + quantity;
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
                for (String l: lines) {
                    writer.write(l);
                    writer.newLine();
                }
                writer.write(newLine);
                writer.newLine();
            } finally {
                if (writer != null) {
                    writer.close();
                }
                reader.close();
            }
        }
    }

    private static String padString(String str, int len) {
        for (int i = str.length(); i < len; i++) {
            str += " ";
        }
        return str;
    }
}
