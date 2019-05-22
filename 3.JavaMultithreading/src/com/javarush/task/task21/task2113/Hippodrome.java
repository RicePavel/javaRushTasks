package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    public static Hippodrome game;

    private List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("qq", 3, 0));
        horses.add(new Horse("www", 3, 0));
        horses.add(new Horse("qeeq", 3, 0));
        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }

    public void run() throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (Horse h: horses) {
            h.move();
        }
    }

    public void print() {
        for (Horse h: horses) {
            h.print();
        }
        for (int i = 1; i <= 100; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        Horse winner = null;
        for (Horse h: horses) {
            if (winner == null) {
                winner = h;
            }
            if (h.getDistance() > winner.getDistance()) {
                winner = h;
            }
        }
        return winner;
    }

    public void printWinner() {
        Horse winner = getWinner();
        if (winner != null) {
            System.out.println("Winner is " + winner.getName() + "!");
        }
    }

    public List<Horse> getHorses() {
        return horses;
    }
}
