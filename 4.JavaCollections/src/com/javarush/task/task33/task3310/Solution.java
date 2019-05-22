package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.FileStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.HashSet;
import java.util.Set;
import java.util.Date;

public class Solution {

    public static void main(String[] args) {
        //testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 100);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> set = new HashSet<>();
        for (String str: strings) {
            set.add(shortener.getId(str));
        }
        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> set = new HashSet<>();
        for (Long k: keys) {
            set.add(shortener.getString(k));
        }
        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();
        for (int i = 1; i <= elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        long ms1 = new Date().getTime();
        Set<Long> keys = getIds(shortener, strings);
        Long ms2 = new Date().getTime();
        Helper.printMessage(String.valueOf(ms2 - ms1));
        ms1 = new Date().getTime();
        Set<String> newStrings = getStrings(shortener, keys);
        ms2 = new Date().getTime();
        Helper.printMessage(String.valueOf(ms2 - ms1));
        if (strings.equals(newStrings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }

}
