package com.shadiz.android.weatherwatcher.network;

import android.content.Context;

import com.shadiz.android.weatherwatcher.R;
import com.shadiz.android.weatherwatcher.model.WeatherData;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by oldman on 11.01.17.
 */

public class WeatherService {
    @Inject
    Context context;
    @Inject
    WeatherApi weatherApi;

    public WeatherService(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public Call<WeatherData> getWeather(String city) {
        return weatherApi.getWeatherCity(city, context.getResources().getString(R.string.api_key));
    }

//    public Call<WeatherData> getWeather(String city, String country) {
//        return weatherApi.getWeatherCity(city + "," + country);
//    }
}
