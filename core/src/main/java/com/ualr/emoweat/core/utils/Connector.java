package com.ualr.emoweat.core.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/

public class Connector {

    public static Connection getConnect() throws SQLException, IllegalAccessException, InstantiationException {
        Connection connection = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + Property.getValue("DB_IP") + ":" + Property.getValue("DB_PORT") + "/" + Property.getValue("DB_NAME"), Property.getValue("DB_USER"), Property.getValue("DB_PASS"));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
