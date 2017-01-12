package com.shadiz.android.weatherwatcher.dagger;

import android.content.Context;

import com.shadiz.android.weatherwatcher.dagger.modules.ApplicationModule;
import com.shadiz.android.weatherwatcher.dagger.modules.ContextModule;
import com.shadiz.android.weatherwatcher.dagger.modules.WeatherModule;
import com.shadiz.android.weatherwatcher.network.WeatherService;
import com.shadiz.android.weatherwatcher.weather.WeatherPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by johny homicide on 11.01.17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, WeatherModule.class, ContextModule.class})
public interface ApplicationComponent {
    public Context context();
    public WeatherService weatherService();
    public WeatherPresenter weatherPresenter();
}
