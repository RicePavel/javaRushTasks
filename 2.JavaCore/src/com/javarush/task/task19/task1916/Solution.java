package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String name_1 = reader.readLine();
            String name_2 = reader.readLine();
            List<String> list_1 = new ArrayList<>();
            List<String> list_2 = new ArrayList<>();
            try (BufferedReader reader_1 = new BufferedReader(new FileReader(name_1))) {
                try (BufferedReader reader_2 = new BufferedReader(new FileReader(name_2))) {
                    String line;
                    while ((line = reader_1.readLine()) != null) {
                        list_1.add(line);
                    }
                    while ((line = reader_2.readLine()) != null) {
                        list_2.add(line);
                    }
                    int index_1 = 0;
                    int index_2 = 0;
                    while (true) {
                        LineItem item = null;
                        String line_1 = null;
                        String line_2 = null;
                        if (index_1 < list_1.size()) {
                            line_1 = list_1.get(index_1).trim();
                        }
                        if (index_2 < list_2.size()) {
                            line_2 = list_2.get(index_2).trim();
                        }
                        if (line_1 == null && line_2 == null) {
                            break;
                        } else if (line_1 == null) {
                            item = new LineItem(Type.ADDED, line_2);
                            lines.add(item);
                            break;
                        } else if (line_2 == null) {
                            item = new LineItem(Type.REMOVED, line_1);
                            lines.add(item);
                            break;
                        } else if (line_1 != null && line_2 != null) {
                            if (line_1.equals(line_2)) {
                                item = new LineItem(Type.SAME, line_1);
                                index_1++;
                                index_2++;
                            } else {
                                String next_1 = list_1.get(index_1 + 1).trim();
                                String next_2 = list_2.get(index_2 + 1).trim();
                                if (next_1.equals(line_2)) {
                                    item = new LineItem(Type.REMOVED, line_1);
                                    index_1++;
                                } else if (line_1.equals(next_2)) {
                                    item = new LineItem(Type.ADDED, line_2);
                                    index_2++;
                                }
                            }
                        }
                        lines.add(item);
                    }
                    int rrr = 4;
                }
            }
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
