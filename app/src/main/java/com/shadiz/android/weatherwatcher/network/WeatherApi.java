package com.shadiz.android.weatherwatcher.network;

import android.database.Observable;

import com.shadiz.android.weatherwatcher.model.WeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by oldman on 11.01.17.
 */

public interface WeatherApi {
    Integer PAGE_SIZE = 50;

    @GET("/weather")
    Call<WeatherData> getWeatherCity(@Query("q") String city,  @Query("appid") String apiKey);

//    @GET("/weather")
//    Call<WeatherData> getWeatherCity(@Query("q") String city, Callback<WeatherData> response);

}