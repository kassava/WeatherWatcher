package com.shadiz.android.weatherwatcher.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by oldman on 11.01.17.
 */

class Temperature {
    @SerializedName("day")
    @Expose
    private Double average;
    @Expose
    private Double max;
    @Expose
    private Double min;
    @Expose
    private Double night;
    @Expose
    private Double eve;
    @Expose
    private Double morn;

    public Double getDay() {
        return average;
    }

    public void setDay(Double average) {
        this.average = average;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getNight() {
        return night;
    }

    public void setNight(Double night) {
        this.night = night;
    }

    public Double getEve() {
        return eve;
    }

    public void setEve(Double eve) {
        this.eve = eve;
    }

    public Double getMorn() {
        return morn;
    }

    public void setMorn(Double morn) {
        this.morn = morn;
    }


}
