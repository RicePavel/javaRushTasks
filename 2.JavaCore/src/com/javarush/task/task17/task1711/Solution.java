package com.javarush.task.task17.task1711;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        if (args.length == 0) {
            return;
        }
        int length = args.length - 1;
        int count = 0;
        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    if (length % 3 != 0) {
                        return;
                    }
                    count = length / 3;
                    for (int i = 1; i <= count; i++) {
                        int startIndex = ((i - 1) * 3) + 1;
                        String name = args[startIndex];
                        Sex sex = (args[startIndex + 1].equals("м") ? Sex.MALE : Sex.FEMALE);
                        SimpleDateFormat f = new SimpleDateFormat("d/M/yyyy");
                        Date date = f.parse(args[startIndex + 2]);
                        Person person;
                        if (sex.equals(Sex.MALE)) {
                            person = Person.createMale(name, date);
                        } else {
                            person = Person.createFemale(name, date);
                        }
                        allPeople.add(person);
                        System.out.println(allPeople.size() - 1);
                    }
                }
                break;
            case "-u":
                synchronized (allPeople) {
                    if (length % 4 != 0) {
                        return;
                    }
                    count = length / 4;
                    for (int i = 1; i <= count; i++) {
                        int startIndex = ((i - 1) * 4) + 1;
                        Integer id = Integer.valueOf(args[startIndex]);
                        String name = args[startIndex + 1];
                        Sex sex = (args[startIndex + 2].equals("м") ? Sex.MALE : Sex.FEMALE);
                        SimpleDateFormat f = new SimpleDateFormat("d/M/yyyy");
                        Date date = f.parse(args[startIndex + 3]);
                        Person person;
                        if (sex.equals(Sex.MALE)) {
                            person = Person.createMale(name, date);
                        } else {
                            person = Person.createFemale(name, date);
                        }
                        allPeople.set(id, person);
                    }
                }
                break;
            case "-d":
                synchronized (allPeople) {
                    count = length;
                    for (int i = 1; i <= count; i++) {
                        Integer id = Integer.valueOf(args[i]);
                        Person person = allPeople.get(id);
                        person.setBirthDate(null);
                        person.setName(null);
                        person.setSex(null);
                    }
                }
                break;
            case "-i":
                synchronized (allPeople) {
                    count = length;
                    for (int i = 1; i <= count; i++) {
                        Integer id = Integer.valueOf(args[i]);
                        Person person = allPeople.get(id);
                        String name = person.getName();
                        String sex = (person.getSex().equals(Sex.MALE) ? "м" : "ж");
                        SimpleDateFormat f = new SimpleDateFormat("d-MMM-yyyy");
                        String date = f.format(person.getBirthDate());
                        System.out.println(name + " " + sex + " " + date);
                    }
                }
                break;
        }
    }
}
