package com.shadiz.android.weatherwatcher.flow;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadiz.android.weatherwatcher.R;
import com.shadiz.android.weatherwatcher.weather.WeatherScreen;

import flow.Dispatcher;
import flow.Traversal;
import flow.TraversalCallback;

/**
 * Created by johny homicide on 11.01.17.
 */

final public class AppDispatcher implements Dispatcher {

    private final String LOG_TAG = AppDispatcher.class.getSimpleName();
    private final Activity activity;

    public AppDispatcher(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void dispatch(@NonNull Traversal traversal, @NonNull TraversalCallback callback) {
        Log.d(LOG_TAG, "dispatching " + traversal);
        Object destination = traversal.destination.top(); // destination key

        // Update container: remove oldView, insert newView
        ViewGroup frame = (ViewGroup) activity.findViewById(R.id.container);

        Log.d(LOG_TAG, "getChildCount: " + frame.getChildCount());
        Log.d(LOG_TAG, "traversal: " + traversal.origin);

        // Remove current screen from container. Something is wrong in this method.
        if (traversal.origin != null) {
            Log.d(LOG_TAG, "remove current screen from container");

            if (frame.getChildCount() > 0) {
                traversal.getState(traversal.origin.top()).save(frame.getChildAt(0));
                frame.removeAllViews();
            }
        }

        // This block is used for cleaning view from container.
        if (frame.getChildCount() > 0) {
            frame.removeAllViews();
        }

        @LayoutRes final int layout;
        if (destination instanceof WeatherScreen) {
            layout = R.layout.screen_weather;
        } else {
            throw new AssertionError("Unrecognized screen " + destination);
        }

        View incomingView = LayoutInflater.from(traversal.createContext(destination, activity))
                .inflate(layout, frame, false);

        // Add new screen
        frame.addView(incomingView);

        // Restore state before adding view (i.e. caused by onBackPressed)
        traversal.getState(traversal.destination.top()).restore(incomingView);

        callback.onTraversalCompleted(); // Tell Flow that we are done
    }
}
