package com.javarush.task.task14.task1411;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 
User, Loser, Coder and Proger
*/

public class Solution {

    public static final String user = "user";
    public static final String loser = "loser";
    public static final String coder = "coder";
    public static final String proger = "proger";

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = null;
        String key = null;

        String[] arr = {user, loser, coder, proger};
        while (true) {
            key = reader.readLine();
            if (!Arrays.asList(arr).contains(key)) {
                break;
            }
            switch (key) {
                case user:
                    person = new Person.User();
                    break;
                case loser:
                    person = new Person.Loser();
                    break;
                case coder:
                    person = new Person.Coder();
                    break;
                case proger:
                    person = new Person.Proger();
                    break;
            }
            doWork(person);
        }
    }

    public static void doWork(Person person) {
        if (person instanceof Person.User) {
            Person.User n = (Person.User) person;
            n.live();
        } else if (person instanceof Person.Loser) {
            Person.Loser n = (Person.Loser) person;
            n.doNothing();
        } else if (person instanceof Person.Coder) {
            Person.Coder n = (Person.Coder) person;
            n.writeCode();
        } else if (person instanceof Person.Proger) {
            Person.Proger n = (Person.Proger) person;
            n.enjoy();
        }
    }
}
