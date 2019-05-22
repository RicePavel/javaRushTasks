package com.javarush.task.task06.task0621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Родственные связи кошек
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String n1 = reader.readLine();
        Cat grandfather = new Cat(n1, null, null);

        String n2 = reader.readLine();
        Cat grandmother = new Cat(n2, null, null);

        String n3 = reader.readLine();
        Cat father = new Cat(n3, grandfather, null);

        String n4 = reader.readLine();
        Cat mother = new Cat(n4, null, grandmother);

        String n5 = reader.readLine();
        Cat son = new Cat(n5, father, mother);

        String n6 = reader.readLine();
        Cat daughter = new Cat(n6, father, mother);

        System.out.println(grandfather);
        System.out.println(grandmother);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(son);
        System.out.println(daughter);
    }

    public static class Cat {
        private String name;
        private Cat mom;
        private Cat dad;

        Cat(String name) {
            this.name = name;
        }

        Cat(String name, Cat dad, Cat mom) {
            this.name = name;
            this.mom = mom;
            this.dad = dad;
        }

        @Override
        public String toString() {
            String str = "The cat's name is " + name + ", ";
            if (mom == null) {
                str += "no mother, ";
            } else {
                str += "mother is " + mom.name;
            }
            if (dad == null) {
                str += "no father, ";
            } else {
                str += "father is " + dad.name;
            }
            return str;
        }
    }

}
