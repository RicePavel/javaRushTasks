package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.List;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        List<Long> list = new ArrayList<>();
        for (int n = 1; n < N; n++) {
            String nStr = String.valueOf(n);
            int countSymbols = nStr.length();
            int result = 0;
            int number = n;
            while (number > 0) {
                int rest = number %  10;
                number = number / 10;
                result += Math.pow(rest, countSymbols);
            }
            if (result == n) {
                list.add( (long) n);
            }
        }
        long[] result = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
