package com.ualr.emoweat.twitter.service;

import com.ualr.emoweat.core.utils.Connector;
import com.ualr.emoweat.twitter.dto.LocationDTO;
import com.ualr.emoweat.twitter.dto.LocationWeatherDTO;
import com.ualr.emoweat.twitter.dto.TweetDTO;
import com.ualr.emoweat.twitter.dto.WeatherDTO;
import com.ualr.emoweat.twitter.enums.FilterTweetType;
import net.aksingh.java.api.owm.CurrentWeatherData;
import net.aksingh.java.api.owm.OpenWeatherMap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - Tolgahan CAKALOGLU "Jackalhan"
 */

public class WeatherService {

    public List<TweetDTO> fillWeatherInfo(List<TweetDTO> tweetDTOs) {
        List<TweetDTO> tweetDTOList = null;
        try {
            tweetDTOList = new ArrayList<TweetDTO>();
            WeatherDTO newWeatherDTO = null;
            TweetDTO newTweetDTO = null;
            LocationWeatherDTO newLocationWeatherDTO = null;

            for (TweetDTO tweetDTO : tweetDTOs) {
                newWeatherDTO = CacheService.getInstance().getWeather(tweetDTO.getLocationWeather().getLocation());
                newTweetDTO = new TweetDTO();
                newLocationWeatherDTO = new LocationWeatherDTO();

                newTweetDTO.setText(tweetDTO.getText());
                newTweetDTO.setInsertTime(tweetDTO.getInsertTime());
                newTweetDTO.setTime(tweetDTO.getTime());
                newTweetDTO.setId(tweetDTO.getId());
                newLocationWeatherDTO.setLocation(tweetDTO.getLocationWeather().getLocation());
                if (newWeatherDTO != null) {
                    newLocationWeatherDTO.setWeather(newWeatherDTO);
                    newTweetDTO.setLocationWeather(newLocationWeatherDTO);
                    tweetDTOList.add(newTweetDTO);
                } else {
                    newWeatherDTO = getWeatherInfo(tweetDTO);
                    if (newWeatherDTO.getCapturedTime() != null || newWeatherDTO.getCapturedTime().equals("")) {
                        newLocationWeatherDTO.setWeather(newWeatherDTO);
                        newTweetDTO.setLocationWeather(newLocationWeatherDTO);
                        tweetDTOList.add(newTweetDTO);
                        insertWeather(newTweetDTO);
                        CacheService.getInstance().forceUpdate();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tweetDTOList;
    }

    private WeatherDTO getWeatherInfo(TweetDTO tweetDTO) {
        WeatherDTO weatherDTO = null;
        try {
            weatherDTO = new WeatherDTO();
            OpenWeatherMap openWeatherMap = new OpenWeatherMap("");
            CurrentWeatherData currentWeatherData = null;
            if (tweetDTO.getLocationWeather().getLocation().getGeoLocation().getLongitude() == 0 && tweetDTO.getLocationWeather().getLocation().getGeoLocation().getLatitude() == 0) {
                currentWeatherData = openWeatherMap.currentWeatherByCoordinates((float) tweetDTO.getLocationWeather().getLocation().getGeoLocation().getLatitude(),
                        (float) tweetDTO.getLocationWeather().getLocation().getGeoLocation().getLongitude());
            } else {
                currentWeatherData = openWeatherMap.currentWeatherByCityName(tweetDTO.getLocationWeather().getLocation().getGeoDistrictName(), tweetDTO.getLocationWeather().getLocation().getGeoCountryCode());

            }
            weatherDTO.setSeq(tweetDTO.getId());
            weatherDTO.setCapturedTime(new Timestamp(currentWeatherData.getDateTime().getTime()));
            weatherDTO.setTemperature(String.valueOf(currentWeatherData.getMainData_Object().getTemperature()));
            weatherDTO.setHumidity(String.valueOf(currentWeatherData.getMainData_Object().getHumidity()));
            weatherDTO.setWindStatus(String.valueOf(currentWeatherData.getWind_Object().getWindGust()));
            weatherDTO.setCloudiness(String.valueOf(currentWeatherData.getWeather_List().get(0).getWeatherDescription()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return weatherDTO;
    }


    private void insertWeather(TweetDTO tweetDTO) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Connector.getConnect();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO buf_weather (seq, fromLatitude, fromLongitude, fromGeoName, fromGeoCode, fromDistrictName, fromCountryCode, fromCountryName, fromFullName, weatWindStatus, weatCloudiness, weatHumidity, weatImageUrl, weatTemperature, weatTemperatureType, weatCapturedTime, insertTime) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, tweetDTO.getId());
            preparedStatement.setDouble(2, tweetDTO.getLocationWeather().getLocation().getGeoLocation().getLatitude());
            preparedStatement.setDouble(3, tweetDTO.getLocationWeather().getLocation().getGeoLocation().getLongitude());
            preparedStatement.setString(4, tweetDTO.getLocationWeather().getLocation().getGeoName());
            preparedStatement.setString(5, tweetDTO.getLocationWeather().getLocation().getGeoCode());
            preparedStatement.setString(6, tweetDTO.getLocationWeather().getLocation().getGeoDistrictName());
            preparedStatement.setString(7, tweetDTO.getLocationWeather().getLocation().getGeoCountryCode());
            preparedStatement.setString(8, tweetDTO.getLocationWeather().getLocation().getGeoCountryName());
            preparedStatement.setString(9, tweetDTO.getLocationWeather().getLocation().getGeoFullName());
            preparedStatement.setString(10, tweetDTO.getLocationWeather().getWeather().getWindStatus());
            preparedStatement.setString(11, tweetDTO.getLocationWeather().getWeather().getCloudiness());
            preparedStatement.setString(12, tweetDTO.getLocationWeather().getWeather().getHumidity());
            preparedStatement.setString(13, tweetDTO.getLocationWeather().getWeather().getImageUrl());
            preparedStatement.setString(14, tweetDTO.getLocationWeather().getWeather().getTemperature());
            preparedStatement.setString(15, tweetDTO.getLocationWeather().getWeather().getTemperatureType());
            preparedStatement.setTimestamp(16, tweetDTO.getLocationWeather().getWeather().getCapturedTime());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            ex.printStackTrace();
        } catch (Exception ex) {
            connection.rollback();
            ex.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            try {
                if (connection != null)
                    connection.close();
                if (!connection.isClosed())
                    connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
