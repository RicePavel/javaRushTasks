package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 0}
                            };
        System.out.println(maxSquare(matrix));
    }

    public static int maxSquare(int[][] matrix) {
        int maxSide = 0;
        int height = matrix.length;
        int width = 0;
        if (height > 0) {
            width = matrix[0].length;
        }
        int[][] checkedMatrix = new int[height][width];
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == 1) {
                    int side = 1;
                    int y2 = y + 1;
                    int x2 = x + 1;
                    while (y2 < height && x2 < width) {
                        boolean allOnes = allOnes(matrix, x, y, x2, y2);
                        if (allOnes) {
                            side++;
                            y2++;
                            x2++;
                        } else {
                            break;
                        }
                    }
                    if (side > maxSide) {
                        maxSide = side;
                    }
                }
            }
        }
        return maxSide * maxSide;
    }

    private static  boolean allOnes(int[][] matrix, int x1, int y1, int x2, int y2) {
        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                if (matrix[y][x] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
