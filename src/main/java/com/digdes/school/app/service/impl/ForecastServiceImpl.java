package com.digdes.school.app.service.impl;

import com.digdes.school.app.model.Forecast;
import com.digdes.school.app.service.ForecastParser;
import com.digdes.school.app.service.ForecastService;
import com.digdes.school.app.service.HttpService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.lang.String.format;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * ...
 *
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
@SuppressWarnings("WeakerAccess")
@Service
public class ForecastServiceImpl implements ForecastService {
    private static final String WEATHER_URL_FORMAT = "http://informer.gismeteo.ru/xml/%d_1.xml";

    @Autowired
    private HttpService httpService;

    @Autowired
    private ForecastParser forecastParser;

    @Autowired
    private SessionFactory session;

    @Override
    public List<Forecast> getForecastsFromWebService(Long cityId) {
        String url = format(WEATHER_URL_FORMAT, cityId);
        String xml = httpService.getUrl(url);
        List<Forecast> forecasts = forecastParser.parse(xml);
        if (forecasts != null)
            for (Forecast forecast : forecasts)
                forecast.setCityId(cityId);
        return forecasts;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Forecast> getForecastsFromDb(Long cityId) {
        return (List<Forecast>) session.getCurrentSession()
                .createCriteria(Forecast.class)
                .add(eq("cityId", cityId)).list();
    }

    @Override
    @Transactional
    public void loadForecastsToDb(Long cityId) {
        List<Forecast> forecasts = getForecastsFromWebService(cityId);
        saveAll(forecasts);
    }

    @Override
    @Transactional
    public void saveAll(List<Forecast> forecasts) {
        forecasts.forEach(forecast -> session.getCurrentSession().save(forecast));
    }

    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    public void setForecastParser(ForecastParser forecastParser) {
        this.forecastParser = forecastParser;
    }

    public void setSession(SessionFactory session) {
        this.session = session;
    }
}
