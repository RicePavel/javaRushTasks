package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {


    public void recurse(int n) {
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                int res = n / i;
                System.out.print(i + " ");
                recurse(res);
                break;
            }
        }
    }
}
