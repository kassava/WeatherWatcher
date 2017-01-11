package com.shadiz.android.weatherwatcher.weather;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.layout.MvpViewStateFrameLayout;
import com.shadiz.android.weatherwatcher.R;
import com.shadiz.android.weatherwatcher.WeatherWatcherApp;
import com.shadiz.android.weatherwatcher.base.CustomViewState;
import com.shadiz.android.weatherwatcher.model.WeatherInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kassava on 11.01.17.
 */

public class WeatherLayout extends MvpViewStateFrameLayout<WeatherView, WeatherPresenter>
        implements WeatherView, SwipeRefreshLayout.OnRefreshListener {

    private Context context;
    private WeatherInfoAdapter adapter;
    private WeatherInfo weatherInfo = null;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.errorView) View errorView;
    @BindView(R.id.loadingView) View loadingView;
    @BindView(R.id.showfab) FloatingActionButton showFab;

    public WeatherLayout(Context ctx, AttributeSet attributeSet) {
        super(ctx, attributeSet);
        this.context = ctx;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        adapter = new WeatherInfoAdapter(LayoutInflater.from(context),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        );

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(false);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);

        showFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: add city choice.
            }
        });
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @NonNull
    @Override
    public ViewState<WeatherView> createViewState() {
        return new CustomViewState<WeatherInfo, WeatherView>();
    }

    @Override
    public void onNewViewStateInstance() {
        loadData(false);
    }

    @NonNull
    @Override
    public WeatherPresenter createPresenter() {
        return WeatherWatcherApp.getComponent().weatherPresenter();
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        castedViewState().setStateShowLoading(pullToRefresh);

        if (pullToRefresh) {
            loadingView.setVisibility(GONE);
            errorView.setVisibility(GONE);
            swipeRefreshLayout.setVisibility(VISIBLE);
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                }
            });
        } else {
            loadingView.setVisibility(VISIBLE);
            errorView.setVisibility(GONE);
            swipeRefreshLayout.setVisibility(GONE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void showContent() {
//        castedViewState().setStateShowContent(adapter.getItems());


        if (isRestoringViewState()) {
            swipeRefreshLayout.setVisibility(VISIBLE);
            errorView.setVisibility(GONE);
            loadingView.setVisibility(GONE);
        } else {
            swipeRefreshLayout.setAlpha(0f);
            swipeRefreshLayout.setVisibility(VISIBLE);
            swipeRefreshLayout.animate().alpha(1f).start();
            loadingView.animate().alpha(0f).
                    withEndAction(new Runnable() {
                                      @Override
                                      public void run() {
                                          loadingView.setVisibility(GONE);
                                          loadingView.setAlpha(1f);
                                      }
                                  }
                    );

            errorView.setVisibility(GONE);
        }

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        castedViewState().setStateShowError(e, pullToRefresh);

        if (pullToRefresh) {
            swipeRefreshLayout.setVisibility(VISIBLE);
            errorView.setVisibility(GONE);
            loadingView.setVisibility(GONE);
            Toast.makeText(context, "An error has occurred", Toast.LENGTH_SHORT).show();
        } else {
            swipeRefreshLayout.setVisibility(GONE);
            loadingView.setVisibility(GONE);
            errorView.setVisibility(VISIBLE);
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setData(WeatherInfo data) {
//        adapter.setItems(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadWeather(pullToRefresh);
    }

    private CustomViewState<WeatherInfo, WeatherView> castedViewState() {
        return (CustomViewState<WeatherInfo, WeatherView>)viewState;
    }
}
