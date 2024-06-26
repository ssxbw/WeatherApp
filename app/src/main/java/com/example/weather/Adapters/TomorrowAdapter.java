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
import com.example.weather.Domains.Hourly;
import com.example.weather.Domains.Tomorrow;
import com.example.weather.R;
import com.example.weather.Utils.IconCodeToDrawableID;

import java.util.ArrayList;

public class TomorrowAdapter extends RecyclerView.Adapter<TomorrowAdapter.ViewHolder> {

    private ArrayList<Tomorrow> items;

    private Context context;

    public TomorrowAdapter(ArrayList<Tomorrow> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public TomorrowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_tomorrow, parent, false);
        context = parent.getContext();

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TomorrowAdapter.ViewHolder holder, int position) {

        holder.dateText.setText(items.get(position).getDate());
        holder.weatherText.setText(items.get(position).getWeather());
        holder.highTempText.setText(String.valueOf(items.get(position).getHighTemperature()));
        holder.lowTempText.setText(String.valueOf(items.get(position).getLowTemperature()));

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

        TextView dateText, weatherText, highTempText, lowTempText;

        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
            weatherText = itemView.findViewById(R.id.weatherText);
            highTempText = itemView.findViewById(R.id.highTempText);
            lowTempText = itemView.findViewById(R.id.lowTempText);
            pic = itemView.findViewById(R.id.tomorrowPic);
        }
    }
}
