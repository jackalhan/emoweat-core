package com.ualr.emoweat.twitter.enums;

/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/

public enum FilterTweetType {
    GEO_POSITION_NOT_NULL(1),
    WEATHER_DATA_ALREADY_HAVE_WITH_SPECIFIED_TIME(2);
    private Integer _id;

    private FilterTweetType(Integer id) {
        _id = id;
    }

    public Integer getId() {
        return _id;
    }
}
