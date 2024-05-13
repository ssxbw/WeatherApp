package com.example.weather.Callback;

import com.example.weather.Response.WeatherNowResponse;

public interface WeatherNowCallback {
    void onSuccess(WeatherNowResponse response);
    void onError(String error);
}
