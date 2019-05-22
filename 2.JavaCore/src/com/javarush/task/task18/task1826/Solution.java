package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class Solution {

    public static Map<Byte, Byte> map = new HashMap<>();

    static {
        List<Byte> bytes = new ArrayList<>();
        for (int b = Byte.MIN_VALUE; b <= Byte.MAX_VALUE; b++) {
            bytes.add((byte) b);
        }
        List<Byte> reverse = new ArrayList<>(bytes);
        Collections.reverse(bytes);
        for (int n = 0; n < bytes.size(); n++) {
            map.put(bytes.get(n), reverse.get(n));
        }
    }

    public static void main(String[] args) throws Exception {
        String mode = args[0];
        String inputName = args[1];
        String outputName = args[2];
        if (mode.equals("-e")) {
            FileInputStream input = new FileInputStream(inputName);
            FileOutputStream output = new FileOutputStream(outputName);
            try {
                byte[] bytes = new byte[input.available()];
                input.read(bytes);
                for (Byte b : bytes) {
                    byte newByte = map.get(b);
                    output.write(newByte);
                }
            } finally {
                input.close();
                output.close();
            }
        } else if (mode.equals("-d")) {
            FileInputStream input = new FileInputStream(inputName);
            FileOutputStream output = new FileOutputStream(outputName);
            try {
                byte[] bytes = new byte[input.available()];
                input.read(bytes);
                for (Byte b : bytes) {
                    Byte newByte = null;
                    for (Byte key: map.keySet()) {
                        Byte value = map.get(key);
                        if (value == b) {
                            newByte = key;
                            break;
                        }
                    }
                    if (newByte != null) {
                        output.write(newByte);
                    }
                }
            } finally {
                input.close();
                output.close();
            }
        }
    }

}
