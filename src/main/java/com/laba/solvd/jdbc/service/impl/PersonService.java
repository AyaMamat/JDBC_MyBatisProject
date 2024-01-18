package com.laba.solvd.jdbc.service.impl;

import com.laba.solvd.jdbc.dao.implDAO.PersonDAO;
import com.laba.solvd.jdbc.dao.implMapper.PersonMapper;
import com.laba.solvd.jdbc.dao.interfaces.IPersonDAO;
import com.laba.solvd.jdbc.model.Address;
import com.laba.solvd.jdbc.model.Person;
import com.laba.solvd.jdbc.service.interfaces.IAddressService;
import com.laba.solvd.jdbc.service.interfaces.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.laba.solvd.jdbc.Main.connectionFactory;

public class PersonService implements IPersonService {
    private static final Logger LOGGER = LogManager.getLogger(PersonService.class.getName());
    private final IPersonDAO personDAO;
    private final IAddressService addressService;

    public PersonService(IAddressService addressService) {
        this.addressService = addressService;
        if (!connectionFactory.isMyBatis()) {
            this.personDAO = new PersonDAO();
            LOGGER.info("Using JDBC Person repository");
        } else {
            this.personDAO = new PersonMapper();
            LOGGER.info("Using MyBatisSQLFactory Person mapper");
        }
    }

    @Override
    public Person create(Person person) {
        if (person.getAddress() != null) {
            Address address = addressService.create(person.getAddress());
            person.setAddress(address);
        }

        personDAO.saveEntity(person);
        return person;
    }

    @Override
    public List<Person> getAll() {
        return personDAO.getAll();
    }
}
