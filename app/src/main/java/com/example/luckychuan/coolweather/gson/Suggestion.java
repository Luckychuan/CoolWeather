package com.example.luckychuan.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2017/4/2.
 */
public class Suggestion {

    @SerializedName("comf")
    public Suggestion1 suggestion1;

    @SerializedName("cw")
    public Suggestion2  suggestion2;

    @SerializedName("sport")
    public Suggestion3  suggestion3;


    public class Suggestion1{

        @SerializedName("txt")
        public String s1;
    }

    public class Suggestion2{

        @SerializedName("txt")
        public String s2;
    }

    public class Suggestion3{

        @SerializedName("txt")
        public String s3;
    }

}
