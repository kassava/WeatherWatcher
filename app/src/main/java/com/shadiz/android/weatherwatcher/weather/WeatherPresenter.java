package com.shadiz.android.weatherwatcher.weather;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.shadiz.android.weatherwatcher.model.WeatherProvider;
import com.shadiz.android.weatherwatcher.model.WeatherData;
import com.shadiz.android.weatherwatcher.utils.RxUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by johny homicide on 11.01.17.
 */

public class WeatherPresenter extends MvpBasePresenter<WeatherView> {

    private WeatherProvider weatherProvider;
    private Subscription subscription = null;

    @Inject
    public WeatherPresenter(WeatherProvider weatherApi) {
        this.weatherProvider = weatherApi;
    }

    public void loadWeather(final boolean pullToRefresh, String city) {
        if (isViewAttached()) {
            getView().showLoading(pullToRefresh);
        }

        RxUtils.wrapAsync(weatherProvider.getWeatherData(city))
                .subscribe(weatherData -> {
                    if (isViewAttached()) {
                        getView().setData(weatherData);
                        getView().showContent();
                    }
                }, exception -> {
                    Log.d("MainActivity", exception.getMessage());
                    if (isViewAttached()) {
                        getView().showError(exception, pullToRefresh);
                    }
                });
    }

    @Override
    public void attachView(WeatherView view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance && subscription != null) {
            subscription.unsubscribe();
        }
    }
}
