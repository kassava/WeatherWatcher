package com.shadiz.android.weatherwatcher.weather;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kassava on 11.01.17.
 */

public class WeatherScreen implements Parcelable {

    public static final Creator<WeatherScreen> CREATOR = new Creator<WeatherScreen>() {
        @Override
        public WeatherScreen createFromParcel(Parcel in) {
            return new WeatherScreen();
        }

        @Override
        public WeatherScreen[] newArray(int size) {
            return new WeatherScreen[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
