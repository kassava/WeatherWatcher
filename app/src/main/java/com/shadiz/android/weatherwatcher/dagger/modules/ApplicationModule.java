package com.shadiz.android.weatherwatcher.dagger.modules;

import com.shadiz.android.weatherwatcher.model.WeatherProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johny homicide on 11.01.17.
 */

@Module
public class ApplicationModule {

    @Provides @Singleton
    public WeatherProvider provideWeatherApi() {
        return new WeatherProvider();
    }
}
