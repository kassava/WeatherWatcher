package com.shadiz.android.weatherwatcher.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by oldman on 11.01.17.
 */

class DayWeather {
    @SerializedName("dt")
    @Expose
    private long date;
    @SerializedName("temp")
    @Expose
    private Temperature temperature;
    @Expose
    private Double pressure;
    @Expose
    private Integer humidity;
    @Expose
    private Double speed;
    @Expose
    private Double deg;
    @Expose
    private Weather weather;


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
}
