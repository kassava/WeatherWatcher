package com.shadiz.android.weatherwatcher.weather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.layout.MvpViewStateFrameLayout;
import com.shadiz.android.weatherwatcher.model.Weather;

/**
 * Created by kassava on 11.01.17.
 */

public class WeatherLayout extends MvpViewStateFrameLayout<WeatherView, WeatherPresenter>
        implements WeatherView, SwipeRefreshLayout.OnRefreshListener {

    private Context context;
    private WeatherInfoAdapter adapter;

    public WeatherLayout(Context ctx, AttributeSet attributeSet) {
        super(ctx, attributeSet);
        this.context = ctx;
        adapter = new WeatherInfoAdapter(LayoutInflater.from(context));
    }

    @Override
    public void onRefresh() {

    }

    @NonNull
    @Override
    public ViewState<WeatherView> createViewState() {
        return null;
    }

    @Override
    public void onNewViewStateInstance() {

    }

    @Override
    public WeatherPresenter createPresenter() {
        return null;
    }

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {

    }

    @Override
    public void setData(Weather data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
