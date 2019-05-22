package com.javarush.task.task36.task3602;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class collectionsCl = Collections.class;
        Class[] classes = collectionsCl.getDeclaredClasses();
        Class found = null;
        for (Class cl: classes) {
            if (List.class.isAssignableFrom(cl)) {
                try {
                    List list = null;
                    Constructor[] constructors = cl.getDeclaredConstructors();
                    for (Constructor cnstr: constructors) {
                        if (cnstr.getParameterCount() == 0) {
                            cnstr.setAccessible(true);
                            list = (List) cnstr.newInstance();
                        }
                    }
                    if (list != null) {
                        list.get(0);
                    }
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    int i = 4343434;
                } catch (IndexOutOfBoundsException e) {
                    found = cl;
                    break;
                }
            }
        }
        return found;
    }
}
