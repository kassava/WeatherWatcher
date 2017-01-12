package com.shadiz.android.weatherwatcher.network;

/**
 * Created by kassava on 12.01.2017.
 */

import com.shadiz.android.weatherwatcher.R;
import com.shadiz.android.weatherwatcher.WeatherWatcherApp;
import com.shadiz.android.weatherwatcher.model.WeatherData;

import retrofit2.Call;

/**
 * Created by johny homicide on 11.01.17.
 */

public class WeatherService {

    private WeatherApi weatherApi;

    public WeatherService(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public Call<WeatherData> getWeather(String city) {
        return weatherApi.getWeatherCity(city, WeatherWatcherApp.getComponent().context()
                .getResources().getString(R.string.api_key));
    }

    public Call<WeatherData> getWeather(String city, String country) {
        return weatherApi.getWeatherCity(city + "," + country, WeatherWatcherApp.getComponent()
                .context().getResources().getString(R.string.api_key));
    }
}
