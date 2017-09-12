package com.digdes.school.app.service;

import com.digdes.school.app.model.Forecast;

import java.util.List;

/**
 * Interface for forecast parsing component
 *
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
public interface ForecastParser {
    List<Forecast> parse(String xml);
}
