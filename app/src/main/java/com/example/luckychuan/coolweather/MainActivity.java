package com.example.luckychuan.coolweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luckychuan.coolweather.gson.Forecast;
import com.example.luckychuan.coolweather.gson.Weather;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://guolin.tech/api/weather?cityid=CN101110101&key=386188613f8e460388361160675a0a82";
    private static final String TAG = "MainActivity";

    TextView mCityName;
    TextView mUpdateTime;
    TextView mTemperature;
    TextView mWeather;
    TextView mAQI;
    TextView mPM25;
    TextView mSuggestion1;
    TextView mSuggestion2;
    TextView mSuggestion3;

    List<Map<String, String>> mPredictionList;
    SimpleAdapter mPredictionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCityName = (TextView) findViewById(R.id.city_name);
        mUpdateTime = (TextView) findViewById(R.id.update_time);
        mTemperature = (TextView) findViewById(R.id.temperature);
        mWeather = (TextView) findViewById(R.id.weather);
        mAQI = (TextView) findViewById(R.id.aqi);
        mPM25 = (TextView) findViewById(R.id.pm_2_point_5);
        mSuggestion1 = (TextView) findViewById(R.id.suggestion1);
        mSuggestion2 = (TextView) findViewById(R.id.suggestion2);
        mSuggestion3 = (TextView) findViewById(R.id.suggestion3);

        ListView predictionListView = (ListView) findViewById(R.id.prediction_listView);
        mPredictionList = new ArrayList<>();
        mPredictionAdapter =new SimpleAdapter(this, mPredictionList, R.layout.prediction_item
                , new String[]{"date", "info", "min", "max"}, new int[]{R.id.date_item, R.id.weather_item, R.id.temperature_min, R.id.temperature_max});
        predictionListView.setAdapter(mPredictionAdapter);
        requestWeather(URL);

    }

    private void requestWeather(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "获取信息失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Weather weather = decodeWeatherJson(result);
                        if (weather != null && weather.status.equals("ok")) {
                            show(weather);
                        }
                    }
                });
            }
        });

    }

    private void show(Weather weather) {
        mCityName.setText(weather.basic.cityName);
        mUpdateTime.setText(weather.basic.update.updateTime.split(" ")[1]);
        mWeather.setText(weather.now.weatherInfo.info);
        mTemperature.setText(weather.now.temperature + "℃");
        mAQI.setText(weather.aqi.city.aqi);
        mPM25.setText(weather.aqi.city.pm25);
        mSuggestion1.setText("舒适度：" + weather.suggestion.suggestion1.s1);
        mSuggestion2.setText("洗车指数：" + weather.suggestion.suggestion2.s2);
        mSuggestion3.setText("运动建议：" + weather.suggestion.suggestion3.s3);
        mPredictionList.clear();
        mPredictionList.addAll(getPredictionList(weather.forecasts));
        mPredictionAdapter.notifyDataSetChanged();

    }

    private Weather decodeWeatherJson(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String contentJson = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(contentJson, Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Map<String, String>> getPredictionList(List<Forecast> forecasts) {
        List<Map<String, String>> list = new ArrayList<>();
        Log.d(TAG, "getPredictionList: "+forecasts.size());
        for (Forecast forecast : forecasts) {
            Map<String, String> map = new HashMap<>();
            map.put("date", forecast.date);
            Log.d(TAG, "getPredictionList: "+forecast.date);
            map.put("info", forecast.weatherIinfo.info);
            map.put("min", forecast.temperature.min);
            map.put("max", forecast.temperature.max);
            list.add(map);
        }

        return list;
    }
}
