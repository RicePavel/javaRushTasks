package com.javarush.task.task08.task0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
Модернизация ПО
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, String> map = new HashMap<>();
        String city = "";
        String family = "";
        while (true) {
            String str = reader.readLine();
            if (str.isEmpty()) break;

            if (city.isEmpty()) {
                city = str;
            } else {
                family = str;
            }
            if (!city.isEmpty() && !family.isEmpty()) {
                map.put(city, family);
                city = "";
                family = "";
            }
        }

        String neededCity = reader.readLine();
        System.out.println(map.get(neededCity));

        /*
        // List of addresses
        List<String> addresses = new ArrayList<>();
        while (true) {
            String family = reader.readLine();
            if (family.isEmpty()) break;

            addresses.add(family);
        }

        // Read the house number
        int houseNumber = Integer.parseInt(reader.readLine());

        if (0 <= houseNumber && houseNumber < addresses.size()) {
            String familyName = addresses.get(houseNumber);
            System.out.println(familyName);
        }
         */
    }
}
