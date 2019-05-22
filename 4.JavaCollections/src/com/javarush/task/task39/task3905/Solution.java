package com.javarush.task.task39.task3905;

/* 
Залей меня полностью
*/

public class Solution {
    public static void main(String[] args) {

        Color[][] image = {
                {Color.BLUE, Color.BLUE, Color.BLUE},
                {Color.BLUE, Color.BLUE, Color.BLUE},
                {Color.BLUE, Color.BLUE, Color.BLUE}
        };
        PhotoPaint paint = new PhotoPaint();
        boolean result = paint.paintFill(image, 2, 1, Color.GREEN);
        int r = 4;;

    }
}
