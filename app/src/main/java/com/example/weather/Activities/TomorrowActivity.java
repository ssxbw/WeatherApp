package com.example.weather.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Adapters.TomorrowAdapter;
import com.example.weather.Application.WeatherApplication;
import com.example.weather.Domains.Tomorrow;
import com.example.weather.R;

import java.util.ArrayList;

public class TomorrowActivity extends AppCompatActivity {

    private ArrayList<Tomorrow> tomorrows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomorrow);

        WeatherApplication app = (WeatherApplication) getApplicationContext();

        tomorrows = app.getTomorrows();

        initView();
        initRecyclerView();
        setNavigation();
    }

    private void initView() {
        TextView tomorrowTemperatureRange = findViewById(R.id.tomorrow_temperature_range_text);
        TextView tomorrowWeather = findViewById(R.id.tomorrow_weather_text);
        TextView tomorrowWind = findViewById(R.id.tomorrow_wind_text);
        TextView tomorrowRain = findViewById(R.id.tomorrow_rain_text);
        TextView tomorrowHumidity = findViewById(R.id.tomorrow_humidity_text);

        Tomorrow tomorrow = tomorrows.get(1);

        tomorrowTemperatureRange.setText(tomorrow.getLowTemperature() + "~" + tomorrow.getHighTemperature() + "°");
        tomorrowWeather.setText(tomorrow.getWeather());
        tomorrowWind.setText(tomorrow.getWind());
        tomorrowRain.setText(tomorrow.getRain());
        tomorrowHumidity.setText(tomorrow.getHumidity());
    }

    private void setNavigation() {
        ConstraintLayout backToMainBtn = findViewById(R.id.backBtnTomorrow);
        backToMainBtn.setOnClickListener(v -> finish());
    }

    private void initRecyclerView() {

        TomorrowAdapter adapterTomorrow;

        RecyclerView recyclerView;


        recyclerView = findViewById(R.id.recyclerView_next_seven_day);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterTomorrow = new TomorrowAdapter(tomorrows);
        recyclerView.setAdapter(adapterTomorrow);
    }
}
