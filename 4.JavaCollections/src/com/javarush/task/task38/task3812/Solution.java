package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest p = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            String[] names = p.fullyQualifiedNames();
            for (String s: names) {
                System.out.println(s);
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean printValues(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest p = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            Class[] names = p.value();
            for (Class s: names) {
                System.out.println(s.getSimpleName());
            }
            return true;
        } else {
            return false;
        }
    }
}
