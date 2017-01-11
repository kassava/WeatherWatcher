package com.shadiz.android.weatherwatcher.weather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shadiz.android.weatherwatcher.R;
import com.shadiz.android.weatherwatcher.model.WeatherInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kassava on 11.01.17.
 */

public class WeatherInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String LOG_TAG = WeatherInfoAdapter.class.getSimpleName();

    private List<Stream> items = null;
    private LayoutInflater inflater;
    private View.OnClickListener onItemClickListener;

    public WeatherInfoAdapter(LayoutInflater inflater, View.OnClickListener onItemClickListener) {
        this.inflater = inflater;
        this.onItemClickListener = onItemClickListener;
    }

    public List<Stream> getItems() {
        return items;
    }

    public void setItems(List<Stream> items) {
//        this.items.clear();
//        this.items.addAll(items);
        this.items = items;
    }

    @Override
    public StreamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_stream, parent, false);
        return new StreamViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(StreamViewHolder holder, int position) {
        Stream stream = items.get(position);
        holder.stream = stream;
        holder.name.setText(stream.getHeader());
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        else return items.size();
    }

    public static class StreamViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.flag) ImageView flag;
        @BindView(R.id.name) TextView name;
        Stream stream;

        public StreamViewHolder(View itemView, View.OnClickListener clickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(clickListener);
        }
    }
}
