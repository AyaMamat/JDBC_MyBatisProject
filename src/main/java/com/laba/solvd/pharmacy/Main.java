package com.laba.solvd.pharmacy;

import com.laba.solvd.pharmacy.model.Doctor;
import com.laba.solvd.pharmacy.model.Person;
import com.laba.solvd.pharmacy.parsers.parsers.JAXBParser;
import com.laba.solvd.pharmacy.parsers.parsers.JSONParser;
import com.laba.solvd.pharmacy.parsers.validator.XMLValidator;
import com.laba.solvd.pharmacy.service.impl.DoctorService;
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
        String newXmlPath = "src/main/resources/person.xml";
        String newJsonPath = "src/main/resources/person.json";

        LOGGER.info("*** XML VALIDATOR ***");
        XMLValidator.validateXMLWithXSD(xmlPath, xsdPath);

        JAXBParser<Person> personXmlParser = new JAXBParser<>(Person.class);
        Person parsedPersonXml = personXmlParser.parse(xmlPath);

        JSONParser<Person> personJSONParser = new JSONParser<>(Person.class);
        Person parsedPersonJson = personJSONParser.parse(jsonPath);

        personXmlParser.write(parsedPersonXml, newXmlPath);
        personJSONParser.write(parsedPersonJson, newJsonPath);

        LOGGER.info("JSON Person: " + parsedPersonJson);
        LOGGER.info("JAXB Person: " + parsedPersonXml);

        LOGGER.info("\nARE ALL PERSON OBJECTS THE SAME?");
        LOGGER.info(parsedPersonJson.equals(parsedPersonXml));

        PersonService personService = new PersonService();
        List<Person> people = personService.getAll();
        LOGGER.info(people);


        DoctorService doctorService = new DoctorService();
        List<Doctor> doctor = doctorService.getAll();
        LOGGER.info(doctor);
    }
}