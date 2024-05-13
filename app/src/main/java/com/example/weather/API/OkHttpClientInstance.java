package com.example.weather.API;

import okhttp3.OkHttpClient;

public class OkHttpClientInstance {

    private static OkHttpClient client = null;

    private OkHttpClientInstance() {}

    public static synchronized OkHttpClient getOkHttpClientInstance() {
        if (client == null) {
            client = new OkHttpClient();
        }
        return client;
    }
}
