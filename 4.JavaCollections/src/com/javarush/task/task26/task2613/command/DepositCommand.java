package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {

    //private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "/resources/deposit_en");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH.replaceAll("\\.", "/") + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException{
        ConsoleHelper.writeMessage(res.getString("before"));
        String curencyCode = ConsoleHelper.askCurrencyCode();
        String[] arr = ConsoleHelper.getValidTwoDigits(curencyCode);
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(curencyCode);
        String nominal = arr[0];
        String count = arr[1];
        manipulator.addAmount(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), Integer.parseInt(nominal), curencyCode));
    }

}
