package com.ualr.emoweat.twitter.dto;

/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/

public class LocationWeatherDTO {
    private LocationDTO location;
    private WeatherDTO weather;
    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public WeatherDTO getWeather() {
        return weather;
    }

    public void setWeather(WeatherDTO weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "LocationWeatherDTO{" +
                "location=" + location +
                ", weather=" + weather +
                '}';
    }
}
