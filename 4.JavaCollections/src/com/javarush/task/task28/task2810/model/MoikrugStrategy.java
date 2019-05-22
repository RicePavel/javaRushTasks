package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {

    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    private static final String host = "https://moikrug.ru";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            searchString = URLEncoder.encode(searchString, "UTF-8");
            int page = 0;
            while (true) {
                List<Vacancy> list = getVacanciesByParams(URL_FORMAT, searchString, page);
                if (list.isEmpty()) {
                    break;
                }
                vacancies.addAll(list);
                page++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return vacancies;
    }

    private List<Vacancy> getJavarushVacanciesByParams(String urlFormat, String searchString, int page) throws IOException {
        if (page > 0) {
            return new ArrayList<>();
        }
        String url = "https://javarush.ru/testdata/big28data.html";
        Document document = Jsoup.connect(url).userAgent("Mozilla/5.0").referrer("").get();
        return getVacancies(document);
    }

    private List<Vacancy> getVacanciesByParams(String urlFormat, String searchString, int page) throws IOException {
        String url = String.format(urlFormat, searchString, page);
        Document document = getDocument(searchString, page);
        return getVacancies(document);
    }

    private List<Vacancy> getVacancies(Document document) {
        List<Vacancy> vacancies = new ArrayList<>();
        Elements elements = document.getElementsByClass("job");
        for (Element vacancyElement: elements) {
            Vacancy vacancy = new Vacancy();
            Element titleLink = vacancyElement.select(".title a").first();
            if (titleLink != null) {
                vacancy.setTitle(titleLink.text());
            }
            Element salaryEl = vacancyElement.select(".salary .count").first();
            if (salaryEl != null) {
                vacancy.setSalary(salaryEl.text());
            } else {
                vacancy.setSalary("");
            }
            Element cityEl = vacancyElement.select(".location a").first();
            if (cityEl != null) {
                vacancy.setCity(cityEl.text());
            }
            Element employerEl = vacancyElement.select(".company_name").first();
            if (employerEl != null) {
                vacancy.setCompanyName(employerEl.text().trim());
            }
            vacancy.setSiteName(URL_FORMAT);
            String urlPage = host + vacancyElement.select(".title a").attr("href");
            vacancy.setUrl(urlPage);
            vacancies.add(vacancy);
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Mozilla/5.0").referrer("").get();
    }

}
