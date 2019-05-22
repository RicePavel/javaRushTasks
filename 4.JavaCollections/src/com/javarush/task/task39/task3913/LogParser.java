package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {

    private List<LogItem> items = new ArrayList<>();

    public LogParser(Path logDir) {
        parseLogs(logDir);
    }

    private void parseLogs(Path path) {
        try {
            File directory = path.toFile();
            for (File f : directory.listFiles()) {
                if (f.isFile()) {
                    String name = f.getName();
                    String[] arr = name.split("\\.");
                    if (arr.length > 1 && arr[arr.length - 1].equals("log")) {
                       try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
                           String line;
                           while ((line = reader.readLine()) != null) {
                               LogItem item = parseLine(line);
                               if (item != null) {
                                   items.add(item);
                               }
                           }
                       }
                    }
                }
            }
        } catch (IOException | ParseException e) {

        }
    }

    private List<LogItem> getFilterList(Date after, Date before) {
        List<LogItem> list = new ArrayList<>();
        for (LogItem i: items) {
            boolean isAfter = (after == null || i.getDate().after(after));
            boolean isBefore = (before == null || i.getDate().before(before));
            if (isAfter && isBefore) {
                list.add(i);
            }
        }
        return list;
    }

    private List<LogItem> getFilterList(Date after, Date before, Event event) {
        return getFilterList(after, before, event, null);
    }

    private List<LogItem> getFilterList(Date after, Date before, Event event, String userName) {
        return getFilterList(after, before, event, userName, null);
    }

    private List<LogItem> getFilterList(Date after, Date before, Event event, String userName, Status status) {
        return getFilterList(after, before, event, userName, status,  null);
    }

    private List<LogItem> getFilterList(Date after, Date before, Event event, String userName, Status status, Integer task) {
        return getFilterList(after, before, event, userName, status,  task, null);
    }

    private List<LogItem> getFilterList(Date after, Date before, Event event, String userName, Status status, Integer task, String ip) {
        List<LogItem> list = new ArrayList<>();
        for (LogItem i: items) {
            boolean isAfter = (after == null || i.getDate().after(after));
            boolean isBefore = (before == null || i.getDate().before(before));
            boolean isEvent = (event == null || i.getEvent().equals(event));
            boolean isUserName = (userName == null || i.getUserName().equals(userName));
            boolean isStatus = (status == null || i.getStatus().equals(status));
            boolean isTask = (task == null || (i.getTaskNumber() != null && i.getTaskNumber().equals(task)));
            boolean isIp = (ip == null || (i.getIp().equals(ip)));
            if (isAfter && isBefore && isEvent && isUserName && isStatus && isTask && isIp) {
                list.add(i);
            }
        }
        return list;
    }

    private List<LogItem> getFilterList(Condition condition) {
        List<LogItem> list = new ArrayList<>();
        for (LogItem i: items) {
            boolean isDate = (condition.getDate() == null || i.getDate().equals(condition.getDate()));
            boolean isAfter = (condition.getAfter() == null || i.getDate().after(condition.getAfter()));
            boolean isBefore = (condition.getBefore() == null || i.getDate().before(condition.getBefore()));
            boolean isEvent = (condition.getEvent() == null || i.getEvent().equals(condition.getEvent()));
            boolean isUserName = (condition.getUser() == null || i.getUserName().equals(condition.getUser()));
            boolean isStatus = (condition.getStatus() == null || i.getStatus().equals(condition.getStatus()));
            boolean isTask = (condition.getTask() == null || (i.getTaskNumber() != null && i.getTaskNumber().equals(condition.getTask())));
            boolean isIp = (condition.getIp() == null || (i.getIp().equals(condition.getIp())));
            if (isDate && isAfter && isBefore && isEvent && isUserName && isStatus && isTask && isIp) {
                list.add(i);
            }
        }
        return list;
    }

    private LogItem parseLineByRegexp(String line) throws ParseException {
        LogItem item = null;
        Pattern p = Pattern.compile("([0-9]{1,3}[\\.]){3}[0-9]{1,3}");
        Matcher m = p.matcher(line);
        String ip = "";
        int endIpPos = 0;
        if (m.find()) {
            ip = m.group();
            endIpPos = m.end();
        } else {
            return null;
        }
        p = Pattern.compile("[0-9]{1,2}[\\.][0-9]{1,2}[\\.][0-9]{4}[\\s][0-9]{1,2}[:][0-9]{1,2}[:][0-9]{1,2}");
        m = p.matcher(line);
        String dateString = "";
        int startDatePos = 0;
        int endDatePos = 0;
        Date date;
        if (m.find()) {
            dateString = m.group();
            startDatePos = m.start();
            endDatePos = m.end();
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            date = formatter.parse(dateString);
        } else {
            return null;
        }
        String userName = line.substring(endIpPos, startDatePos).trim();
        String endLine = line.substring(endDatePos, line.length()).trim();
        String[] arr = endLine.split("\\s+");
        String eventString = "";
        String statusString = "";
        Integer taskNumber = null;
        Status status;
        Event event;
        eventString = arr[0];
        if (arr.length == 2) {
            statusString = arr[1];
        } else if (arr.length == 3) {
            taskNumber = Integer.valueOf(arr[1]);
            statusString = arr[2];
        }
        event = Event.valueOf(eventString);
        status = Status.valueOf(statusString);
        item = new LogItem(ip, userName, date, event, taskNumber, status);
        return item;
    }

    private LogItem parseLine(String line) throws ParseException {
        LogItem item = null;
        String[] arr = line.split("\\s+");
        String ip = arr[0];
        if (arr.length > 1) {
            String statusString = arr[arr.length - 1];
            Status status = Status.valueOf(statusString);
            int pos = arr.length - 2;
            String str = arr[pos];
            Event event = null;
            Integer taskNumber = null;
            if (str.matches("[0-9]+")) {
                taskNumber = Integer.valueOf(str);
                pos--;
                str = arr[pos];
                event = Event.valueOf(str);
            } else {
                event = Event.valueOf(str);
            }
            pos--;
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String dateString = arr[pos - 1] + " " + arr[pos];
            pos--;
            pos--;
            Date date = formatter.parse(dateString);
            String[] userNameArr = Arrays.copyOfRange(arr, 1, pos +1);
            String userName = String.join(" ", userNameArr);
            item = new LogItem(ip, userName, date, event, taskNumber, status);
        }
        return item;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<LogItem> list = getFilterList(after, before);
        for (LogItem i: list) {
            set.add(i.getIp());
        }
        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<LogItem> list = getFilterList(after, before);
        for (LogItem i: list) {
            if (i.getUserName().equals(user)) {
                set.add(i.getIp());
            }
        }
        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<LogItem> list = getFilterList(after, before);
        for (LogItem i: list) {
            if (i.getEvent().equals(event)) {
                set.add(i.getIp());
            }
        }
        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<LogItem> list = getFilterList(after, before);
        for (LogItem i: list) {
            if (i.getStatus().equals(status)) {
                set.add(i.getIp());
            }
        }
        return set;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> set = new HashSet<>();
        for (LogItem i: items) {
            set.add(i.getUserName());
        }
        return set;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<LogItem> list = getFilterList(after, before);
        for (LogItem i: list) {
            set.add(i.getUserName());
        }
        return set.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, null, user);
        Set<Event> events = new HashSet<>();
        for (LogItem i: list) {
            events.add(i.getEvent());
        }
        return events.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<LogItem> list = getFilterList(after, before);
        for (LogItem i: list) {
            if (i.getIp().equals(ip)) {
                set.add(i.getUserName());
            }
        }
        return set;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<LogItem> list = getFilterList(after, before, Event.LOGIN);
        for (LogItem i: list) {
            set.add(i.getUserName());
        }
        return set;
    }


    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<LogItem> list = getFilterList(after, before, Event.DOWNLOAD_PLUGIN);
        for (LogItem i: list) {
            set.add(i.getUserName());
        }
        return set;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<LogItem> list = getFilterList(after, before, Event.WRITE_MESSAGE);
        for (LogItem i: list) {
            set.add(i.getUserName());
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<LogItem> list = getFilterList(after, before, Event.SOLVE_TASK);
        for (LogItem i: list) {
            set.add(i.getUserName());
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> set = new HashSet<>();
        List<LogItem> list = getFilterList(after, before, Event.SOLVE_TASK);
        for (LogItem i: list) {
            if (i.getTaskNumber() != null && i.getTaskNumber().equals(task)) {
                set.add(i.getUserName());
            }
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        List<LogItem> list = getFilterList(after, before, Event.DONE_TASK);
        for (LogItem i: list) {
            set.add(i.getUserName());
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> set = new HashSet<>();
        List<LogItem> list = getFilterList(after, before, Event.DONE_TASK);
        for (LogItem i: list) {
            if (i.getTaskNumber() != null && i.getTaskNumber().equals(task)) {
                set.add(i.getUserName());
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, event, user);
        Set<Date> set = new HashSet<>();
        for (LogItem i: list) {
            set.add(i.getDate());
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, null, null, Status.FAILED);
        Set<Date> set = new HashSet<>();
        for (LogItem i: list) {
            set.add(i.getDate());
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, null, null, Status.ERROR);
        Set<Date> set = new HashSet<>();
        for (LogItem i: list) {
            set.add(i.getDate());
        }
        return set;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Date date = null;
        List<LogItem> list = getFilterList(after, before, Event.LOGIN, user, null);
        for (LogItem i: list) {
            if (date == null) {
                date = i.getDate();
            } else {
                if (i.getDate().before(date)) {
                    date = i.getDate();
                }
            }
        }
        return date;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Date date = null;
        List<LogItem> list = getFilterList(after, before, Event.SOLVE_TASK, user, null, task);
        for (LogItem i: list) {
            if (date == null) {
                date = i.getDate();
            } else {
                if (i.getDate().before(date)) {
                    date = i.getDate();
                }
            }
        }
        return date;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Date date = null;
        List<LogItem> list = getFilterList(after, before, Event.DONE_TASK, user, null, task);
        for (LogItem i: list) {
            if (date == null) {
                date = i.getDate();
            } else {
                if (i.getDate().before(date)) {
                    date = i.getDate();
                }
            }
        }
        return date;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, Event.WRITE_MESSAGE, user);
        Set<Date> set = new HashSet<>();
        for (LogItem i: list) {
            set.add(i.getDate());
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, Event.DOWNLOAD_PLUGIN, user);
        Set<Date> set = new HashSet<>();
        for (LogItem i: list) {
            set.add(i.getDate());
        }
        return set;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        List<LogItem> list = getFilterList(after, before);
        Set<Event> set = new HashSet<>();
        for (LogItem i: list) {
            set.add(i.getEvent());
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, null, null, null, null, ip);
        Set<Event> set = new HashSet<>();
        for (LogItem i: list) {
            set.add(i.getEvent());
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, null, user);
        Set<Event> set = new HashSet<>();
        for (LogItem i: list) {
            set.add(i.getEvent());
        }
        return set;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, null, null, Status.FAILED);
        Set<Event> set = new HashSet<>();
        for (LogItem i: list) {
            set.add(i.getEvent());
        }
        return set;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, null, null, Status.ERROR);
        Set<Event> set = new HashSet<>();
        for (LogItem i: list) {
            set.add(i.getEvent());
        }
        return set;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, Event.SOLVE_TASK, null, null, task);
        return list.size();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, Event.DONE_TASK, null, null, task);
        return list.size();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, Event.SOLVE_TASK);
        Map<Integer, Integer> map = new HashMap<>();
        for (LogItem i: list) {
            Integer task = i.getTaskNumber();
            if (!map.containsKey(task)) {
                map.put(task, 0);
            }
            map.put(task, map.get(task) + 1);
        }
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        List<LogItem> list = getFilterList(after, before, Event.DONE_TASK);
        Map<Integer, Integer> map = new HashMap<>();
        for (LogItem i: list) {
            Integer task = i.getTaskNumber();
            if (!map.containsKey(task)) {
                map.put(task, 0);
            }
            map.put(task, map.get(task) + 1);
        }
        return map;
    }

    private Set<Object> getSet(List<LogItem> list, String propertyName) {
        Set<Object> set = new HashSet<Object>();
        for (LogItem i: list) {
            switch (propertyName) {
                case "ip":
                    set.add(i.getIp());
                    break;
                case "user":
                    set.add(i.getUserName());
                    break;
                case "date":
                    set.add(i.getDate());
                    break;
                case "event":
                    set.add(i.getEvent());
                    break;
                case "status":
                    set.add(i.getStatus());
                    break;
            }
        }
        return set;
    }

    @Override
    public Set<Object> execute(String query) {
        try {
            Pattern pattern = Pattern.compile("get");
            Matcher m = pattern.matcher(query);
            if (m.find()) {
                int startGetSection = m.end();
                int endGetSection = query.length();
                int startForSection = 0;
                boolean existForSection = false;

                pattern = Pattern.compile("for");
                m = pattern.matcher(query);
                if (m.find()) {
                    existForSection = true;
                    endGetSection = m.start();
                    startForSection = m.end();
                }
                String getParameterName = query.substring(startGetSection, endGetSection).trim();
                List<LogItem> itemsList = items;
                if (existForSection) {
                    String forSection = query.substring(startForSection, query.length());
                    Condition andCondition = getEquallyCondition(forSection);
                    Condition betweenCondition = getBetweenCondition(forSection);
                    Condition condition = null;
                    if (andCondition != null || betweenCondition != null) {
                        if (andCondition != null) {
                            condition = andCondition;
                        }
                        if (condition != null && betweenCondition != null) {
                            condition.setAfter(betweenCondition.getAfter());
                            condition.setBefore(betweenCondition.getBefore());
                        }
                        if (condition == null) {
                            condition = betweenCondition;
                        }
                    }
                    if (condition != null) {
                        itemsList = getFilterList(condition);
                    }
                }
                return getSet(itemsList, getParameterName);
            }
        } catch (ParseException e) {

        }
        return new HashSet<>();
    }

    private Condition getBetweenCondition(String sectionFor) throws ParseException {
        Pattern pattern = Pattern.compile("date[\\s]+between[\\s]+\".+\"[\\s]+and[\\s]+\".+\"");
        Matcher m = pattern.matcher(sectionFor);
        if (m.find()) {
            String stringCondition = m.group();
            //pattern = Pattern.compile("\"[1-9\\.:\\s]+\"");
            pattern = Pattern.compile("\"(.+)\"[\\s]+and[\\s]+\"(.+)\"");
            m = pattern.matcher(stringCondition);
            String after = null;
            String before = null;
            if (m.find() && m.groupCount() == 2) {
                after = m.group(1);
                before = m.group(2);
            }
            if (after != null && before != null) {
                Condition condition = new Condition();
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                condition.setAfter(formatter.parse(after));
                condition.setBefore(formatter.parse(before));
                return condition;
            }
        }
        return null;
    }

    private Condition getEquallyCondition(String forSection) throws ParseException {
        Condition condition = new Condition();
        Pattern pattern = Pattern.compile("[\\w]+[\\s]+=[\\s]+\".+?\"");
        Matcher m = pattern.matcher(forSection);
        if (m.find()) {
            String stringCondition = m.group().trim();
            String[] arr = stringCondition.split("=");
            if (arr.length == 2) {
                String forName = arr[0].trim();
                String forValue = arr[1].trim();
                if (forValue.length() > 1 && forValue.charAt(0) == '"' && forValue.charAt(forValue.length() - 1) == '"') {
                    forValue = forValue.substring(1, forValue.length() - 1);
                }
                Object forValueObject = null;
                switch (forName) {
                    case "ip":
                        forValueObject = forValue;
                        condition.setIp((String) forValueObject);
                        break;
                    case "user":
                        forValueObject = forValue;
                        condition.setUser((String) forValueObject);
                        break;
                    case "date":
                        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                        forValueObject = formatter.parse(forValue);
                        condition.setDate((Date) forValueObject);
                        break;
                    case "event":
                        forValueObject = Event.valueOf(forValue);
                        condition.setEvent((Event) forValueObject);
                        break;
                    case "status":
                        forValueObject = Status.valueOf(forValue);
                        condition.setStatus((Status) forValueObject);
                        break;
                }
                if (forValueObject != null) {
                    return condition;
                }
            }
        }
        return null;
    }

}