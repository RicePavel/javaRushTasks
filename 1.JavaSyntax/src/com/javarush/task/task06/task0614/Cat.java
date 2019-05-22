package com.javarush.task.task06.task0614;

import java.util.ArrayList;

/* 
Статические коты
*/

public class Cat {
    //напишите тут ваш код
    public static ArrayList<Cat> cats = new ArrayList<>();

    public Cat() {
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            cats.add(new Cat());
        }
        printCats();
    }

    public static void printCats() {
        for (Cat cat: cats) {
            System.out.println(cat);
        }
    }
}
