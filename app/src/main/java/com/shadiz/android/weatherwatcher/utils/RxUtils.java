package com.shadiz.android.weatherwatcher.utils;

import android.util.Log;

import com.shadiz.android.weatherwatcher.model.WeatherData;
import com.shadiz.android.weatherwatcher.network.WeatherError;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oldman on 11.01.17.
 */

public class RxUtils {
    public static <T> Observable<T> wrapRetrofitCall(final Call<T> call) {
        return Observable.create(subscriber ->
        {
            final Response<T> execute;
            try {
                execute = call.execute();
            } catch (IOException e) {
                subscriber.onError(e);
                return;
            }
            if (execute.isSuccessful()) {
                subscriber.onNext(execute.body());
            } else {
                subscriber.onError(new WeatherError(execute.errorBody()));
            }
        });
    }

    public static <T> Observable<T> wrapAsync(Observable<T> observable) {
        return wrapAsync(observable, Schedulers.io());
    }

    public static <T> Observable<T> wrapAsync(Observable<T> observable, Scheduler scheduler) {
        return observable
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
