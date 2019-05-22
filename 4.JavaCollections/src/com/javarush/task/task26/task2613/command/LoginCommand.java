package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private final String cardnumber = "123456789012";

    private final String pin = "1234";

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH.replaceAll("\\.", "/") + "login_en");;

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH.replaceAll("\\.", "/") + "verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String s1 = ConsoleHelper.readString();
            String s2 = ConsoleHelper.readString();
            if (validCreditCards.containsKey(s1)) {
                if (validCreditCards.getString(s1).equals(s2)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), s1));
                    break;
                } else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), s1));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), s1));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }
    }

    /*
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String number = null;
        while (number == null) {
            number = login();
        }
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), number));
    }

    private String login() throws InterruptOperationException  {
        String[] arr = getNumbers();
        if (arr == null) {
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            return null;
        }
        String number = arr[0];
        String pin = arr[1];
        if (validCreditCards.containsKey(number) && validCreditCards.getString(number).equals(pin)) {
            return number;
        }
        ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), number));
        return null;
    }

    private String[] getNumbers() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        String str1 = ConsoleHelper.readString();
        String str2 = ConsoleHelper.readString();
            try {
                boolean b1 = str1.matches("[0-9]+");
                boolean b2 = str2.matches("[0-9]+");
                if (b1 && b2) {
                    return new String[]{str1, str2};
                }
            } catch (Exception e) {

            }
        return null;
    }
     */


}
