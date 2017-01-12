package com.shadiz.android.weatherwatcher.network;

import com.shadiz.android.weatherwatcher.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by oldman on 11.01.17.
 */

public interface WeatherApi {
    @GET("forecast/daily?mode=json&units=metric&cnt=7")
    Call<WeatherData> getWeatherCity(@Query("q") String city, @Query("appid") String appid);

}