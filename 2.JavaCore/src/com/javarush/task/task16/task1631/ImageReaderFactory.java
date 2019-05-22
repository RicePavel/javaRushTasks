package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

public class ImageReaderFactory {

    public static ImageReader getImageReader(ImageTypes type) {
        if (type != null) {
            if (type.equals(ImageTypes.BMP)) {
                return new BmpReader();
            } else if (type.equals(ImageTypes.JPG)) {
                return new JpgReader();
            } else if (type.equals(ImageTypes.PNG)) {
                return new PngReader();
            }
        }
        throw new IllegalArgumentException();
    }

}
