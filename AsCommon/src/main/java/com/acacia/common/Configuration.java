package com.acacia.common;

import javax.naming.ConfigurationException;
import java.util.Properties;

/**
 * Created by miaomiao on 6/6/2017.
 */
public class Configuration {
    private static Properties properties = new Properties();


    public static int getInt(String key, int defaultValue) throws ConfigurationException {
        String defaultString = Integer.toString(defaultValue);
        try {
            return Integer.parseInt(properties.getProperty(key,defaultString));
        } catch (NumberFormatException e) {
            throw new ConfigurationException(e.getMessage());
        }
    }


    public static String getValue(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }



}
