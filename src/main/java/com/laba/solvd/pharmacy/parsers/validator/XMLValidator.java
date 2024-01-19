package com.laba.solvd.pharmacy.parsers.validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.FileInputStream;
import java.io.IOException;

public class XMLValidator {

    public static void validateXMLWithXSD(String xmlPath, String xsdPath) {
        try (FileInputStream xmlFis = new FileInputStream(xmlPath);
             FileInputStream xsdFis = new FileInputStream(xsdPath);) {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsdFis));
            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFis));
        } catch (SAXException | IOException e) {
            System.out.println("XML is not valid with XSD schema");
            e.printStackTrace();
        }
        System.out.println("XML is valid with XSD schema");
    }
}
