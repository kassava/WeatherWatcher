package com.shadiz.android.weatherwatcher.model;

import com.google.gson.annotations.Expose;

/**
 * Created by kassava on 11.01.17.
 */

class Weather {

    @Expose
    private Integer id;
    @Expose
    private String main;
    @Expose
    private Double description;
    @Expose
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getDescription() {
        return description;
    }

    public void setDescription(Double description) {
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
