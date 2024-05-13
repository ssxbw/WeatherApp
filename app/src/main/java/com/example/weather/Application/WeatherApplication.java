package com.example.weather.Application;

import android.app.Application;

import com.example.weather.Domains.City;
import com.example.weather.Domains.CityMain;
import com.example.weather.Domains.Hourly;
import com.example.weather.Domains.Tomorrow;

import java.util.ArrayList;

public class WeatherApplication extends Application {
    private CityMain cityMain;

    private ArrayList<Hourly> hourlies;

    private ArrayList<City> cities;

    private ArrayList<Tomorrow> tomorrows;

    @Override
    public void onCreate() {
        super.onCreate();
        cityMain = new CityMain();
        cities = new ArrayList<>();
        hourlies = new ArrayList<>();
        tomorrows = new ArrayList<>();

        cityMain.setName("北京");
        cityMain.setId("101010100");

        cities.add(new City());
        cities.get(0).setName("北京");
        cities.get(0).setId("101010100");

        for (int i = 0; i < 24; i++) {
            hourlies.add(new Hourly());
        }

        for (int i = 0; i < 7; i++) {
            tomorrows.add(new Tomorrow());
        }
    }

    public CityMain getCityMain() {
        return cityMain;
    }

    public ArrayList<Hourly> getHourlies() {
        return hourlies;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public ArrayList<Tomorrow> getTomorrows() {
        return tomorrows;
    }
}
