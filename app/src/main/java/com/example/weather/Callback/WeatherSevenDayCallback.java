package com.example.weather.Callback;

import com.example.weather.Response.WeatherSevenDayResponse;

public interface WeatherSevenDayCallback {
    void onSuccess(WeatherSevenDayResponse response);
    void onError(String error);
}
