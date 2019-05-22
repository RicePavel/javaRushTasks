package com.javarush.task.task39.task3905;

public class PhotoPaint {

    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (image == null || desiredColor == null) {
            return false;
        }
        int height = image.length;
        int width = 0;
        if (image.length > 0) {
            width = image[0].length;
        }
        if (x > width - 1 || y > height - 1 || x < 0 || y < 0) {
            return false;
        }
        if (image[y][x].equals(desiredColor)) {
            return false;
        }
        image[y][x] = desiredColor;
        return true;
    }

}
