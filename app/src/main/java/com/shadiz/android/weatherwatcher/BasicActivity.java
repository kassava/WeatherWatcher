package com.shadiz.android.weatherwatcher;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shadiz.android.weatherwatcher.flow.AppDispatcher;
import com.shadiz.android.weatherwatcher.flow.AppKeyParceler;
import com.shadiz.android.weatherwatcher.weather.WeatherScreen;

import flow.Flow;

/**
 * Created by kassava on 11.01.17.
 */

public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basic);
    }

    @Override
    protected void attachBaseContext(Context baseContextWrapper) {
        baseContextWrapper = Flow.configure(baseContextWrapper, this)
                .dispatcher(new AppDispatcher(this))
                .defaultKey(new WeatherScreen())
                .keyParceler(new AppKeyParceler())
                .install();
        super.attachBaseContext(baseContextWrapper);
    }

    @Override
    public void onBackPressed() {
        if (!Flow.get(this).goBack()) {
            super.onBackPressed();
        }
    }
}