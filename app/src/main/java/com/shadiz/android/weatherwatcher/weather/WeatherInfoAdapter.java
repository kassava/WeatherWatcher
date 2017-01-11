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

    private final int VIEWTYPE_TEXT = 0;
    private final int VIEWTYPE_IMAGE = 1;
    private LayoutInflater inflater;

    List<WeatherInfo> items = null;

    public WeatherInfoAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch(viewType) {
            case VIEWTYPE_IMAGE:
                break;
            case VIEWTYPE_TEXT:
                break;
            default:
//                throw new Exception("unkonown viewholder");
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        } else {
            return items.size();
        }
    }

    public static class InfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title) TextView title;
        @BindView(R.id.text) TextView text;

        public InfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public String toString() {
            return "InfoViewHolder " + super.toString();
        }
    }

    public static class InfoPictureViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;

        public InfoPictureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public String toString() {
            return "InfoPictureViewHolder " + super.toString();
        }
    }
}
