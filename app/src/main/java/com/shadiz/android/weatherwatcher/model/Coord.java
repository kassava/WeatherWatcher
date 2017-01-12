package com.shadiz.android.weatherwatcher.model;

import com.google.gson.annotations.Expose;

/**
 * Created by johny homicide on 11.01.17.
 */

public class Coord {
    @Expose
    private Double lon;
    @Expose
    private Double lat;

    /**
     * @return The longitude
     */
    public Double getLon() {
        return lon;
    }

    /**
     * @param lon The longitude
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * @return The latitude
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @param lat The latitude
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }
}
