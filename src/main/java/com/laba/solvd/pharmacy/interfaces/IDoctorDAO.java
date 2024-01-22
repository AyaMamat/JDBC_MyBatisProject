package com.laba.solvd.pharmacy.interfaces;

import com.laba.solvd.pharmacy.model.Person;

public interface IDoctorDAO<Doctor> extends IBaseDAO<Doctor> {

    Person getByPersonLastName(String lastName);

}
