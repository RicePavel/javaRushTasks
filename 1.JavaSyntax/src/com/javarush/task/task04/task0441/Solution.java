package com.javarush.task.task04.task0441;


/* 
Как-то средненько
*/
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Integer a = scan.nextInt();
        Integer b = scan.nextInt();
        Integer c = scan.nextInt();
        int[] arr = {a, b, c};
        Arrays.sort(arr);
        System.out.println(arr[1]);
    }
}
