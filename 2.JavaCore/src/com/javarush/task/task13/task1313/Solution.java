package com.javarush.task.task13.task1313;

import java.awt.*;

/* 
Лисица — это такое животное
*/

public class Solution {
    public static void main(String[] args) throws Exception {
    }

    public interface Animal {
        Color getName();
        Color getColor();
    }

    public abstract static class Fox implements Animal {
        public Color getName() {
            return null;
        }
    }
}