package com.laba.solvd.jdbc.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public enum Config {
    DRIVER("driver"),
    URL("url"),
    USERNAME("username"),
    PASSWORD("password"),
    POOL_SIZE("pool_size"),
    CONNECTION_METHOD("connection_method");

    private static final Logger logger = LogManager.getLogger(Config.class.getName());

    private final String key;
    private String value;
    private static final Properties properties;
    private static final String file = "src/main/resources/config.properties";

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            properties.load(fis);
        } catch (IOException e) {
            logger.error("Unable to configure database connection", e);
            throw new RuntimeException();
        }
    }

    Config(String key) {
        this.key = key;
    }

    Config(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return properties.getProperty(key, value);
    }

}
