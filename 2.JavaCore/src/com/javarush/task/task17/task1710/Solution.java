package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        if (args.length == 4 && args[0].equals("-c")) {
            String name = args[1];
            Sex sex = (args[2].equals("м") ? Sex.MALE : Sex.FEMALE);
            SimpleDateFormat f = new SimpleDateFormat("d/M/yyyy");
            Date date = f.parse(args[3]);
            Person person;
            if (sex.equals(Sex.MALE)) {
                person = Person.createMale(name, date);
            } else {
                person = Person.createFemale(name, date);
            }
            allPeople.add(person);
            System.out.println(allPeople.size() - 1);
        } else if (args.length == 5 && args[0].equals("-u")) {
            Integer id = Integer.valueOf(args[1]);
            String name = args[2];
            Sex sex = (args[3].equals("м") ? Sex.MALE : Sex.FEMALE);
            SimpleDateFormat f = new SimpleDateFormat("d/M/yyyy");
            Date date = f.parse(args[4]);
            Person person;
            if (sex.equals(Sex.MALE)) {
                person = Person.createMale(name, date);
            } else {
                person = Person.createFemale(name, date);
            }
            allPeople.set(id, person);
        } else if (args.length == 2 && args[0].equals("-d")) {
            Integer id = Integer.valueOf(args[1]);
            Person person = allPeople.get(id);
            person.setBirthDate(null);
            person.setName(null);
            person.setSex(null);
        } else if (args.length == 2 && args[0].equals("-i")) {
            Integer id = Integer.valueOf(args[1]);
            Person person = allPeople.get(id);
            String name = person.getName();
            String sex = (person.getSex().equals(Sex.MALE) ? "м" : "ж");
            SimpleDateFormat f = new SimpleDateFormat("d-MMM-yyyy");
            String date = f.format(person.getBirthDate());
            System.out.println(name + " " + sex + " " + date);
        }
    }
}
