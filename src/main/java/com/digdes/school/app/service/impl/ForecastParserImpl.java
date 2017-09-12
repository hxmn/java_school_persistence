package com.digdes.school.app.service.impl;

import com.digdes.school.app.model.Forecast;
import com.digdes.school.app.service.ForecastParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.json.XML.toJSONObject;

/**
 * ...
 *
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
@Service
public class ForecastParserImpl implements ForecastParser {
    @Override
    public List<Forecast> parse(String xml) {
        JSONObject json = toJSONObject(xml)
                .getJSONObject("MMWEATHER")
                .getJSONObject("REPORT")
                .getJSONObject("TOWN");

        List<Forecast> forecasts = new ArrayList<>();

        //parse forecasts
        JSONArray jsonForecasts = json.getJSONArray("FORECAST");
        for (int i = 0; i < jsonForecasts.length(); i++) {
            JSONObject j = jsonForecasts.getJSONObject(i);
            Forecast forecast = new Forecast();

            //date
            Calendar calendar = new GregorianCalendar(
                    j.getInt("year"), j.getInt("month") - 1, j.getInt("day"), j.getInt("hour"), 0);
            forecast.setDate(calendar.getTime());

            //wind
            forecast.setWindDirection(j.getJSONObject("WIND").getInt("direction"));
            forecast.setWindMin(j.getJSONObject("WIND").getInt("min"));
            forecast.setWindMax(j.getJSONObject("WIND").getInt("max"));

            //phenomena
            forecast.setPrecipitation(j.getJSONObject("PHENOMENA").getInt("precipitation"));
            forecast.setRPower(j.getJSONObject("PHENOMENA").getInt("rpower"));
            forecast.setSPower(j.getJSONObject("PHENOMENA").getInt("spower"));
            forecast.setCloudiness(j.getJSONObject("PHENOMENA").getInt("cloudiness"));

            //relwet
            forecast.setRelWetMin(j.getJSONObject("RELWET").getInt("min"));
            forecast.setRelWetMax(j.getJSONObject("RELWET").getInt("max"));

            forecast.setPressureMin(j.getJSONObject("PRESSURE").getInt("min"));
            forecast.setPressureMax(j.getJSONObject("PRESSURE").getInt("max"));

            forecast.setHeatMin(j.getJSONObject("HEAT").getInt("min"));
            forecast.setHeatMax(j.getJSONObject("HEAT").getInt("max"));

            forecast.setTemperatureMin(j.getJSONObject("TEMPERATURE").getInt("min"));
            forecast.setTemperatureMax(j.getJSONObject("TEMPERATURE").getInt("max"));
            forecasts.add(forecast);
        }
        return forecasts;
    }

}
