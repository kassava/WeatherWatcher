package com.shadiz.android.weatherwatcher.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oldman on 11.01.17.
 */

public class WeatherResponse {
    @Expose
    private City city;
    @SerializedName("cod")
    @Expose
    private String code;
    @Expose
    private String message;
    @Expose
    private String cnt;
    @Expose
    private List<DayWeather> list = new ArrayList<DayWeather>();;

    public List<DayWeather> getList() {
        return list;
    }

    public void setList(List<DayWeather> list) {
        this.list = list;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


}
