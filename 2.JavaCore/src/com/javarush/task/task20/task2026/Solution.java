package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int rectangleCount =0;
        int N = a.length;
        byte[][] checkedArr = new byte[N][N];
        for (int i =0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                checkedArr[i][j] = 0;
            }
        }
        for (int i =0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (checkedArr[i][j] == 1) {
                    continue;
                } else {
                    if (a[i][j] == 1) {
                        rectangleCount++;
                        for (int n = i; n < N; n++) {
                            if (a[n][j] == 0) {
                                break;
                            }
                            for (int m = j; m < N; m++) {
                                if (a[n][m] == 1) {
                                    checkedArr[n][m] = 1;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return rectangleCount;
    }
}
