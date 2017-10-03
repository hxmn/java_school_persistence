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
        List<Forecast> forecasts = fs.getForecastsFromWebService(SPB);
    }

    public static void startWithXmlConfig() {

    }
}
