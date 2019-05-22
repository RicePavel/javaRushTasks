package com.javarush.task.task08.task0824;

/* 
Собираем семейство
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        Human grandfather_1 = new Human();
        Human grandfather_2 = new Human();
        Human grandmother_1 = new Human();
        Human grandmother_2 = new Human();
        Human father = new Human();
        Human mother = new Human();
        Human child_1 = new Human();
        Human child_2 = new Human();
        Human child_3 = new Human();

        grandfather_1.children.add(father);
        grandmother_1.children.add(father);

        grandfather_2.children.add(mother);
        grandmother_2.children.add(mother);

        mother.children.add(child_1);
        mother.children.add(child_2);
        mother.children.add(child_3);

        father.children.add(child_1);
        father.children.add(child_2);
        father.children.add(child_3);

        System.out.println(grandfather_1);
        System.out.println(grandfather_2);
        System.out.println(grandmother_1);
        System.out.println(grandmother_2);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(child_1);
        System.out.println(child_2);
        System.out.println(child_3);
    }

    public static class Human {

        String name;
        boolean sex;
        int age;
        ArrayList<Human> children = new ArrayList<>();

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }

}
