package com.javarush.task.task14.task1408;

class UkrainianHen extends Hen {
    @Override
    int getCountOfEggsPerMonth() {
        return 0;
    }
    String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.UKRAINE + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
