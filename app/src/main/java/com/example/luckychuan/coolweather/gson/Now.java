package com.example.luckychuan.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2017/4/2.
 */
public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public WeatherInfo weatherIinfo;

    public class WeatherInfo{

        @SerializedName("txt")
        public String info;
    }


}
