package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

class WithdrawCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH.replaceAll("\\.", "/") + "withdraw_en");;


    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int amount = askAmount();
        Map<Integer, Integer> money = getMoney(amount, manipulator);
        while (money == null) {
            amount = askAmount();
            money = getMoney(amount, manipulator);
        }
        List<Integer> nominals = new ArrayList<>(money.keySet());
        Collections.sort(nominals, Collections.reverseOrder());
        for (Integer nominal: nominals) {
            System.out.println("\t" + nominal + " - " + money.get(nominal));
        }
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, currencyCode));
    }

    private  int askAmount() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("specify.amount"));
        String str = ConsoleHelper.readString();
        int amount = getAmount(str);
        while (amount <= 0) {
            ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            str = ConsoleHelper.readString();
            amount = getAmount(str);
        }
        return amount;
    }

    private  int getAmount(String str) {
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
            return -1;
        }
    }

    private Map<Integer, Integer> getMoney(int amount, CurrencyManipulator manipulator) {
        if (!manipulator.isAmountAvailable(amount)) {
            ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            return null;
        }
        try {
            Map<Integer, Integer> money = manipulator.withdrawAmount(amount);
            return money;
        } catch (NotEnoughMoneyException e) {
            ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            return null;
        }
    }

}
