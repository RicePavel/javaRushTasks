package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;


public class Solution {

    public static void main(String[] args) throws IOException {
        String tag = args[0];
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            String fileName = consoleReader.readLine();
            try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
                String content = "";
                String line;
                while ((line = fileReader.readLine()) != null) {
                    content += line + " ";
                }
                Document doc = Jsoup.parseBodyFragment(content);
                Elements elements = doc.getElementsByTag(tag);
                for (Element element: elements) {
                    System.out.println(element.outerHtml());
                }
            }
        }
    }
}
