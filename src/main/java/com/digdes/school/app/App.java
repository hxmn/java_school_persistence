package com.digdes.school.app;

import com.digdes.school.app.model.Forecast;
import com.digdes.school.app.service.ForecastService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Main class for our application.
 */
public class App {
    public static final long SPB = 26063L;

    public static void main(String[] args) {
//        AbstractApplicationContext ctx = getAnnotationContext();
        AbstractApplicationContext ctx = getXmlContext();
        ForecastService fs = ctx.getBean(ForecastService.class);

        System.out.println("Getting forecasts from Web Service.");
        List<Forecast> forecasts = fs.getForecastsFromWebService(SPB);
        for (Forecast forecast : forecasts) {
            System.out.println(forecast);
        }

        System.out.println("Saving it to database.");
        fs.saveAll(forecasts);

        System.out.println("Load all forecasts from DataBase.");
        forecasts = fs.getForecastsFromDb(SPB);
        for (Forecast forecast : forecasts) {
            System.out.println(forecast);
        }
    }

    private static AbstractApplicationContext getXmlContext() {
        return new ClassPathXmlApplicationContext("sprint-ctx.xml");

    }

    private static AbstractApplicationContext getAnnotationContext() {
        return new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
