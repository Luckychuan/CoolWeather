package com.example.luckychuan.coolweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://guolin.tech/api/weather?cityid=CN101110101&key=386188613f8e460388361160675a0a82";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadData(URL);

    }

    private void downloadData(String url){
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
                           if(weather != null){
                               Log.d(TAG, "run: "+weather.toString());
                           }
                       }
                   });
               }
           });

    }

    private Weather decodeWeatherJson(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String contentJson = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(contentJson,Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
