package com.laba.solvd.pharmacy;

import com.laba.solvd.pharmacy.model.Person;
import com.laba.solvd.pharmacy.parsers.parsers.JAXBParser;
import com.laba.solvd.pharmacy.parsers.parsers.JSONParser;
import com.laba.solvd.pharmacy.parsers.parsers.Parser;
import com.laba.solvd.pharmacy.parsers.validator.XMLValidator;
import com.laba.solvd.pharmacy.service.impl.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String xmlPath = "src/main/resources/pharmacy.xml";
        String jsonPath = "src/main/resources/pharmacy.json";
        String xsdPath = "src/main/resources/pharmacy.xsd";

        Parser parser = null;

        LOGGER.info("*** XML VALIDATOR ***");
        XMLValidator.validateXMLWithXSD(xmlPath, xsdPath);

        JAXBParser<Person> personParser = new JAXBParser<>(Person.class);
        Person person = personParser.parse(xmlPath);

        JSONParser<Person> personJSONParser = new JSONParser<>(Person.class);
        Person parsedPerson = personJSONParser.parse(jsonPath);

        LOGGER.info("JSON Person: " + parsedPerson);
        LOGGER.info("JAXB Person: " + person);

        LOGGER.info("\nARE ALL PERSON OBJECTS THE SAME?");
        LOGGER.info( person.equals(parsedPerson));

        PersonService personService = new PersonService();
        List<Person> people = personService.getAll();
        LOGGER.info(people);
    }
}