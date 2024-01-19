package com.laba.solvd.pharmacy.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConfig {

    private static final Logger logger = LogManager.getLogger(DBConfig.class.getName());
    private static final Properties properties;
    private static final String file = "src/main/resources/config.properties";

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            properties.load(fis);
        } catch (IOException e) {
            logger.error("Unable to configure database connection", e);
            e.printStackTrace();
        }
    }

    public static final String URL = properties.getProperty("url");
    public static final String USERNAME = properties.getProperty("username");
    public static final String PASSWORD = properties.getProperty("password");
    public static final int POOL_SIZE = Integer.parseInt(properties.getProperty("pool_size"));

    public static String getDatabasePassword() {
        return properties.getProperty("password");
    }
}
