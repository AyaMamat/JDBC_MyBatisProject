package com.laba.solvd.jdbc;

import com.laba.solvd.jdbc.factory.ConnectionMethodFactory;
import com.laba.solvd.jdbc.model.Address;
import com.laba.solvd.jdbc.model.Person;
import com.laba.solvd.jdbc.service.impl.AddressService;
import com.laba.solvd.jdbc.service.impl.PersonService;
import com.laba.solvd.jdbc.service.interfaces.IAddressService;
import com.laba.solvd.jdbc.service.interfaces.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    public static ConnectionMethodFactory connectionFactory = new ConnectionMethodFactory();


    public static void main(String[] args) {
        Person person = new Person();
        IAddressService addressService = new AddressService();
        IPersonService personService = new PersonService(addressService);
        person.setFirstName("Kayla");
        person.setLastName("Kobe");
        person.setBirthDate(LocalDate.parse("2022-03-03"));
        person.setEmail("kayla.kobe@gmail.com");
        person.setPhone("6543334567");
        Address address = new Address();
        address.setStreet("3552 N Winston Str");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZipCode(45332);
        address.setCountry("USA");

        person.setAddress(address);
        LOGGER.debug(personService.create(person));
    }

}
