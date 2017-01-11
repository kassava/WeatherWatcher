package com.shadiz.android.weatherwatcher.weather;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.shadiz.android.weatherwatcher.model.City;
import com.shadiz.android.weatherwatcher.model.DayWeather;
import com.shadiz.android.weatherwatcher.model.Temperature;
import com.shadiz.android.weatherwatcher.model.WeatherApi;
import com.shadiz.android.weatherwatcher.model.WeatherData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kassava on 11.01.17.
 */

public class WeatherPresenter extends MvpBasePresenter<WeatherView> {

    private WeatherApi weatherApi;
    private Subscription subscription = null;

    @Inject
    public WeatherPresenter(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public void loadWeather(final boolean pullToRefresh) {
        if (isViewAttached()) {
            getView().showLoading(pullToRefresh);
        }

        subscription = weatherApi.getWeatherData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherData>() {
                    @Override
                    public void onCompleted() {
                        if (isViewAttached()) {
                            getView().showContent();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().showError(e, pullToRefresh);
                        }
                    }

                    @Override
                    public void onNext(WeatherData weatherData) {
                        if (isViewAttached()) {
                            getView().setData(weatherData);
                        }
                    }
                });
    }
}
