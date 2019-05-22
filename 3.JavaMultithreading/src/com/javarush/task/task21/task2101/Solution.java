package com.javarush.task.task21.task2101;

/* 
Определяем адрес сети
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        if (ip.length == mask.length) {
            byte[] result = new byte[ip.length];
            for (int i = 0; i < ip.length; i++) {
                result[i] = (byte) (ip[i] & mask[i]);
            }
            return result;
        } else {
            return new byte[0];
        }
    }

    public static void print(byte[] bytes) {
        for (byte b: bytes) {
            String str = Integer.toBinaryString(b);
            if (str.length() > 8) {
                str = str.substring(str.length() - 8, str.length());
            }
            while (str.length() < 8) {
                str = "0" + str;
            }
            System.out.print(str + " ");
        }
        System.out.println();
    }
}
