package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];


    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] arr = new Solution[2];
        for (int i = 0; i <= 1; i++) {
            Solution s = new Solution();
            s.innerClasses[0] = s.new InnerClass();
            s.innerClasses[1] = s.new InnerClass();
            arr[i] = s;
        }
        return arr;
    }

    public static void main(String[] args) {

    }
}
