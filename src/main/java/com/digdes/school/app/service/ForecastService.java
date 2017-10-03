package com.digdes.school.app.service;

import com.digdes.school.app.model.Forecast;

import java.util.List;

/**
 * Interface for Forecast Service
 *
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
public interface ForecastService {
    List<Forecast> getForecastsFromWebService(Long cityId);

    List<Forecast> getForecastsFromDb(Long cityId);

    void loadForecastsToDb(Long cityId);
}
