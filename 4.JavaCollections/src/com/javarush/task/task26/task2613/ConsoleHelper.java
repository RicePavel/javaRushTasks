package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH.replaceAll("\\.", "/") + "/common_en");;

    public static Operation askOperation() throws InterruptOperationException  {
        Operation o = null;
        writeMessage("выберите операцию: 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");
        String str = readString();
        o = getOperation(str);
        while (o == null) {
            writeMessage("неверные данные!");
            writeMessage("выберите операцию: 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");
            str = readString();
            o = getOperation(str);
        }
        return o;
    }

    private static Operation getOperation(String str) {
        try {
            return Operation.getAllowableOperationByOrdinal(Integer.valueOf(str));
        } catch (Exception e) {
            return null;
        }
    }

    public static String askCurrencyCode(String before, String invalidData) throws InterruptOperationException  {
        String str = "";
        writeMessage(before);
        str = readString();
        while (str.length() != 3) {
            writeMessage(invalidData);
            writeMessage(before);
            str = readString();
        }
        return str.toUpperCase();
    }

    public static String askCurrencyCode() throws InterruptOperationException  {
        String str = "";
        writeMessage("введите код валюты (3 символа):");
        str = readString();
        while (str.length() != 3) {
            writeMessage("неверные данные! код валюты должен содержать 3 символа");
            writeMessage("введите код валюты (3 символа):");
            str = readString();
        }
        return str.toUpperCase();
    }

    public static int askAmount() throws InterruptOperationException {
        writeMessage("Введите сумму");
        String str = readString();
        int amount = getAmount(str);
        while (amount <= 0) {
            writeMessage("Неверные данные! Сумма должна быть числом больше 0");
            writeMessage("Введите сумму");
            str = readString();
            amount = getAmount(str);
        }
        return amount;
    }

    private static int getAmount(String str) {
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
            return -1;
        }
    }

    public static String[] getValidTwoDigits(String currencyCode, String beforeMessage, String invalidDataMessage) throws InterruptOperationException {
        String[] arr = new String[2];
        String str = "";
        writeMessage(beforeMessage);
        str = readString();
        arr = getTwoDigits(str);
        while (arr == null) {
            writeMessage(invalidDataMessage);
            writeMessage(beforeMessage);
            str = readString();
            arr = getTwoDigits(str);
        }
        return arr;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] arr = new String[2];
        String str = "";
        writeMessage("введите номинал и количество банкнот (" + currencyCode + "):");
        str = readString();
        arr = getTwoDigits(str);
        while (arr == null) {
            writeMessage("неверные данные! введите два ыелых числа, разделенных пробелом");
            writeMessage("введите номинал и количество банкнот (" + currencyCode + "):");
            str = readString();
            arr = getTwoDigits(str);
        }
        return arr;
    }

    private static String[] getTwoDigits(String input) {
        String[] arr = input.split(" ");
        if (arr.length == 2) {
            try {
                Integer i1 = Integer.valueOf(arr[0]);
                Integer i2 = Integer.valueOf(arr[1]);
                return arr;
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage("До свидания!");
    }

    public static String readString() throws InterruptOperationException {
        try {
            String str = bis.readLine();
            if (str.equalsIgnoreCase("EXIT")) {
                throw new InterruptOperationException();
            }
            return str;
        } catch (IOException e) {
            e.printStackTrace(System.out);
            return "";
        }
    }

}
