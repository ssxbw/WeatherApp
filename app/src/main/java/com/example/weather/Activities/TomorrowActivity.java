package com.example.weather.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Adapters.TomorrowAdapter;
import com.example.weather.Domains.Tomorrow;
import com.example.weather.R;

import java.util.ArrayList;

public class TomorrowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomorrow);

        initRecyclerView();
        setBackToMainActivity();
    }

    private void setBackToMainActivity() {
        ConstraintLayout backToMainBtn = findViewById(R.id.backBtn);
        backToMainBtn.setOnClickListener(v -> finish());
    }

    private void initRecyclerView() {

        RecyclerView.Adapter<TomorrowAdapter.ViewHolder> adapterTomorrow;

        RecyclerView recyclerView;

        ArrayList<Tomorrow> items = new ArrayList<>();

        items.add(new Tomorrow("07/01", "tianqi_qing", "晴", 30, 20));
        items.add(new Tomorrow("07/02", "tianqi_baoyu", "暴雨", 31, 21));
        items.add(new Tomorrow("07/03", "tianqi_baoyuzhuandabaoyu", "暴雨转大暴雨", 32, 22));
        items.add(new Tomorrow("07/04", "tianqi_baoyu", "暴雨", 33, 23));
        items.add(new Tomorrow("07/05", "tianqi_baoyuzhuandabaoyu", "暴雨转大暴雨", 34, 24));
        items.add(new Tomorrow("07/06", "tianqi_baoyu", "暴雨", 35, 25));
        items.add(new Tomorrow("07/07", "tianqi_baoyuzhuandabaoyu", "暴雨转大暴雨", 36, 26));

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterTomorrow = new TomorrowAdapter(items);
        recyclerView.setAdapter(adapterTomorrow);
    }
}
