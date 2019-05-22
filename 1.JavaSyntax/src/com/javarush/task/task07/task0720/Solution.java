package com.javarush.task.task07.task0720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Перестановочка подоспела
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        int n = Integer.valueOf(reader.readLine());
        int m = Integer.valueOf(reader.readLine());
        for (int i = 1; i <= n; i++) {
            list.add(reader.readLine());
        }

        for (int i = 1; i <= m; i++) {
            String s = list.remove(0);
            list.add(s);
        }

        for (String str: list) {
            System.out.println(str);
        }
    }
}
