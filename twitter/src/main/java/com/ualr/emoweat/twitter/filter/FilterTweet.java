package com.ualr.emoweat.twitter.filter;

import com.ualr.emoweat.twitter.dto.TweetDTO;
import com.ualr.emoweat.twitter.enums.FilterTweetType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - Tolgahan CAKALOGLU "Jackalhan"
 */

public class FilterTweet {

    public List<TweetDTO> filter(List<TweetDTO> tweetDTOs, List<TweetDTO> globalTweetList, FilterTweetType filterTweetType) {

        try {
            if (filterTweetType.toString() == FilterTweetType.GEO_POSITION_NOT_NULL.toString()) {
                for (TweetDTO tweetDTO : tweetDTOs) {
                    if (tweetDTO.getLocationWeather().getLocation().getGeoLocation() != null) {
                        if ((!tweetDTO.getLocationWeather().getLocation().getGeoCountryName().contains("?")) ||
                                (!tweetDTO.getLocationWeather().getLocation().getGeoDistrictName().contains("?")) ||
                                (!tweetDTO.getLocationWeather().getLocation().getGeoFullName().contains("?")) ||
                                (!tweetDTO.getLocationWeather().getLocation().getGeoCountryCode().contains("?"))
                                ) {
                            globalTweetList.add(tweetDTO);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return globalTweetList;
    }


}
