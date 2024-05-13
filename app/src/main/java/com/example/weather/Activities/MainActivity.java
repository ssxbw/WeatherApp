package com.example.weather.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.API.QWeatherService;
import com.example.weather.Adapters.HourlyAdapter;
import com.example.weather.Application.WeatherApplication;
import com.example.weather.Callback.WeatherNowCallback;
import com.example.weather.Callback.WeatherSevenDayCallback;
import com.example.weather.Callback.WeatherTwentyFourHourCallback;
import com.example.weather.Domains.CityMain;
import com.example.weather.Domains.Hourly;
import com.example.weather.Domains.Tomorrow;
import com.example.weather.R;
import com.example.weather.Response.WeatherNowResponse;
import com.example.weather.Response.WeatherSevenDayResponse;
import com.example.weather.Response.WeatherTwentyFourHourResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private CityMain cityMain;

    private ArrayList<Hourly> hourlies;

    private ArrayList<Tomorrow> tomorrows;

    HourlyAdapter adapterHourly;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();

        setNavigation();
        setWeatherNow();
        setWeatherTomorrow();
        setWeather24Hours();
    }

    private void setWeatherTomorrow() {
        if(cityMain.getId().isEmpty()) {
            updateUI();
            updateRecyclerView();
            return;
        }
        QWeatherService.getWeatherSevenDay(cityMain.getId(), new WeatherSevenDayCallback() {
            @Override
            public void onSuccess(WeatherSevenDayResponse response) {

                for (int i = 0; i < 7; i++) {
                    tomorrows.get(i).setDate(response.getDaily().get(i).getFxDate());
                    tomorrows.get(i).setWeather(response.getDaily().get(i).getTextDay());
                    tomorrows.get(i).setHighTemperature(response.getDaily().get(i).getTempMax());
                    tomorrows.get(i).setLowTemperature(response.getDaily().get(i).getTempMin());
                    tomorrows.get(i).setWind(response.getDaily().get(i).getWindDirDay() + response.getDaily().get(i).getWindScaleDay() + "级");
                    tomorrows.get(i).setRain(response.getDaily().get(i).getPrecip() + "mm");
                    tomorrows.get(i).setHumidity(response.getDaily().get(i).getHumidity() + "%");
                    tomorrows.get(i).setPicCode(response.getDaily().get(i).getIconDay());
                }

                cityMain.setHighTemperature(tomorrows.get(0).getHighTemperature());
                cityMain.setLowTemperature(tomorrows.get(0).getLowTemperature());

                updateUI();
                Log.i("WeatherSevenDay", "onSuccess: " + tomorrows.size());
            }

            @Override
            public void onError(String error) {
//                updateUI();
                Log.e("WeatherSevenDay", "onError: " + error);
            }
        });

        updateRecyclerView();
    }

    private void setWeatherNow() {
        if(cityMain.getId().isEmpty()) {
            updateUI();
            return;
        }

        QWeatherService.getWeatherNow(cityMain.getId(), new WeatherNowCallback() {
            @Override
            public void onSuccess(WeatherNowResponse response) {
                cityMain.setUpdateTime(response.getUpdateTime());
                cityMain.setWeather(response.getNow().getText());
                cityMain.setTemperature(response.getNow().getTemp());
                cityMain.setHumidity(response.getNow().getHumidity());
                cityMain.setRain(response.getNow().getPrecip());
                cityMain.setWind(response.getNow().getWindDir() + response.getNow().getWindScale() + "级");
                cityMain.setPicCode(response.getNow().getIcon());

                updateUI();
                Log.i("WeatherNow", "onSuccess: " + cityMain.getName() + cityMain.getUpdateTime() + cityMain.getWeather() + cityMain.getTemperature() + cityMain.getHumidity() + cityMain.getRain() + cityMain.getWind());
            }

            @Override
            public void onError(String error) {
//                updateUI();
                Log.e("WeatherNow", "onError: " + error);
            }
        });
    }

    private void updateUI() {
        runOnUiThread(() -> {
            TextView cityName = findViewById(R.id.main_city_name_text);
            TextView updateTime = findViewById(R.id.update_time_text);
            TextView weather = findViewById(R.id.main_weather_text);
            TextView temperature = findViewById(R.id.main_temperature_text);
            TextView temperatureRange = findViewById(R.id.main_temperature_range_text);
            TextView humidity = findViewById(R.id.main_humidity_text);
            TextView rain = findViewById(R.id.main_rain_text);
            TextView wind = findViewById(R.id.main_wind_text);

            cityName.setText(cityMain.getName());
            updateTime.setText("更新时间: " + getTimeAgo(cityMain.getUpdateTime()));
            weather.setText(cityMain.getWeather());
            temperature.setText(cityMain.getTemperature() + "°");
            temperatureRange.setText(cityMain.getLowTemperature() + "~" + cityMain.getHighTemperature() + "°");
            humidity.setText(cityMain.getHumidity() + "%");
            rain.setText(cityMain.getRain() + "mm");
            wind.setText(cityMain.getWind());
        });
    }

    private void setNavigation() {
        TextView nextSevenDayBtn = findViewById(R.id.nextSevenDayBtn);
        nextSevenDayBtn.setOnClickListener( v -> startActivity(new Intent(MainActivity.this, TomorrowActivity.class)));

        ConstraintLayout addCityBtn = findViewById(R.id.addBtnCity);
        ActivityResultLauncher<Intent> mStartActivityForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if(data != null) {
                            if(data.getBooleanExtra("isCityChange", false)) {
                                Log.i("isCityChange", " " + data.getBooleanExtra("isCityChange", false));
                                Log.i("refresh main city", " " + cityMain.getName() + " " + cityMain.getId());
                                setWeatherNow();
                                setWeatherTomorrow();
                                setWeather24Hours();
                                Log.i("redraw", "redraw main activity");
                            }
                            else {
                                Log.i("isCityChange", " " + data.getBooleanExtra("isCityChange", false));
                            }
                        }
                    }
                }
        );
        addCityBtn.setOnClickListener( v -> mStartActivityForResult.launch(new Intent(MainActivity.this, CityActivity.class)));
    }

    private void setWeather24Hours() {
        if(cityMain.getId().isEmpty()) {
            updateRecyclerView();
            return;
        }
        QWeatherService.getWeatherTwentyFourHour(cityMain.getId(), new WeatherTwentyFourHourCallback() {
            @Override
            public void onSuccess(WeatherTwentyFourHourResponse response) {
                for (int i = 0; i < 24; i++) {
                    hourlies.get(i).setHour(response.getHourly().get(i).getFxTime().substring(11, 16));
                    hourlies.get(i).setTemperature(response.getHourly().get(i).getTemp() + "°");
                    hourlies.get(i).setPicCode(response.getHourly().get(i).getIcon());
                }

                updateRecyclerView();
                Log.i("WeatherTwentyFourHour", "onSuccess: " + hourlies.size());
            }

            @Override
            public void onError(String error) {
//                updateRecyclerView();
                Log.e("WeatherTwentyFourHour", "onError: " + error);
            }
        });

        updateRecyclerView();
    }

    private void updateRecyclerView() {
        runOnUiThread(() -> {

            recyclerView = findViewById(R.id.recyclerView_24_hour);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

            adapterHourly = new HourlyAdapter(hourlies);
            recyclerView.setAdapter(adapterHourly);
        });
    }

    private String getTimeAgo(String timeStr) {
        String timeAgo = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            Date date = sdf.parse(timeStr);
            Date now = new Date();

            long diff = now.getTime() - date.getTime();

            if (diff < 0) {
                return "刚刚";
            }

            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);
            long diffMonths = diff / (30 * 24 * 60 * 60 * 1000);
            long diffYears = diff / (365 * 24 * 60 * 60 * 1000);

            if (diffYears > 0) {
                timeAgo = diffYears + "年前";
            } else if (diffMonths > 0) {
                timeAgo = diffMonths + "个月前";
            } else if (diffDays > 0) {
                timeAgo = diffDays + "天前";
            } else if (diffHours > 0) {
                timeAgo = diffHours + "小时前";
            } else if (diffMinutes > 0) {
                timeAgo = diffMinutes + "分钟前";
            } else {
                timeAgo = "刚刚";
            }
        } catch (ParseException e) {
            Log.e("getTimeAgo", e.getMessage());
        }
        return timeAgo;
    }

    private void getData() {
        WeatherApplication app = (WeatherApplication) getApplicationContext();
        cityMain = app.getCityMain();
        hourlies = app.getHourlies();
        tomorrows = app.getTomorrows();
    }
}
