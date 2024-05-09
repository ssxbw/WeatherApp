package com.example.weather.Adapters;

import com.bumptech.glide.Glide;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Domains.Hourly;
import com.example.weather.R;

import java.util.ArrayList;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {

    ArrayList<Hourly> items;

    Context context;

    public HourlyAdapter(ArrayList<Hourly> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public HourlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hourly, parent, false);
        context = parent.getContext();

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyAdapter.ViewHolder holder, int position) {
        holder.hourText.setText(items.get(position).getHour());
        holder.temperatureText.setText(items.get(position).getTemperature() + "℃");

        int drawableResourceId = holder.itemView.getResources()
                .getIdentifier(items.get(position).getPicPath(), "drawable", context.getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView hourText;

        TextView temperatureText;

        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hourText = itemView.findViewById(R.id.hourText);
            temperatureText = itemView.findViewById(R.id.temperatureText);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
