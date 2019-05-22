package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws Exception {
        if (args.length == 5 && args[0].equals("-u")) {
            Scanner scan = new Scanner(System.in);
            String fileName = scan.nextLine();
            String updateId = args[1];
            String productName = args[2];
            String price = args[3];
            String quantity = args[4];
            updateId = padString(updateId, 8);
            productName = padString(productName, 30);
            price = padString(price, 8);
            quantity = padString(quantity, 4);
            BufferedWriter writer = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            try {
                String line = "";
                List<String> lines = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    String id = line.substring(0, 8);
                    if (id.equals(updateId)) {
                        line = id + productName + price + quantity;
                    }
                    lines.add(line);
                }
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
                for (String l: lines) {
                    writer.write(l);
                    writer.newLine();
                }
            } finally {
                if (writer != null) {
                    writer.close();
                }
                reader.close();
            }
        } else if (args.length == 2 && args[0].equals("-d")) {
            Scanner scan = new Scanner(System.in);
            String fileName = scan.nextLine();
            String deleteId = args[1];
            deleteId = padString(deleteId, 8);
            BufferedWriter writer = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            try {
                String line = "";
                List<String> lines = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    String id = line.substring(0, 8);
                    if (id.equals(deleteId)) {
                        continue;
                    }
                    lines.add(line);
                }
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
                for (String l: lines) {
                    writer.write(l);
                    writer.newLine();
                }
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
