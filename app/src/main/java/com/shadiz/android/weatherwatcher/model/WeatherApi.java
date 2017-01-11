package com.shadiz.android.weatherwatcher.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by kassava on 11.01.17.
 */

public class WeatherApi {

    public Observable<WeatherData> getWeatherData() {
        WeatherData weatherData = new WeatherData();
        City city = new City();
        city.setCountry("RF");
        city.setName("SPB");
        city.setPopulation(123);
        city.setId(7);
        Coordinates coordinates = new Coordinates();
        coordinates.setLat(123455566.898);
        coordinates.setLon(0890207.565);
        city.setCoordinates(coordinates);
        weatherData.setCity(city);
        weatherData.setCnt("war");
        weatherData.setCode("asdf");
        weatherData.setMessage("ussr");
        List<DayWeather> list = new ArrayList<>();
        DayWeather dayWeather = new DayWeather();
        dayWeather.setDate(123456);
        dayWeather.setDeg(230.01);
        dayWeather.setHumidity(34);
        dayWeather.setSpeed(15.0);
        dayWeather.setPressure(365.9);
        Temperature temperature = new Temperature();
        temperature.setDay(-15.4);
        dayWeather.setTemperature(temperature);
        list.add(dayWeather);
        dayWeather = new DayWeather();
        dayWeather.setDate(456789);
        dayWeather.setDeg(30.01);
        dayWeather.setHumidity(70);
        dayWeather.setSpeed(155.0);
        dayWeather.setPressure(235.9);
        temperature = new Temperature();
        temperature.setDay(-5.4);
        dayWeather.setTemperature(temperature);
        list.add(dayWeather);
        weatherData.setList(list);

        return Observable.just(weatherData).delay(2000, TimeUnit.MILLISECONDS);
    }
}
