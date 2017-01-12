package com.shadiz.android.weatherwatcher;

import android.app.Application;

import com.shadiz.android.weatherwatcher.dagger.ApplicationComponent;
import com.shadiz.android.weatherwatcher.dagger.modules.ContextModule;

/**
 * Created by johny homicide on 11.01.17.
 */

public class WeatherWatcherApp extends Application {

    private static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = com.shadiz.android.weatherwatcher.dagger.DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this)).build();
    }

    public static ApplicationComponent getComponent() {
        return component;
    }
}
