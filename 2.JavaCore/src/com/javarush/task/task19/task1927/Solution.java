package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(byteArrayOutputStream);
        PrintStream consoleStream = System.out;
        System.setOut(stream);
        testString.printSomething();
        String content = byteArrayOutputStream.toString();
        String[] lines = content.split("\\n");
        System.setOut(consoleStream);
        int i = 1;
        for (String line: lines) {
            System.out.println(line);
            if (i % 2 == 0) {
                System.out.println("JavaRush - курсы Java онлайн");
            }
            i++;
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
