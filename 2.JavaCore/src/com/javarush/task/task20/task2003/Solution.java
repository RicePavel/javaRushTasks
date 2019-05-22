package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.*;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        FileInputStream fis = new FileInputStream(fileName);
        load(fis);
    }

    public void save(OutputStream outputStream) throws Exception {
        Properties prop = new Properties();
        for (String name: properties.keySet()) {
            prop.setProperty(name, properties.get(name));
        }
        prop.store(outputStream, "");
    }

    public void load(InputStream inputStream) throws Exception {
        Properties property = new Properties();
        property.load(inputStream);
        Set<String> en =  property.stringPropertyNames();
        for (String name: en) {
            properties.put(name, property.getProperty(name));
        }
    }

    public static void main(String[] args) {

    }
}
