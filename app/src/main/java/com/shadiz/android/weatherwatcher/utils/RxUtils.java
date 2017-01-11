package com.shadiz.android.weatherwatcher.utils;

import com.shadiz.android.weatherwatcher.network.WeatherError;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;

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

            if (execute.isSuccess()) {
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
