package com.ualr.emoweat.twitter.service;

import com.ualr.emoweat.core.utils.Connector;
import com.ualr.emoweat.core.utils.Constant;
import com.ualr.emoweat.twitter.dto.TweetDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/

public class TwitterService {

    public void insertTweet(List<TweetDTO> tweetDTOs) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO buf_tweet_by_location_weather (seq, tweetTime, tweet, buf_weather_seq, insertTime) " +
                "VALUES (?, ?, ?, ?, NOW())";

        try {
            connection = Connector.getConnect();
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            for (TweetDTO tweetDTO : tweetDTOs) {
                preparedStatement.setLong(1, tweetDTO.getId());
                preparedStatement.setTimestamp(2, tweetDTO.getTime());
                preparedStatement.setString(3, tweetDTO.getText());
                preparedStatement.setLong(4, tweetDTO.getLocationWeather().getWeather().getSeq());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
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
