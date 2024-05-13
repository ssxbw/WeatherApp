package com.example.weather.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weather.Callback.CardClickListener;
import com.example.weather.Domains.City;
import com.example.weather.R;
import com.example.weather.Utils.IconCodeToDrawableID;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private ArrayList<City> items;

    private Context context;

    public CityAdapter(ArrayList<City> items) { this.items = items; }

    private CardClickListener cardClickListener;

    public void setCardClickListener(CardClickListener cardClickListener) {
        this.cardClickListener = cardClickListener;
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_city, parent, false);
        context = parent.getContext();

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {
        holder.cityNameText.setText(items.get(position).getName());
        holder.weatherText.setText(items.get(position).getWeather());
        holder.tempText.setText(items.get(position).getTemperature());

        int drawableResourceId = holder.itemView.getResources()
                .getIdentifier(IconCodeToDrawableID.findDrawableId(items.get(position).getPicCode()), "drawable", context.getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cityNameText, tempText, tempRangeText, weatherText;

        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityNameText = itemView.findViewById(R.id.cityNameText);
            tempText = itemView.findViewById(R.id.tempText);
            weatherText = itemView.findViewById(R.id.weatherText);
            pic = itemView.findViewById(R.id.cityPic);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if(cardClickListener != null) {
                    cardClickListener.onCardClick(position, true);
                }
            });
        }
    }
}
