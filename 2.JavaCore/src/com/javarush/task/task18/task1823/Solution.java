package com.javarush.task.task18.task1823;

import java.io.FileInputStream;
import java.util.*;
import java.io.*;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String name = scan.nextLine();
            if (name.equals("exit")) {
                break;
            }
            ReadThread thread = new ReadThread(name);
            thread.start();
        }


    }

    public static class ReadThread extends Thread {

        private String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            FileInputStream input_1 = null;
            try {
                input_1 = new FileInputStream(fileName);
                byte[] data_1 = new byte[input_1.available()];
                input_1.read(data_1);
                Map<Byte, Integer> map = new HashMap<>();
                for (byte b : data_1) {
                    Byte bo = new Byte(b);
                    if (!map.containsKey(bo)) {
                        map.put(bo, 0);
                    }
                    map.put(bo, map.get(bo) + 1);
                }
                Collection<Integer> values = map.values();
                int max = Collections.max(values);
                for (Byte b : map.keySet()) {
                    if (map.get(b) == max) {
                        resultMap.put(fileName, (int) b);
                    }
                }
            } catch (Exception e) {
            } finally {
                try {
                    if (input_1 != null) {
                        input_1.close();
                    }
                } catch (IOException e) {}
            }

        }
    }
}
