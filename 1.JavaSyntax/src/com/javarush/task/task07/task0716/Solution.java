package com.javarush.task.task07.task0716;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* 
Р или Л
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<String>();
        list.add("роза"); // 0
        list.add("лоза"); // 1
        list.add("лира"); // 2
        list = fix(list);

        for (String s : list) {
            System.out.println(s);
        }
    }

    public static ArrayList<String> fix(ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<>();
        for (String s : list) {
            if (s.indexOf("р") != -1 && s.indexOf("л") != -1) {
                newList.add(s);
            } else if (s.indexOf("р") != -1) {

            } else if (s.indexOf("л") != -1) {
                newList.add(s);
                newList.add(s);
            } else {
                newList.add(s);
            }
        }
        return newList;
    }
}