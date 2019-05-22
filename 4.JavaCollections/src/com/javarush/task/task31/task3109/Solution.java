package com.javarush.task.task31.task3109;

import java.io.*;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties props = new Properties();
        try {
            File file = new File(fileName);
            String[] arr = fileName.split(File.separator);
            String shortName = arr[arr.length - 1];
            String ext = "";
            arr = shortName.split("\\.");
            if (arr.length > 1) {
                ext = arr[arr.length - 1];
            }
            if (ext.equals("xml")) {
                props.loadFromXML(new FileInputStream(file));
            } else {
                props.load(new FileReader(file));
            }
        } catch (Exception e) {

        }
        return props;
    }
}
