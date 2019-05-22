package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object o = Integer.valueOf(4);
        String str = (String) o;
    }

    public void methodThrowsNullPointerException() {
        String s = null;
        s.length();
    }

    public static void main(String[] args) {

    }
}
