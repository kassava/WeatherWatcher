package com.shadiz.android.weatherwatcher.weather;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.shadiz.android.weatherwatcher.model.Weather;
import com.shadiz.android.weatherwatcher.model.WeatherApi;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by kassava on 11.01.17.
 */

public class WeatherPresenter extends MvpBasePresenter<WeatherView> {

    private WeatherApi weatherApi;

    @Inject
    public WeatherPresenter(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public void loadWeather(final boolean pullToRefresh) {
        if (isViewAttached()) {
            getView().showLoading(pullToRefresh);
        }

        try {
            Thread.sleep(2000);
            if (isViewAttached()) {
                getView().showContent();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
