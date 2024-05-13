package com.example.weather.Callback;

import com.example.weather.Response.WeatherTwentyFourHourResponse;

public interface WeatherTwentyFourHourCallback {
    void onSuccess(WeatherTwentyFourHourResponse response);
    void onError(String error);
}
