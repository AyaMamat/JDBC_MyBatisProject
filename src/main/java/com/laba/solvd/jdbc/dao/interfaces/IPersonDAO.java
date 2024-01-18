package com.laba.solvd.jdbc.dao.interfaces;

import com.laba.solvd.jdbc.model.Person;

import java.util.List;

public interface IPersonDAO extends IBaseDAO<Person> {

    List<Person> getPersonsByBirthDate(String birthDate);
}
