package com.shadiz.android.weatherwatcher.dagger;

import com.shadiz.android.weatherwatcher.model.WeatherApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kassava on 11.01.17.
 */

@Module
public class ApplicationModule {

    @Provides @Singleton
    public WeatherApi provideWeatherApi() {
        return new WeatherApi();
    }
}
