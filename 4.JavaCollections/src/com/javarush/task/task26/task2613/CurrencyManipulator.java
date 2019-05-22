package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {

    private String currencyCode;

    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Entry entry = withdrawal(new HashMap<>(denominations), expectedAmount);
        if (entry != null) {
            denominations = entry.rest;
            return entry.exchange;
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    private Entry withdrawal(Map<Integer, Integer> rest, int amount) {
        Map<Integer, Integer> exchange = new HashMap<>();
        List<Integer> nominals = new ArrayList<>(rest.keySet());
        Collections.sort(nominals, Collections.reverseOrder());
        for (int nominal: nominals) {
            int maxCountBills = amount / nominal;
            if (maxCountBills <= 0) {
                continue;
            }
            for (int countBills = maxCountBills; countBills > 0; countBills--) {
                if (countBills > rest.get(nominal)) {
                    continue;
                }
                int restAmount = amount - (nominal * countBills);

                if (restAmount == 0) {
                    Entry entry = new Entry();
                    entry.rest = new HashMap<>(rest);
                    entry.rest.put(nominal, entry.rest.get(nominal) - countBills);
                    if (entry.rest.get(nominal) == 0) {
                        entry.rest.remove(nominal);
                    }
                    entry.exchange = new HashMap<>();
                    entry.exchange.put(nominal, countBills);
                    return entry;
                } else {
                    Map<Integer, Integer> newRest = new HashMap<>(rest);
                    newRest.put(nominal, newRest.get(nominal) - countBills);
                    if (newRest.get(nominal) == 0) {
                        newRest.remove(nominal);
                    }
                    Entry entry = withdrawal(newRest, restAmount);
                    if (entry != null) {
                        Entry newEntry = new Entry();
                        newEntry.exchange = new HashMap<>(entry.exchange);
                        if (!newEntry.exchange.containsKey(nominal)) {
                            newEntry.exchange.put(nominal, 0);
                        }
                        newEntry.exchange.put(nominal, newEntry.exchange.get(nominal) + countBills);
                        newEntry.rest = new HashMap<>(entry.rest);
                        return newEntry;
                    }
                }
            }
        }
        return null;
    }

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }

    public int getTotalAmount() {
        int amount = 0;
        for (int denomination: denominations.keySet()) {
            amount += denomination * denominations.get(denomination);
        }
        return amount;
    }

    public void addAmount(int denomination, int count) {
        if (!denominations.containsKey(denomination)) {
            denominations.put(denomination, 0);
        }
        denominations.put(denomination, denominations.get(denomination) + count);
    }

    private static class Entry {

        Map<Integer, Integer> exchange;

        Map<Integer, Integer> rest;

    }

}
