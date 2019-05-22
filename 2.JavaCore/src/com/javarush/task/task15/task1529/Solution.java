package com.javarush.task.task15.task1529;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }
    
    static {
        try {
            reset();
        } catch (IOException e) {

        }
    }

    public static CanFly result;

    public static void reset() throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        try {
            String str = buf.readLine();
            if (str.equals("helicopter")) {
                result = new Helicopter();
            } else if (str.equals("plane")) {
                int i = Integer.valueOf(buf.readLine());
                result = new Plane(i);
            }
        } finally {
            buf.close();
        }
    }
}
