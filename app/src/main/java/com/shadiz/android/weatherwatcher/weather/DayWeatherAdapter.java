package com.shadiz.android.weatherwatcher.weather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shadiz.android.weatherwatcher.R;
import com.shadiz.android.weatherwatcher.model.DayWeather;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kassava on 11.01.17.
 */

public class DayWeatherAdapter extends RecyclerView.Adapter<DayWeatherAdapter.DayWeatherViewHolder> {

    private List<DayWeather> items = null;
    private LayoutInflater inflater;

    public DayWeatherAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public List<DayWeather> getItems() {
        return items;
    }

    public void setItems(List<DayWeather> items) {
        this.items = items;
    }

    @Override
    public DayWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_dayweather, parent, false);
        return new DayWeatherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DayWeatherViewHolder holder, int position) {
        DayWeather dayWeather = items.get(position);
        holder.date.setText(Long.toString(dayWeather.getDate()));
        holder.temperature.setText(Double.toString(dayWeather.getTemperature().getDay()) + " C");
        holder.humidity.setText(Integer.toString(dayWeather.getHumidity()) + " %");
        holder.wind.setText(Double.toString(dayWeather.getSpeed()) + " m/s " + Double.toString(dayWeather.getDeg()) + " deg");
        holder.pressure.setText(Double.toString(dayWeather.getPressure()) + " hPa");
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        else return items.size();
    }

    public static class DayWeatherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date) TextView date;
        @BindView(R.id.temperature) TextView temperature;
        @BindView(R.id.humidity) TextView humidity;
        @BindView(R.id.wind) TextView wind;
        @BindView(R.id.pressure) TextView pressure;

        public DayWeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
