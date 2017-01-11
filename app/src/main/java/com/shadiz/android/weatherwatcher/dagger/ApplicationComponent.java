package com.shadiz.android.weatherwatcher.dagger;

import com.shadiz.android.weatherwatcher.weather.WeatherPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kassava on 11.01.17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    public WeatherPresenter weatherPresenter();
}
