package com.shadiz.android.weatherwatcher.weather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shadiz.android.weatherwatcher.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by johny homicide on 11.01.17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<com.shadiz.android.weatherwatcher.model.List> items = null;
    private LayoutInflater inflater;

    public ListAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public List<com.shadiz.android.weatherwatcher.model.List> getItems() {
        return items;
    }

    public void setItems(List<com.shadiz.android.weatherwatcher.model.List> items) {
        this.items = items;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_dayweather, parent, false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        com.shadiz.android.weatherwatcher.model.List dayWeather = items.get(position);
        holder.date.setText(formatDate(dayWeather.getDt()));
        holder.temperature.setText(Double.toString(dayWeather.getTemp().getDay()) + " C");
        holder.humidity.setText(Double.toString(dayWeather.getHumidity()) + " %");
        holder.wind.setText(Double.toString(dayWeather.getSpeed()) + " m/s " + Double.toString(dayWeather.getDeg()) + " deg");
        holder.pressure.setText(Double.toString(dayWeather.getPressure()) + " hPa");
    }

    private String formatDate(Integer value) {
        Date date = new Date(value * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        return dateFormat.format(date);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        else return items.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date) TextView date;
        @BindView(R.id.temperature) TextView temperature;
        @BindView(R.id.humidity) TextView humidity;
        @BindView(R.id.wind) TextView wind;
        @BindView(R.id.pressure) TextView pressure;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
