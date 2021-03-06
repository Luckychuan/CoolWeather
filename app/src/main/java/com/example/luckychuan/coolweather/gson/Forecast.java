package com.example.luckychuan.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2017/4/2.
 */
public class Forecast {

    public String date;

    @SerializedName("cond")
    public WeatherInfo weatherIinfo;

    @SerializedName("tmp")
    public Temperature temperature;

    public class Temperature{

        public String max;
        public String min;
    }

    public class WeatherInfo{

        @SerializedName("txt_d")
        public String info;
    }

}
