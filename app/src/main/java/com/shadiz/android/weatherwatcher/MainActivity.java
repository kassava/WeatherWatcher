package com.shadiz.android.weatherwatcher;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.shadiz.android.weatherwatcher.model.WeatherData;
import com.shadiz.android.weatherwatcher.network.WeatherService;
import com.shadiz.android.weatherwatcher.utils.RxUtils;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {
//    @Inject
    WeatherService weatherService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        InjectorClass.inject(this);
        weatherService = ((App)getApplication()).getComponent().getWeatherService();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                sendRequest("Saint-Petersburg");
            }
        });
    }
    private void sendRequest(String city){

        Observable<WeatherData> weatherObservable = RxUtils.wrapRetrofitCall(weatherService.getWeather(city));

        RxUtils.wrapAsync(weatherObservable)
                .subscribe(weatherData -> {
                    Log.d("MainActivity", "Success ");

                    Log.d("MainActivity", "Success " + weatherData.getCity().getName());
                }, exception -> {
                    Log.d("MainActivity", exception.getMessage());
                });
    }
}
