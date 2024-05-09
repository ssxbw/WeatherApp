package com.example.weather.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Adapters.HourlyAdapter;
import com.example.weather.Domains.Hourly;
import com.example.weather.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        setToNextSevenDay();
    }

    private void setToNextSevenDay() {
        TextView nextSevenDayBtn = findViewById(R.id.nextSevenDayBtn);
        nextSevenDayBtn.setOnClickListener( v -> startActivity(new Intent(MainActivity.this, TomorrowActivity.class)));
    }

    private void initRecyclerView() {

        RecyclerView.Adapter<HourlyAdapter.ViewHolder> adapterHourly;

        RecyclerView recyclerView;

        ArrayList<Hourly> items = new ArrayList<>();

        items.add(new Hourly("12:00", 25, "tianqi_qing"));
        items.add(new Hourly("13:00", 26, "tianqi_baoyu"));
        items.add(new Hourly("14:00", 27, "tianqi_baoyuzhuandabaoyu"));
        items.add(new Hourly("15:00", 28, "tianqi_baoyu"));
        items.add(new Hourly("16:00", 23, "tianqi_baoyuzhuandabaoyu"));
        items.add(new Hourly("17:00", 22, "tianqi_baoyu"));

        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterHourly = new HourlyAdapter(items);
        recyclerView.setAdapter(adapterHourly);
    }
}
