package com.example.weather.Callback;

import com.example.weather.Response.CitySearchResponse;

public interface CitySearchCallback {
    void onSuccess(CitySearchResponse response);
    void onError(String error);
}
