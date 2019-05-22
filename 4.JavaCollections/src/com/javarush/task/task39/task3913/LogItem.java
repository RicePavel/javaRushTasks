package com.javarush.task.task39.task3913;

import java.util.Calendar;
import java.util.Date;

public class LogItem {

    private String ip;

    private String userName;

    private Date date;

    private Event event;

    private Integer taskNumber;

    private Status status;

    public LogItem(String ip, String userName, Date date, Event event, Integer taskNumber, Status status) {
        this.ip = ip;
        this.userName = userName;
        this.date = date;
        this.event = event;
        this.taskNumber = taskNumber;
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public String getUserName() {
        return userName;
    }

    public Date getDate() {
        return date;
    }

    public Event getEvent() {
        return event;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public Status getStatus() {
        return status;
    }


}
