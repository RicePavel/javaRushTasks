package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.model.MoikrugStrategy;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.view.HtmlView;
import com.javarush.task.task28.task2810.view.View;

import java.io.IOException;

public class Aggregator {

    public static void main(String[] args) {
        HtmlView view = new HtmlView();
        String test = "1111";
        Provider p1 = new Provider(new HHStrategy());
        Provider p = new Provider(new MoikrugStrategy());
        Model model = new Model(view, p1, p);
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();
    }
}
