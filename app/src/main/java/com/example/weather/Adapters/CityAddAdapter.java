package com.example.weather.Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weather.Application.WeatherApplication;
import com.example.weather.Callback.CardClickListener;
import com.example.weather.Domains.City;
import com.example.weather.Domains.CitySearch;
import com.example.weather.R;

import java.util.ArrayList;

public class CityAddAdapter extends RecyclerView.Adapter<CityAddAdapter.ViewHolder> {

    private ArrayList<CitySearch> items;

    private Context context;

    private ArrayList<City> cities;

    private CardClickListener cardClickListener;

    public CityAddAdapter(ArrayList<CitySearch> items) {
        this.items = items;
    }

    public void setCardClickListener(CardClickListener cardClickListener) {
        this.cardClickListener = cardClickListener;
    }

    @NonNull
    @Override
    public CityAddAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_add_city, parent, false);
        context = parent.getContext();

        WeatherApplication app = (WeatherApplication) context.getApplicationContext();
        cities = app.getCities();

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAddAdapter.ViewHolder holder, int position) {
        if(items.get(position).getName().equals(items.get(position).getAdm2())) {
            holder.cityNameText.setText(items.get(position).getAdm1() + "-" + items.get(position).getAdm2());
        }
        else {
            holder.cityNameText.setText(items.get(position).getAdm1() + "-" + items.get(position).getAdm2() + "-" + items.get(position).getName());
        }

        int drawableResourceId = holder.itemView.getResources()
                .getIdentifier(isCityAdded(items.get(position).getId()) ? "add_city_already" : "add_city_blue", "drawable", context.getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cityNameText;

        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cityNameText = itemView.findViewById(R.id.cityNameText);
            pic = itemView.findViewById(R.id.addedOrNotPic);

            pic.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if(cardClickListener != null) {
                    cardClickListener.onCardClick(position, isCityAdded(items.get(position).getId()));
                }
            });
        }
    }

    boolean isCityAdded(String cityId) {
        for(City city : cities) {
            if(city.getId().equals(cityId)) {
                return true;
            }
        }
        return false;
    }
}
