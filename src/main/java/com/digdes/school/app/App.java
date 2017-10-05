package com.digdes.school.app;

import com.digdes.school.app.model.Forecast;
import com.digdes.school.app.service.ForecastService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Main class for our application.
 */
public class App {
    public static final long SPB = 26063L;
    public static void main(String[] args) {
//        startWithXmlConfig();
        startWithAnnotationConfig();
    }

    public static void startWithAnnotationConfig() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

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

    public static void startWithXmlConfig() {

    }
}
