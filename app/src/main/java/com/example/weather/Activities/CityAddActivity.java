package com.example.weather.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.API.QWeatherService;
import com.example.weather.Adapters.CityAdapter;
import com.example.weather.Adapters.CityAddAdapter;
import com.example.weather.Application.WeatherApplication;
import com.example.weather.Callback.CitySearchCallback;
import com.example.weather.Domains.City;
import com.example.weather.Domains.CitySearch;
import com.example.weather.R;
import com.example.weather.Response.CitySearchResponse;

import java.util.ArrayList;

public class CityAddActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    CityAddAdapter cityAddAdapter;

    ArrayList<CitySearch> citySearches;

    ArrayList<City> cities;

    private Boolean isCityAdd = false;

    private Handler citySearchHandler = new Handler();
    private Runnable citySearchRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        citySearches = new ArrayList<>();

        WeatherApplication app = (WeatherApplication) getApplicationContext();
        cities = app.getCities();

        setNavigation();
        setEditorTextChange();
        updateRecyclerView();
    }

    private void setEditorTextChange() {
        EditText searchCity = findViewById(R.id.search_city_text);
        searchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if(input.contains("\n")){
                    input = input.replace("\n","");
                    searchCity.setText(input);
                    searchCity.setSelection(input.length());
                }
                Log.i("do search", input);
                doSearch(input);
            }
        });
    }

    private void updateRecyclerView() {
        runOnUiThread(() -> {
            recyclerView = findViewById(R.id.recyclerView_add_city);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            cityAddAdapter = new CityAddAdapter(citySearches);
            cityAddAdapter.setCardClickListener((position, isSelected) -> {
                if(!isSelected) {
                    City city = new City();
                    city.setId(citySearches.get(position).getId());
                    city.setName(citySearches.get(position).getName());
                    cities.add(city);
                    isCityAdd = true;

                    updateRecyclerView();
                }
            });
            recyclerView.setAdapter(cityAddAdapter);
        });
    }

    private void setNavigation() {
        TextView cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("isCityAdd", isCityAdd);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void doSearch(final String query) {

        if (citySearchRunnable != null) {
            citySearchHandler.removeCallbacks(citySearchRunnable);
        }

        citySearchRunnable = () -> QWeatherService.searchCity(query, new CitySearchCallback() {
            @Override
            public void onSuccess(CitySearchResponse response) {
                citySearches.clear();
                for(int i = 0; i < response.getLocation().size(); i++) {
                    citySearches.add(new CitySearch(
                            response.getLocation().get(i).getName(),
                            response.getLocation().get(i).getId(),
                            response.getLocation().get(i).getAdm2(),
                            response.getLocation().get(i).getAdm1()));
                }
                updateRecyclerView();
            }

            @Override
            public void onError(String error) {

            }
        });
        citySearchHandler.postDelayed(citySearchRunnable, 500);
    }
}
