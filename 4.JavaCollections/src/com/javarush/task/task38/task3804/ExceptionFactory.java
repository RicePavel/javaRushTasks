package com.javarush.task.task38.task3804;

public class ExceptionFactory {

    public static Throwable getException(Enum o) {
        String message = "";
        if (o != null) {
            message = o.name();
            message = message.replaceAll("_", " ");
            message = message.charAt(0) + message.substring(1, message.length()).toLowerCase();
        }
        if (o instanceof ApplicationExceptionMessage) {
            ApplicationExceptionMessage en = (ApplicationExceptionMessage) o;
            return new Exception(message);
        } else if (o instanceof DatabaseExceptionMessage) {
            DatabaseExceptionMessage en = (DatabaseExceptionMessage) o;
            return new RuntimeException(message);
        } else if (o instanceof UserExceptionMessage) {
            UserExceptionMessage en = (UserExceptionMessage) o;
            return new Error(message);
        } else {
            return new IllegalArgumentException();
        }
    }



}
