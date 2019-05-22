package com.javarush.task.task26.task2613;


import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.command.LoginCommand;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.Map;

public class CashMachine {

    //public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName().replaceAll("\\.", "/");
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";

    public static void main(String[] args) {
        try {
            Locale.setDefault(Locale.ENGLISH);
            CommandExecutor.execute(Operation.LOGIN);
            Operation o = ConsoleHelper.askOperation();
            CommandExecutor.execute(o);
            while (!o.equals(Operation.EXIT)) {
                o = ConsoleHelper.askOperation();
                CommandExecutor.execute(o);
            }
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
    }
}
