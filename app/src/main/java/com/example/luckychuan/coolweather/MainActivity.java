package com.example.luckychuan.coolweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://guolin.tech/api/weather?cityid=CN101110101&key=386188613f8e460388361160675a0a82";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
