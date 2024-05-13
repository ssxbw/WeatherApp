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

import java.util.ArrayList;

public class CityManageAdapter extends RecyclerView.Adapter<CityManageAdapter.ViewHolder>{

    private ArrayList<City> items;

    private Context context;

    private CardClickListener cardClickListener;

    public void setCardClickListener(CardClickListener cardClickListener) {
        this.cardClickListener = cardClickListener;
    }

    public CityManageAdapter(ArrayList<City> items) { this.items = items; }

    @NonNull
    @Override
    public CityManageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_manage_city, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CityManageAdapter.ViewHolder holder, int position) {
        holder.cityNameText.setText(items.get(position).getName());

        int drawableResourceId = holder.itemView.getResources()
                .getIdentifier(items.get(position).isSelected() ? "select_already" : "select_not_already", "drawable", context.getPackageName());

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
            pic = itemView.findViewById(R.id.picSelect);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if(cardClickListener != null) {
                    cardClickListener.onCardClick(position, !items.get(position).isSelected());
                }
            });
        }
    }
}
