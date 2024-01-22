package com.laba.solvd.pharmacy.interfaces;

import java.time.LocalDate;
import java.util.List;

public interface IPersonDAO<Person> extends IBaseDAO<Person> {

    List<Person> getPersonsByBirthDate(LocalDate birthDate);
}
