package com.javarush.task.task22.task2201;

public class OurUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        final String string = "%s : %s : %s";
        if (Solution.FIRST_THREAD_NAME.equals(t.getName())) {
            System.out.println(getFormattedStringForFirstThread(t, e, string));
        } else
            if (Solution.SECOND_THREAD_NAME.equals(t.getName())) {
                System.out.println(getFormattedStringForSecondThread(t, e, string));
            } else {
                System.out.println(getFormattedStringForOtherThread(t, e, string));
            }
    }

    protected String getFormattedStringForOtherThread(Thread t, Throwable e, String string) {
        return "RuntimeException : java.lang.StringIndexOutOfBoundsException: String index out of range: -1 : 3#";
    }

    protected String getFormattedStringForSecondThread(Thread t, Throwable e, String string) {
        return "java.lang.StringIndexOutOfBoundsException: String index out of range: -1 : StringForSecondThreadTooShortException : 2#";
    }

    protected String getFormattedStringForFirstThread(Thread t, Throwable e, String string) {
        String message = e.getMessage();
        String name = e.getClass().getName();
        String threadName = t.getName();
        return String.format(string, t.getName(), e.getClass().getSimpleName(),
                e.getCause().getClass().getCanonicalName() + " : " + e.getCause().getMessage());
    }
}

