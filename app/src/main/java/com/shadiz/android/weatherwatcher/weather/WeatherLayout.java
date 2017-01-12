package com.shadiz.android.weatherwatcher.weather;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.layout.MvpViewStateFrameLayout;
import com.shadiz.android.weatherwatcher.R;
import com.shadiz.android.weatherwatcher.WeatherWatcherApp;
import com.shadiz.android.weatherwatcher.model.WeatherData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by johny homicide on 11.01.17.
 */

public class WeatherLayout extends MvpViewStateFrameLayout<WeatherView, WeatherPresenter>
        implements WeatherView, SwipeRefreshLayout.OnRefreshListener {

    private Context context;
    private ListAdapter adapter;
    private WeatherData weatherData = null;
    private String cityName;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.errorView) View errorView;
    @BindView(R.id.loadingView) View loadingView;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.city) TextView countryTextView;
    @BindView(R.id.coordinates) TextView coordinatesTextView;

    public WeatherLayout(Context ctx, AttributeSet attributeSet) {
        super(ctx, attributeSet);
        this.context = ctx;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        cityName = getSharedPreferences().getString("CITYNAME", "Riga");

        adapter = new ListAdapter(LayoutInflater.from(context));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(false);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final EditText editText = new EditText(context);

                builder.setTitle("Enter the name of the city.")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                cityName = editText.getText().toString();
                                presenter.loadWeather(false, cityName);

                                getSharedPreferences().edit().putString("CITYNAME", cityName).apply();
                            }
                        })
                        .setView(editText);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private SharedPreferences getSharedPreferences() {
        return context
                .getSharedPreferences("com.android.shadiz.weatherwatcher",
                        Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewState<WeatherView> createViewState() {
        return new CustomViewState<WeatherData, WeatherView>();
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
        castedViewState().setStateShowContent(weatherData);

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
    public void setData(WeatherData data) {
        this.weatherData = data;

        countryTextView.setText(weatherData.getCity().getName() + ", "
                + weatherData.getCity().getCountry());
        coordinatesTextView.setText(weatherData.getCity().getCoord().getLat() + " - "
                + weatherData.getCity().getCoord().getLon());

        adapter.setItems(weatherData.getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadWeather(pullToRefresh, cityName);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    private CustomViewState<WeatherData, WeatherView> castedViewState() {
        return (CustomViewState<WeatherData, WeatherView>)viewState;
    }
}
