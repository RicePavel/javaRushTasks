package com.javarush.task.task05.task0510;

/* 
Кошкоинициация
*/

public class Cat {
    //напишите тут ваш код

    String name;
    int age;
    int weight;
    String address;
    String color;

    public void initialize(String name) {
        initialize(name, 1, 1, null, "1");
    }

    public void initialize(String name, int weight, int age) {
        initialize(name, age, weight, null, "1");
    }

    public void initialize(String name, int age) {
        initialize(name, age, 1, null, "1");
    }

    public void initialize(int weight, String color) {
        initialize(null, 1, weight, null, color);
    }

    public void initialize(int weight, String color, String address) {
        initialize(null, 1, weight, address, color);
    }


    public void initialize(String name, int age, int weight, String address, String color) {
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.address = address;
        this.color = color;
    }

    public static void main(String[] args) {

    }
}
