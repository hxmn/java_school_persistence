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
 * @author Ilya Ashikhmin (ashikhmin.i@digdes.com)
 * Date: 12.04.17 17:08
 * Copyright Digital Design (http://digdes.com)
 */
@SuppressWarnings("WeakerAccess")
@Service
public class ForecastServiceImpl implements ForecastService {
    private static final String WEATHER_URL_FORMAT = "http://informer.gismeteo.ru/xml/%d_1.xml";

    @Autowired
    HttpService httpService;

    @Autowired
    ForecastParser forecastParser;

    @Autowired
    SessionFactory session;

    @Override
    public List<Forecast> getForecastsFromWebService(Long cityId) {
        String url = format(WEATHER_URL_FORMAT, cityId);
        String xml = httpService.getUrl(url);
        return forecastParser.parse(xml);
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
        forecasts.forEach(forecast -> {
            forecast.setCityId(cityId);
            session.getCurrentSession().save(forecast);
        });
    }
}
