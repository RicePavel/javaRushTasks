package com.javarush.task.task19.task1905;

import java.util.*;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {

    }

    public static class DataAdapter implements  RowItem {

        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            String countryName = customer.getCountryName();
            for (String key: countries.keySet()) {
                String value = countries.get(key);
                if (value.equals(countryName)) {
                    return key;
                }
            }
            return "";
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            String fullName = contact.getName();
            String[] arr = fullName.split(" ");
            return arr[1];
        }

        @Override
        public String getContactLastName() {
            String fullName = contact.getName();
            String[] arr = fullName.split(" ");
            String firstName = arr[0];
            firstName = firstName.substring(0, firstName.length() - 1);
            return firstName;
        }

        @Override
        public String getDialString() {
            String number = contact.getPhoneNumber();
            String newNumber = "";
            String[] arr = {"(", ")", "-"};
            List<String> list = Arrays.asList(arr);
            for (int i = 0; i < number.length(); i++) {
                String s = String.valueOf(number.charAt(i));
                if (!list.contains(s)) {
                    newNumber += s;
                }
            }
            return "callto://" + newNumber;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //For example: UA
        String getCompany();            //For example: JavaRush Ltd.
        String getContactFirstName();   //For example: Ivan
        String getContactLastName();    //For example: Ivanov
        String getDialString();         //For example: callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.
        String getCountryName();        //For example: Ukraine
    }

    public static interface Contact {
        String getName();               //For example: Ivanov, Ivan
        String getPhoneNumber();        //For example: +38(050)123-45-67 or +3(805)0123-4567 or +380(50)123-4567 or ...
    }
}