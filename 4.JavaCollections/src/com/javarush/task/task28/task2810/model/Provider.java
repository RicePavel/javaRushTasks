package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Provider {

    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public Provider() {

    }

    public List<Vacancy> getJavaVacancies(String searchString) throws IOException {
        if (strategy != null) {
            return strategy.getVacancies(searchString);
        } else {
            return new ArrayList<>();
        }
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
