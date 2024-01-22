package com.laba.solvd.pharmacy.parsers.parsers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;

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

    public void write(T object, String path) {
        try (FileOutputStream xmlFos = new FileOutputStream(path)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(tclass);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, xmlFos);
            LOGGER.info("Successfully written to xml file", path);
        } catch (JAXBException e) {
            LOGGER.error("Unsuccessful", e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void printObjectDetails(T object) {

        LOGGER.info(object.toString());
    }
}