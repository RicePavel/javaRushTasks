package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Неравноценный обмен
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Please enter a number: ");

        long number = Long.parseLong(reader.readLine());
        System.out.println("Please enter the first index: ");
        int i = Integer.parseInt(reader.readLine());
        System.out.println("Please enter the second index: ");
        int j = Integer.parseInt(reader.readLine());

        System.out.println("The result of swapping bits is " + swapBits(number, i, j));

    }

    public static long swapBits(long number, int i, int j) {
        byte b_i = (byte) ((number >> i) & 1);
        byte b_j = (byte) ((number >> j) & 1);
        if (b_j == 1) {
            number = (number | (1 << i));
        } else if (b_j == 0) {
            number = (number & ~(1 << i));
        }
        if (b_i == 1) {
            number = (number | (1 << j));
        } else if (b_i == 0) {
            number = (number & ~(1 << j));
        }
        return number;
    }

    /*
    public static long swapBits(long number, int i, int j) {
        byte b_i = getByteValue(number, i);
        byte b_j = getByteValue(number, j);
        number = setByte(number, i, b_j);
        number = setByte(number, j, b_i);
        return number;
    }

    private static byte getByteValue(long target, int numberOfByte) {
        return (byte) ((target >> numberOfByte) & 1);
    }

    private static long setByte(long target, int numberOfByte, byte b) {
        if (b == 1) {
            return (target | (1 << numberOfByte));
        } else if (b == 0) {
            return (target & ~(1 << numberOfByte));
        }
        return target;
    }

     */

}
