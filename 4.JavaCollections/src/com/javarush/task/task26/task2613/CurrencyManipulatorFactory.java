package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {

    private CurrencyManipulatorFactory() {

    }

    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (!map.containsKey(currencyCode)) {
            map.put(currencyCode, new CurrencyManipulator(currencyCode));
        }
        return map.get(currencyCode);
    }

}
