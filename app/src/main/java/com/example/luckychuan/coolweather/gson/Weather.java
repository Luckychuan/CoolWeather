package com.example.luckychuan.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luckychuan on 2017/4/2.
 */
public class Weather {

    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecasts;

    @Override
    public String toString() {
        return "Weather{" +
                "status='" + status + '\'' +
                ", basic=" + basic +
                ", aqi=" + aqi +
                ", now=" + now +
                ", suggestion=" + suggestion +
                ", forecasts=" + forecasts +
                '}';
    }
}
