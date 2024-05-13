package com.example.weather.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.API.QWeatherService;
import com.example.weather.Adapters.CityAdapter;
import com.example.weather.Application.WeatherApplication;
import com.example.weather.Callback.WeatherNowCallback;
import com.example.weather.Domains.City;
import com.example.weather.Domains.CityMain;
import com.example.weather.Domains.Hourly;
import com.example.weather.Domains.Tomorrow;
import com.example.weather.R;
import com.example.weather.Response.WeatherNowResponse;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity {

    private ArrayList<City> cities = new ArrayList<>();

    private CityMain mainCity;

    private ArrayList<Tomorrow> tomorrows;

    private ArrayList<Hourly> hourlies;

    RecyclerView recyclerView;

    CityAdapter adapterCity;

    private Boolean isCityChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        WeatherApplication app = (WeatherApplication) getApplicationContext();

        cities = app.getCities();
        mainCity = app.getCityMain();
        tomorrows = app.getTomorrows();
        hourlies = app.getHourlies();

        setWeatherCities();
        setNavigation();
    }

    private void setWeatherCities() {
        for(int i = 0; i < cities.size(); i++) {
            final int currentCityIndex = i;

            QWeatherService.getWeatherNow(cities.get(i).getId(), new WeatherNowCallback() {
                @Override
                public void onSuccess(WeatherNowResponse response) {
                    cities.get(currentCityIndex).setTemperature(response.getNow().getTemp());
                    cities.get(currentCityIndex).setWeather(response.getNow().getText());
                    cities.get(currentCityIndex).setTemperature(response.getNow().getTemp());

                    updateRecyclerView();

                    Log.i("CityActivity", "setWeatherCities: " + cities.get(currentCityIndex).getName() + " " + cities.get(currentCityIndex).getTemperature() + " " + cities.get(currentCityIndex).getWeather());
                }

                @Override
                public void onError(String error) {
                    Log.e("CityActivity", "setWeatherCities: " + error);
                }
            });
        }
    }

    private void setNavigation() {
        ConstraintLayout backToMainBtn = findViewById(R.id.backBtnCity);
        backToMainBtn.setOnClickListener(v -> {
            if(updateCityMain()) {
                Intent intent = new Intent();
                intent.putExtra("isCityChange", true);
                setResult(RESULT_OK, intent);
            }
            WeatherApplication app = (WeatherApplication) getApplicationContext();
            CityMain _temp = app.getCityMain();
            Log.i("temp_city_main", _temp.getId() + " " + _temp.getName());
            finish();
        });

        ImageView editorBtn = findViewById(R.id.editorBtn);
        ActivityResultLauncher<Intent> mStartActivityForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if(data != null) {
                            if(data.getBooleanExtra("isCityDelete", false)) {
                                Log.i("isCityDelete", " " + data.getBooleanExtra("isCityActivityDelete", false));
                                updateRecyclerView();
                            }
                            else {
                                Log.i("isCityDelete", " " + data.getBooleanExtra("isCityDelete", false));
                            }
                        }
                    }
                }
        );
        editorBtn.setOnClickListener(v -> mStartActivityForResult.launch(new Intent(CityActivity.this, CityManageActivity.class)));

        ConstraintLayout searchBtn = findViewById(R.id.searchBtn);
        ActivityResultLauncher<Intent> sStartActivityForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if(data != null) {
                            if(data.getBooleanExtra("isCityAdd", false)) {
                                Log.i("isCityAdd", " " + data.getBooleanExtra("isCityAdd", false));
                                setWeatherCities();
                                updateRecyclerView();
                            }
                            else {
                                Log.i("isCityAdd", " " + data.getBooleanExtra("isCityAdd", false));
                            }
                        }
                    }
                }
        );
        searchBtn.setOnClickListener(v -> sStartActivityForResult.launch(new Intent(CityActivity.this, CityAddActivity.class)));
    }

    private void updateRecyclerView() {
        runOnUiThread(() -> {

            recyclerView = findViewById(R.id.recyclerView_city_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            adapterCity = new CityAdapter(cities);
            adapterCity.setCardClickListener((position, isSelected) -> {
                if(isSelected) {
                    isCityChange = !cities.get(position).getId().equals(mainCity.getId());
                }
                updateCityMain(position);

                Intent intent = new Intent();
                intent.putExtra("isCityChange", isCityChange);
                setResult(RESULT_OK, intent);
                finish();

                Log.i("ClickCard", "position: " + position + " isSelected: " + isSelected);
            });
            recyclerView.setAdapter(adapterCity);
        });
    }

    private void updateCityMain(int position) {
        mainCity.setId(cities.get(position).getId());
        mainCity.setName(cities.get(position).getName());
    }

    private boolean updateCityMain() {
        if(cities.isEmpty()) {
            mainCity.refresh();
            for(Hourly hourly : hourlies) {
                hourly.refresh();
            }
            for(Tomorrow tomorrow : tomorrows) {
                tomorrow.refresh();
            }
            Log.i("refresh main city", mainCity.getId() + " " + mainCity.getName());
            return true;
        }
        else {
            for(City city : cities) {
                if(city.getId().equals(mainCity.getId())) {
                    return false;
                }
            }
            mainCity.setId(cities.get(0).getId());
            mainCity.setName(cities.get(0).getName());
            return true;
        }
    }
}
