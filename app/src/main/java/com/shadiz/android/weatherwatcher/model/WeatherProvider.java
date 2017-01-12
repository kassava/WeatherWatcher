package com.shadiz.android.weatherwatcher.model;

import android.util.Log;

import com.shadiz.android.weatherwatcher.WeatherWatcherApp;
import com.shadiz.android.weatherwatcher.network.WeatherService;
import com.shadiz.android.weatherwatcher.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by johny homicide on 11.01.17.
 */

public class WeatherProvider {

    public Observable<WeatherData> getWeatherData(String cityName) {

        WeatherService weatherService = WeatherWatcherApp.getComponent().weatherService();
        Observable<WeatherData> weatherObservable = RxUtils.wrapRetrofitCall(weatherService.getWeather(cityName));

        return weatherObservable;
    }
}
