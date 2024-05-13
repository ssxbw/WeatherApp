package com.example.weather.API;

import android.util.Log;

import com.example.weather.Callback.CitySearchCallback;
import com.example.weather.Callback.WeatherNowCallback;
import com.example.weather.Callback.WeatherSevenDayCallback;
import com.example.weather.Callback.WeatherTwentyFourHourCallback;
import com.example.weather.Response.CitySearchResponse;
import com.example.weather.Response.WeatherNowResponse;
import com.example.weather.Response.WeatherSevenDayResponse;
import com.example.weather.Response.WeatherTwentyFourHourResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class QWeatherService {
    private static final String key = "219508057ba642db9bf87c0d587b29ff";

    public static void getWeatherNow(String cityId, WeatherNowCallback callback) {
        OkHttpClient okHttpClient = OkHttpClientInstance.getOkHttpClientInstance();

        Request request = new Request.Builder()
                .url("https://devapi.qweather.com/v7/weather/now?location=" + cityId + "&key=" + key)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("weather", e.getMessage());
                callback.onError(e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();

                    try {
                        Gson gson = new Gson();
                        WeatherNowResponse weatherNowResponse = gson.fromJson(responseData, WeatherNowResponse.class);

                        callback.onSuccess(weatherNowResponse);
                    }
                    catch (Exception e) {
                        Log.e("response json", e.getMessage());
                        callback.onError("response json error");
                    }
                }
                else {
                    Log.e("response json", "weather now response failed");
                    callback.onError("weather now response failed");
                }
            }
        });
    }

    public static void getWeatherTwentyFourHour(String cityId, WeatherTwentyFourHourCallback callback) {

        OkHttpClient okHttpClient = OkHttpClientInstance.getOkHttpClientInstance();

        Request request = new Request.Builder()
                .url("https://devapi.qweather.com/v7/weather/24h?location=" + cityId + "&key=" + key)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("weather", e.getMessage());
                callback.onError(e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();

                    try {
                        Gson gson = new Gson();
                        WeatherTwentyFourHourResponse weatherTwentyFourHourResponse = gson.fromJson(responseData, WeatherTwentyFourHourResponse.class);

                        callback.onSuccess(weatherTwentyFourHourResponse);
                    }
                    catch (Exception e) {
                        Log.e("response json", e.getMessage());
                        callback.onError("response json error");
                    }
                }
                else {
                    Log.e("response json", "weather twenty four hour response failed");
                    callback.onError("weather twenty four hour response failed");
                }
            }
        });
    }

    public static void getWeatherSevenDay(String cityId, WeatherSevenDayCallback callback) {
        OkHttpClient okHttpClient = OkHttpClientInstance.getOkHttpClientInstance();

        Request request = new Request.Builder()
                .url("https://devapi.qweather.com/v7/weather/7d?location=" + cityId + "&key=" + key)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("weather", e.getMessage());
                callback.onError(e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();

                    try {
                        Gson gson = new Gson();
                        WeatherSevenDayResponse weatherSevenDayResponse = gson.fromJson(responseData, WeatherSevenDayResponse.class);

                        callback.onSuccess(weatherSevenDayResponse);
                    }
                    catch (Exception e) {
                        Log.e("response json", e.getMessage());
                        callback.onError("response json error");
                    }
                }
                else {
                    Log.e("response json", "weather seven day response failed");
                    callback.onError("weather seven day response failed");
                }
            }
        });
    }

    public static void searchCity(String keywords, CitySearchCallback callback) {
        OkHttpClient okHttpClient = OkHttpClientInstance.getOkHttpClientInstance();

        Request request = new Request.Builder()
                .url("https://geoapi.qweather.com/v2/city/lookup?location=" + keywords + "&range=cn" + "&key=" + key)
                .build();

        Log.i("search city url", request.url().toString());
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("weather", e.getMessage());
                callback.onError(e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();

                    try {
                        Gson gson = new Gson();
                        CitySearchResponse citySearchResponse = gson.fromJson(responseData, CitySearchResponse.class);
                        Log.i("search city response", responseData);
                        callback.onSuccess(citySearchResponse);

                    }
                    catch (Exception e) {
                        Log.e("search city response", e.getMessage());
                        callback.onError("response json error");
                    }
                }
                else {
                    Log.e("response json", "weather seven day response failed");
                    callback.onError("city search response failed");
                }
            }
        });
    }
}
