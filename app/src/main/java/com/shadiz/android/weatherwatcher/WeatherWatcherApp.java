package com.shadiz.android.weatherwatcher;

import android.app.Application;

import com.shadiz.android.weatherwatcher.dagger.ApplicationComponent;

/**
 * Created by kassava on 11.01.17.
 */

public class WeatherWatcherApp extends Application {

    private static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = com.shadiz.android.weatherwatcher.dagger.DaggerApplicationComponent.create();
    }

    public static ApplicationComponent getComponent() {
        return component;
    }
}
