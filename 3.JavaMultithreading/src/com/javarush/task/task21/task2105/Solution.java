package com.javarush.task.task21.task2105;

import java.util.HashSet;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Solution))
            return false;
        if (o == this) {
            return true;
        }
        Solution s = (Solution) o;
        boolean equaslFirst = (first == null && s.first == null) || (first != null && s.first != null && first.equals(s.first));
        boolean equalsLast = (last == null && s.last == null) || (last != null && s.last != null && last.equals(s.last));
        boolean result = equaslFirst && equalsLast;
        return result;
    }

    public int hashCode() {
        int hash = 0;
        if (first != null) {
            hash += first.hashCode();
        }
        if (last != null) {
            hash += last.hashCode();
        }
        return hash;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }
}
