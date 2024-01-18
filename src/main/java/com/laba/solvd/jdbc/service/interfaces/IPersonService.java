package com.laba.solvd.jdbc.service.interfaces;

import com.laba.solvd.jdbc.model.Person;

import java.util.List;

public interface IPersonService {
    Person create(Person person);
    List<Person> getAll();
}
