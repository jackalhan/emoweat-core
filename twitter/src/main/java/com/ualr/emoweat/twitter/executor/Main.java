package com.ualr.emoweat.twitter.executor;
import com.ualr.emoweat.core.utils.Converter;
import com.ualr.emoweat.twitter.dto.LocationDTO;
import com.ualr.emoweat.twitter.dto.LocationWeatherDTO;
import com.ualr.emoweat.twitter.dto.TweetDTO;
import com.ualr.emoweat.twitter.enums.FilterTweetType;
import com.ualr.emoweat.twitter.filter.FilterTweet;
import com.ualr.emoweat.twitter.service.AccessBuilderService;
import com.ualr.emoweat.twitter.service.TwitterService;
import com.ualr.emoweat.twitter.service.WeatherService;
import twitter4j.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;


/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/

public class Main {

    public static void main(String[] args) {

        Query query = new Query("lang:en +exclude:retweets");
        QueryResult result;
        List<TweetDTO> tweetDTOList = new ArrayList<TweetDTO>();
        List<TweetDTO> globalTweetDTOList = new ArrayList<TweetDTO>();

        FilterTweet filterTweet = new FilterTweet();
        WeatherService weatherService = null;
        TwitterService twitterService = null;

        try {

            do {
                result = AccessBuilderService.build().search(query);
                List<Status> tweets = result.getTweets();
                TweetDTO tweetDTO = null;
                LocationWeatherDTO locationWeatherDTO = null;
                LocationDTO locationDTO = null;
                weatherService = new WeatherService();
                tweetDTOList.clear();
                Converter converter = new Converter();
                for (Status tweet : tweets) {
                    System.out.println("Before UTF8 : Time: " + tweet.getCreatedAt() + " Location : " + tweet.getGeoLocation() + " @" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                    System.out.println("After  UTF8 : Time: " + tweet.getCreatedAt() + " Location : " + tweet.getGeoLocation() + " @" + tweet.getUser().getScreenName() + " - " + converter.eliminateNonUnicodes(tweet.getText()));
                    System.out.println(tweet.getId());

                    tweetDTO = new TweetDTO();
                    locationDTO = new LocationDTO();
                    locationWeatherDTO = new LocationWeatherDTO();
                    tweetDTO.setId(tweet.getId());
                    tweetDTO.setInsertTime(new Timestamp(new Date().getTime()));
                    tweetDTO.setText(converter.eliminateNonUnicodes(tweet.getText()));
                    locationDTO.setGeoLocation(tweet.getGeoLocation());
                    Locale enLocale = new Locale("en_US");
                    try {
                        locationDTO.setGeoDistrictName(tweet.getPlace().getName().toUpperCase(enLocale));
                        locationDTO.setGeoCountryCode(tweet.getPlace().getCountryCode().toUpperCase(enLocale));
                        locationDTO.setGeoCountryName(tweet.getPlace().getCountry().toUpperCase(enLocale));
                        locationDTO.setGeoFullName(tweet.getPlace().getFullName().toUpperCase(enLocale));
                    } catch (Exception ex) {
                        System.out.println(".......No geo info........");
                    }
                    locationWeatherDTO.setLocation(locationDTO);
                    tweetDTO.setLocationWeather(locationWeatherDTO);
                    tweetDTO.setTime(new Timestamp(tweet.getCreatedAt().getTime()));
                    tweetDTOList.add(tweetDTO);
                }
                globalTweetDTOList = filterTweet.filter(tweetDTOList, globalTweetDTOList, FilterTweetType.GEO_POSITION_NOT_NULL);
                globalTweetDTOList = weatherService.fillWeatherInfo(globalTweetDTOList);

            } while ((query = result.nextQuery()) != null);

        } catch (Exception ex) {

            twitterService = new TwitterService();
            try {

                if (globalTweetDTOList.size() != 0) {
                    System.out.println("===================== Tweets are being writing to DB ===================");
                    twitterService.insertTweet(globalTweetDTOList);
                    System.out.println("===================== Tweets are in DB  ===================");
                }

            } catch (SQLException e) {
                System.out.println("===================== Exception while inserting them to DB  ===================");
                e.printStackTrace();
            }
            System.out.println("===================== Error for getting tweets from Twitter ===================");
            ex.printStackTrace();
        }

    }

}
