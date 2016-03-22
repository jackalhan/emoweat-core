package com.ualr.emoweat.twitter.service;

import com.ualr.emoweat.core.utils.Connector;
import com.ualr.emoweat.core.utils.Constant;
import com.ualr.emoweat.core.utils.Property;
import com.ualr.emoweat.core.utils.SimpleDate;
import com.ualr.emoweat.twitter.dto.LocationDTO;
import com.ualr.emoweat.twitter.dto.LocationWeatherDTO;
import com.ualr.emoweat.twitter.dto.WeatherDTO;
import twitter4j.GeoLocation;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - Tolgahan CAKALOGLU "Jackalhan"
 */

public class CacheService {

    public static CacheService instance = new CacheService();
    private List<LocationWeatherDTO> locationWeatherDTOs = null;
    private final String datacacheUpdatePeriodInMinutesName = "WEATHER_CACHING_MINUTE";
    private Integer datacacheUpdatePeriodInMinutes;
    private SimpleDate lastUpdateTime = null;

    private CacheService() {
        super();
    }

    public void forceUpdate() throws Exception {
        synchronized (this) {
            initialize();
        }
    }

    protected void initialize() throws Exception {
        try {
            initializeLocationWeather();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeLocationWeather() {
        try {
            locationWeatherDTOs = getAllLocationWeatherInfo();
            setLastUpdateTime(new SimpleDate());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private List<LocationWeatherDTO> getAllLocationWeatherInfo() {
        Connection connection = null;
        List<LocationWeatherDTO> locationWeatherDTOList = new ArrayList<LocationWeatherDTO>();
        LocationWeatherDTO locationWeatherDTO = null;
        LocationDTO locationDTO = null;
        WeatherDTO weatherDTO = null;
        GeoLocation geoLocation = null;
        String sql = "SELECT * FROM buf_weather as bw WHERE (bw.fromCountryName not like '%?%' or bw.fromDistrictName  not like '%?%'  or bw.fromFullName not like '%?%')  and bw.insertTime > NOW() - interval ? minute";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Connector.getConnect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.valueOf(Property.getValue(datacacheUpdatePeriodInMinutesName)));
            setDatacacheUpdatePeriodInMinutes(Integer.parseInt(Property.getValue(datacacheUpdatePeriodInMinutesName)));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                locationWeatherDTO = new LocationWeatherDTO();
                locationDTO = new LocationDTO();
                geoLocation = new GeoLocation(resultSet.getDouble("fromLatitude"), resultSet.getDouble("fromLongitude"));
                locationDTO.setGeoLocation(geoLocation);
                locationDTO.setGeoCountryCode(resultSet.getString("fromCountryCode"));
                locationDTO.setGeoFullName(resultSet.getString("fromFullName"));
                locationDTO.setGeoCountryName(resultSet.getString("fromCountryName"));
                locationDTO.setGeoCode(resultSet.getString("fromGeoCode"));
                locationDTO.setGeoDistrictName(resultSet.getString("fromDistrictName"));
                locationDTO.setGeoName(resultSet.getString("fromGeoName"));
                locationWeatherDTO.setLocation(locationDTO);

                weatherDTO = new WeatherDTO();
                try {
                    weatherDTO.setCapturedTime(Timestamp.valueOf(resultSet.getString("weatCapturedTime")));
                    weatherDTO.setWindStatus(resultSet.getString("weatWindStatus"));
                    weatherDTO.setCloudiness(resultSet.getString("weatCloudiness"));
                    weatherDTO.setHumidity(resultSet.getString("weatHumidity"));
                    weatherDTO.setImageUrl(resultSet.getString("weatImageUrl"));
                    weatherDTO.setTemperature(resultSet.getString("weatTemperature"));
                    weatherDTO.setTemperatureType(resultSet.getString("weatTemperatureType"));
                    weatherDTO.setSeq(resultSet.getLong("seq"));
                    locationWeatherDTO.setWeather(weatherDTO);
                    locationWeatherDTOList.add(locationWeatherDTO);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }

            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
                if (!connection.isClosed())
                    connection.close();
            } catch (Exception ex3) {
                ex3.printStackTrace();

            }

        }
        return locationWeatherDTOList;
    }


    public static CacheService getInstance() throws Exception {


        try {
            if (instance.getLastUpdateTime() == null ||
                    (new SimpleDate()).after(instance.getLastUpdateTime().getMinutesLater(instance.getDatacacheUpdatePeriodInMinutes()))) {
                instance.forceUpdate();
            }
        } catch (Exception ex) {
            instance.forceUpdate();
        }
        return instance;

    }

    public List<LocationWeatherDTO> getAllLocationWeathers() throws Exception {

        if (locationWeatherDTOs == null) {
            synchronized (this) {
                initializeLocationWeather();
            }
        }
        return locationWeatherDTOs;
    }

    public WeatherDTO getWeather(LocationDTO locationDTO) {
        WeatherDTO weatherDTO = null;
        try {
            List<LocationWeatherDTO> listOfLocationWeather = getAllLocationWeathers();
            if (listOfLocationWeather != null) {
                for (LocationWeatherDTO locationWeatherDTO : listOfLocationWeather) {
                    if (locationDTO.getGeoCountryName().equals(locationWeatherDTO.getLocation().getGeoCountryName())
                            && locationDTO.getGeoDistrictName().equals(locationWeatherDTO.getLocation().getGeoDistrictName())
                            && locationDTO.getGeoFullName().equals(locationWeatherDTO.getLocation().getGeoFullName())) {
                        return locationWeatherDTO.getWeather();
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return weatherDTO;
    }


    public SimpleDate getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(SimpleDate lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getDatacacheUpdatePeriodInMinutes() {
        return datacacheUpdatePeriodInMinutes;
    }

    public void setDatacacheUpdatePeriodInMinutes(Integer datacacheUpdatePeriodInMinutes) {
        this.datacacheUpdatePeriodInMinutes = datacacheUpdatePeriodInMinutes;
    }

}
