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

    }

    public static ApplicationComponent getComponent() {
        return component;
    }
}
