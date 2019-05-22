package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object n) {
        if (n == this) {
            return true;
        }
        if (n instanceof Solution) {
            Solution s = (Solution) n;
            boolean equaslFirst = (first == null && s.first == null) || (first != null && s.first != null && first.equals(s.first));
            boolean equalsLast = (last == null && s.last == null) || (last != null && s.last != null && last.equals(s.last));
            boolean result = equaslFirst && equalsLast;
            return result;
        } else {
            return false;
        }
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
        s.add(new Solution("Donald", "Duck"));
        Solution s1 = new Solution("Donald", "Duck");
        Solution s2 = new Solution("Donald1", "Duck");
        s.add(s1);
        s.add(s2);
        System.out.println(s1.equals(s2));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
