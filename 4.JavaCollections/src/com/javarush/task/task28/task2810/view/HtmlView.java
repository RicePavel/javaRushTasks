package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {

    private Controller controller;

    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    @Override
    public void update(List<Vacancy> vacancies) {
        String str = getUpdatedFileContent(vacancies);
        updateFile(str);
    }

    private String getUpdatedFileContent(List<Vacancy> list) {
        try {
            Document doc = getDocument();
            Element template = doc.getElementsByClass("template").first();
            Element clone = template.clone();
            clone.removeAttr("style");
            clone.removeClass("template");
            Elements elements = doc.select(".vacancy");
            for (Element el : elements) {
                if (!el.hasClass("template")) {
                    el.remove();
                }
            }
            for (Vacancy vacancy : list) {
                Element newElement = clone.clone();
                Element a = newElement.select(".title a").first();
                a.text(vacancy.getTitle());
                a.attr("href", vacancy.getUrl());
                newElement.select(".city").first().text(vacancy.getCity());
                newElement.select(".companyName").first().text(vacancy.getCompanyName());
                newElement.select(".salary").first().text(vacancy.getSalary());
                template.before(newElement);
            }
            return doc.html();
        } catch (Exception e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
    }

    private void updateFile(String str) {
        try {
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(str);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected Document getDocument() {
        try {
            return Jsoup.parse(new File(filePath), "UTF-8");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
