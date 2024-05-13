package com.example.weather.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Adapters.CityManageAdapter;
import com.example.weather.Application.WeatherApplication;
import com.example.weather.Domains.City;
import com.example.weather.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CityManageActivity extends AppCompatActivity {

    private ArrayList<City> cities;

    private Set<Integer> selectedCityIds;

    private Boolean isCityDelete = false;

    RecyclerView recyclerView;

    CityManageAdapter adapterCityManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_city);

        WeatherApplication app = (WeatherApplication) getApplicationContext();

        cities = app.getCities();
        for(City city : cities) {
            city.setSelected(false);
        }

        selectedCityIds = new HashSet<>();

        updateRecyclerView();
        setNavigation();
        setOnClickDelete();
    }

    private void setOnClickDelete() {
        TextView deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(v -> {
            if(selectedCityIds.isEmpty()) {
                return;
            }
            else {
                for (int i = cities.size()-1; i >= 0; i--) {
                    if (selectedCityIds.contains(i)) {
                        cities.remove(i);
                    }
                }
                selectedCityIds.clear();
                isCityDelete = true;
                updateRecyclerView();
            }
        });
    }

    private void setNavigation() {
        TextView finishBtn = findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("isCityDelete", isCityDelete);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void updateRecyclerView() {
        runOnUiThread(() -> {

            recyclerView = findViewById(R.id.recyclerView_city_manage);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            adapterCityManage = new CityManageAdapter(cities);
            adapterCityManage.setCardClickListener((position, isSelected) -> {
                cities.get(position).setSelected(isSelected);
                adapterCityManage.notifyItemChanged(position);
                if(isSelected) {
                    selectedCityIds.add(position);
                } else {
                    selectedCityIds.remove(position);
                }

                Log.i("ClickCard", "position: " + position + " isSelected: " + isSelected);
            });
            recyclerView.setAdapter(adapterCityManage);
        });
    }
}
