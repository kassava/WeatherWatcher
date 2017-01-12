package com.shadiz.android.weatherwatcher.dagger.modules;

/**
 * Created by johny homicide on 12.01.2017.
 */

import com.shadiz.android.weatherwatcher.network.WeatherApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by johny homicide on 11.01.17.
 */

@Module(includes = {RetrofitModule.class})
public class ApiModule {
    @Provides
    @Singleton
    public WeatherApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }
}
