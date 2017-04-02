package com.example.luckychuan.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2017/4/2.
 */
public class Basic {
    
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public  String cityId;

    public Update update;

    public class Update{

        @SerializedName("loc")
        public String updateTime;

    }



}
