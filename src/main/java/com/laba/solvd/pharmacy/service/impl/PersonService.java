package com.laba.solvd.pharmacy.service.impl;

import com.laba.solvd.pharmacy.mybatis.implDAO.PersonDAO;
import com.laba.solvd.pharmacy.interfaces.IPersonDAO;
import com.laba.solvd.pharmacy.factory.ConnectionDAOFactory;
import com.laba.solvd.pharmacy.factory.DBConnectionType;
import com.laba.solvd.pharmacy.model.Person;

import java.time.LocalDate;
import java.util.List;


public class PersonService implements IPersonDAO<Person> {

    private static final PersonDAO personDAO = (PersonDAO) new ConnectionDAOFactory().getDAOFactory(DBConnectionType.MYBATIS).getDAO("people");

    @Override
    public void saveEntity(Person person) {
        personDAO.saveEntity(person);
    }

    @Override
    public Person getEntityByID(int id) {
        return personDAO.getEntityByID(id);
    }

    @Override
    public void updateEntity(Person person) {
        personDAO.updateEntity(person);
    }

    @Override
    public void removeEntityById(int id) { personDAO.removeEntityById(id); }

    @Override
    public List<Person> getAll() {
        return personDAO.getAll();
    }

    @Override
    public List<Person> getPersonsByBirthDate(LocalDate birthDate) {
        return personDAO.getPersonsByBirthDate(birthDate);
    }
}