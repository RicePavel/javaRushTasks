package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.FileReader;
import java.io.FileWriter;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        FileReader reader = new FileReader("d/fghdfhdf.rrr");
        reader.read();
    }

    public static void main(String[] args) throws Exception {
        new VeryComplexClass().veryComplexMethod();
    }
}
