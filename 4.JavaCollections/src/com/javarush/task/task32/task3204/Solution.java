package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        String pass = "";
        pass += getRandomLowerCase() + getRandomLowerCase() + getRandomLowerCase() + getRandomUpperCase() + getRandomUpperCase() + getRandomUpperCase() + getRandomNumber() + getRandomNumber();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(pass.getBytes());
        return bos;
    }

    private static String getRandomLowerCase() {
        String all = "qwertyuiopasdfghjklzxcvbnm";
        Random random = new Random();
        int n = random.nextInt(all.length());
        return String.valueOf(all.charAt(n));
    }

    private static String getRandomUpperCase() {
        String all = "QWERTYUIOPASDFGHJKLZXCVBNM";
        Random random = new Random();
        int n = random.nextInt(all.length());
        return String.valueOf(all.charAt(n));
    }

    private static String getRandomNumber() {
        String all = "q1234567890";
        Random random = new Random();
        int n = random.nextInt(all.length());
        return String.valueOf(all.charAt(n));
    }




}