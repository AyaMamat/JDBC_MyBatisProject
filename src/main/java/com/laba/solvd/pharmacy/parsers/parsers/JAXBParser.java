package com.laba.solvd.pharmacy.parsers.parsers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class JAXBParser<T> implements Parser<T> {

    private static final Logger LOGGER = LogManager.getLogger(JAXBParser.class);
    private final Class<T> tclass;

    public JAXBParser(Class<T> tclass) {
        this.tclass = tclass;
    }
    @Override
    public T parse(String path) {
        try {
            File xmlFile = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(tclass);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            T object = (T) jaxbUnmarshaller.unmarshal(xmlFile);

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

    private void printObjectDetails(T object) {

        LOGGER.info(object.toString());
    }
}