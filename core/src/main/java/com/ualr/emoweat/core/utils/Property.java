package com.ualr.emoweat.core.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/

public class Property {

    private static Properties properties = null;


    private static void init() {
        InputStream file = null;
        try {
            file = new FileInputStream(System.getProperty(Constant.configDirectory) + Constant.emoweat + "/" + Constant.config);
            properties = new Properties();
            properties.load(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getValue(String propertyName) {
        if (properties == null || properties.size() == 0) {
            init();
        }
        return properties.getProperty(propertyName);
    }
}
