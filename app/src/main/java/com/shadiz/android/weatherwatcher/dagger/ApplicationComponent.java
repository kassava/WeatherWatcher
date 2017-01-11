package com.shadiz.android.weatherwatcher.dagger;

import android.content.Context;

import com.shadiz.android.weatherwatcher.dagger.modules.ContextModule;
import com.shadiz.android.weatherwatcher.dagger.modules.WeatherModule;
import com.shadiz.android.weatherwatcher.network.WeatherService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by oldman on 11.01.17.
 */
@Singleton
@Component(modules = {WeatherModule.class, ContextModule.class})
public interface ApplicationComponent {
    Context getContext();
    WeatherService getWeatherService();

//    void inject( presenter);
}
