package com.ualr.emoweat.twitter.dto;

import java.sql.Timestamp;
import java.util.Comparator;

/**
 * @author - Tolgahan CAKALOGLU "Jackalhan"
 */

public class TweetDTO implements Comparable<TweetDTO> {

    private long id;
    private Timestamp time;
    private String text;
    private LocationWeatherDTO locationWeather;
    private Timestamp insertTime;


    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Timestamp insertTime) {
        this.insertTime = insertTime;
    }

    public LocationWeatherDTO getLocationWeather() {
        return locationWeather;
    }

    public void setLocationWeather(LocationWeatherDTO locationWeather) {
        this.locationWeather = locationWeather;
    }


    @Override
    public int compareTo(TweetDTO o) {
        return Comparators.ID.compare(this, o);
    }

    @Override
    public String toString() {
        return "TweetDTO{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", text='" + text + '\'' +
                ", locationWeather=" + locationWeather +
                ", insertTime=" + insertTime +
                '}';
    }


    public static class Comparators {

        public static Comparator<TweetDTO> ID = new Comparator<TweetDTO>() {
            @Override
            public int compare(TweetDTO o1, TweetDTO o2) {
                return o1.getId() < o2.getId() ? 1 :
                        o1.getId() > o2.getId() ? -1 : 0;
            }
        };
    }
}
