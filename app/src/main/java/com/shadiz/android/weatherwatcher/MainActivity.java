package com.shadiz.android.weatherwatcher;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shadiz.android.weatherwatcher.model.WeatherData;

import javax.inject.Inject;

import rx.Observable;

public class MainActivity extends AppCompatActivity {
//    @Inject
//    NetworkApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        InjectorClass.inject(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Observable<WeatherData> userObservable = RxUtils.wrapRetrofitCall(mGithubService.signIn(token))
                        .doOnNext(user -> AuthUtils.setToken(token));

                RxUtils.wrapAsync(userObservable)
                        .subscribe(user -> {
                            getViewState().hideProgress();
                            getViewState().successSignIn();
                        }, exception -> {
                            getViewState().hideProgress();
                            getViewState().showError(exception.getMessage());
                        });
            }
        });
    }

}
