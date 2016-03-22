package com.ualr.emoweat.twitter.dto;


import java.sql.Date;
import java.sql.Timestamp;

/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/

public class WeatherDTO {

    private Long seq;
    private Timestamp capturedTime;
    private String windStatus;
    private String cloudiness;
    private String humidity;
    private String imageUrl;
    private String temperature;
    private String temperatureType;

    public Timestamp getCapturedTime() {
        return capturedTime;
    }

    public void setCapturedTime(Timestamp capturedTime) {
        this.capturedTime = capturedTime;
    }

    public String getWindStatus() {
        return windStatus;
    }

    public void setWindStatus(String windStatus) {
        this.windStatus = windStatus;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(String cloudiness) {
        this.cloudiness = cloudiness;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureType() {
        return temperatureType;
    }

    public void setTemperatureType(String temperatureType) {
        this.temperatureType = temperatureType;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }


    @Override
    public String toString() {
        return "WeatherDTO{" +
                "seq=" + seq +
                ", capturedTime=" + capturedTime +
                ", windStatus='" + windStatus + '\'' +
                ", cloudiness='" + cloudiness + '\'' +
                ", humidity='" + humidity + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", temperature='" + temperature + '\'' +
                ", temperatureType='" + temperatureType + '\'' +
                '}';
    }
}
