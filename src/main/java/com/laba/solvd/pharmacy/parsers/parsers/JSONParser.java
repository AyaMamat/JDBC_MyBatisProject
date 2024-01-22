package com.laba.solvd.pharmacy.parsers.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class JSONParser<T> implements Parser<T> {

    private static final Logger LOGGER = LogManager.getLogger(JSONParser.class);
    private final Class<T> clazz;

    public JSONParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T parse(String path) {
        try (FileInputStream jsonFis = new FileInputStream(path)) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            T object = objectMapper.readValue(jsonFis, clazz);

            if (object != null) {
                printObjectDetails(object);
            } else {
                LOGGER.warn("Object is null");
            }

            return object;
        } catch (Exception e) {
            LOGGER.error("Error during parsing", e);
        }

        return null;
    }

    public void write(T object, String path) {
        try (FileOutputStream jsonF = new FileOutputStream(path)) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.writeValue(jsonF, object);
            LOGGER.info("Successfully written to json file", path);
        } catch (Exception e) {
            LOGGER.error("Error writing to json file", e);
        }
    }



    private void printObjectDetails(T object) {
        LOGGER.info(object.toString());
    }
}