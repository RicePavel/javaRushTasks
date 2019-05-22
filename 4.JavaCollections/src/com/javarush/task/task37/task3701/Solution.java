package com.javarush.task.task37.task3701;

import java.util.ArrayList;
import java.util.Iterator;

/* 
Круговой итератор
*/
public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }

        Iterator iter = list.iterator();
        iter.next();
        iter.remove();
        iter.next();
        iter.remove();
        iter.next();


        System.out.println();
        count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator(this);
    }

    private Iterator<T> superIterator() {
        return super.iterator();
    }

    public class RoundIterator implements Iterator<T> {

        Iterator<T> superIterator;

        Solution solution;

        public RoundIterator(Solution solution) {
            this.solution = solution;
            this.superIterator = superIterator();
        }

        @Override
        public boolean hasNext() {
            return solution.size() > 0;
        }

        @Override
        public T next() {
            if (!superIterator.hasNext()) {
                superIterator = solution.superIterator();
            }
            return superIterator.next();
        }

        @Override
        public void remove() {
            superIterator.remove();
        }
    }
}
