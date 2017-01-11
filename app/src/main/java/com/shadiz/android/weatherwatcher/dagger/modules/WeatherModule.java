package com.shadiz.android.weatherwatcher.dagger.modules;

import com.shadiz.android.weatherwatcher.network.WeatherApi;
import com.shadiz.android.weatherwatcher.network.WeatherService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by oldman on 11.01.17.
 */
@Module(includes = {ApiModule.class})
public class WeatherModule {
    @Provides
    @Singleton
    public WeatherService provideWeatherService(WeatherApi authApi) {
        return new WeatherService(authApi);
    }
}
