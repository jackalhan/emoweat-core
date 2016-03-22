package com.ualr.emoweat.twitter.dto;

import twitter4j.GeoLocation;

/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/

public class LocationDTO {
    private GeoLocation geoLocation ;
    private String geoName;
    private String geoCode;
    private String geoDistrictName;
    private String geoCountryCode;
    private String geoCountryName;
    private String geoFullName;


    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getGeoCode() {
        return geoCode;
    }

    public void setGeoCode(String geoCode) {
        this.geoCode = geoCode;
    }


    public String getGeoName() {
        return geoName;
    }

    public void setGeoName(String geoName) {
        this.geoName = geoName;
    }

    public String getGeoDistrictName() {
        return geoDistrictName;
    }

    public void setGeoDistrictName(String geoDistrictName) {
        this.geoDistrictName = geoDistrictName;
    }

    public String getGeoCountryCode() {
        return geoCountryCode;
    }

    public void setGeoCountryCode(String geoCountryCode) {
        this.geoCountryCode = geoCountryCode;
    }

    public String getGeoCountryName() {
        return geoCountryName;
    }

    public void setGeoCountryName(String geoCountryName) {
        this.geoCountryName = geoCountryName;
    }

    public String getGeoFullName() {
        return geoFullName;
    }

    public void setGeoFullName(String geoFullName) {
        this.geoFullName = geoFullName;
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
                "geoLocation=" + geoLocation +
                ", geoName='" + geoName + '\'' +
                ", geoCode='" + geoCode + '\'' +
                ", geoDistrictName='" + geoDistrictName + '\'' +
                ", geoCountryCode='" + geoCountryCode + '\'' +
                ", geoCountryName='" + geoCountryName + '\'' +
                ", geoFullName='" + geoFullName + '\'' +
                '}';
    }
}
